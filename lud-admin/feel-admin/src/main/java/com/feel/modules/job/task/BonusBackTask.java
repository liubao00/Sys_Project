/**
 * Copyright (c) 2016-2019 feel All rights reserved.
 *
 * https://www.feel.io
 *
 * 版权所有，侵权必究！
 */

package com.feel.modules.job.task;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.feel.common.config.*;
import com.feel.common.exception.FeelException;
import com.feel.common.exception.RRException;
import com.feel.common.utils.TimeUtils;
import com.feel.modules.app.entity.*;
import com.feel.modules.app.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 定时任务(收益返现)
 *
 * CashBackTask为spring bean的名称
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component("BonusBackTask")
public class BonusBackTask implements ITask {
	private Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	private FUserScoreService fUserScoreService;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private FRepeatService fRepeatService;

	@Autowired
	private FProductService fProductService;

	@Autowired
	private FUserSignService fUserSignService;

	@Autowired
	private FUserTeamScoreDetailService fUserTeamScoreDetailService;

	@Override
	@Transactional
	public void run(String params){

		//获取前一天时间
		//每天凌晨轮询一次
		Date dataNow = TimeUtils.getDateAfter(TimeUtils.getDateNow(),-1);
		String data = TimeUtils.formatDate(dataNow,"yyyy-MM-dd");
		List<FUserSignEntity> userSignList = fUserSignService.list();
		//查询status=1冻结状态
		List<FUserTeamScoreDetailEntity> list = fUserTeamScoreDetailService.list(new QueryWrapper<FUserTeamScoreDetailEntity>().and(i ->
				i.eq("data_time",data).eq("status",ScoreStatus.FREEZING_IN_ORDER_AB)));
		if(list.size() > 0){
			list.stream().forEach(item -> {
				//查询用户是否签到
				FUserSignEntity userSign = userSignList.size() > 0 ? userSignList.stream().filter(i ->i.getUserId() == item.getUserId() ).findAny().orElse(null) : null;

				//用户签到才有收益
				if(userSign != null){
					List list1 = JSONArray.parseArray(userSign.getResults());
					String sign = list1.get(Integer.valueOf(data.substring(8))-1).toString();
					//查询用户是否排单
					FRepeatEntity fRepeatEntity = fRepeatService.getOne(new QueryWrapper<FRepeatEntity>().and(i ->
							i.eq("user_id", item.getUserId())
									.eq("product_id", item.getProductId())
									.ne("times",0)));

					//用户签到并且排单了
					if( (sign.equals("1")) && fRepeatEntity != null){
						FUserScoreEntity fUserScoreEntity = fUserScoreService.getOne(new QueryWrapper<FUserScoreEntity>().eq("user_id",item.getUserId()));
						List<FUserTeamScoreDetailEntity> teamDetailList = fUserTeamScoreDetailService.list(new QueryWrapper<FUserTeamScoreDetailEntity>().and(i ->
						i.eq("user_id",item.getUserId()).eq("product_id",item.getProductId()).eq("data_time",data)));

						//A区奖池收益
						BigDecimal A = teamDetailList.size() >0 ? teamDetailList.stream().filter(i -> i.getSection() == 1).findAny().orElse(null) == null ?
								BigDecimal.ZERO : teamDetailList.stream().filter(i -> i.getSection() == 1).findAny().orElse(null).getGrandTotal() : BigDecimal.ZERO;

						//B区奖池收益
						BigDecimal B = teamDetailList.size() >0 ? teamDetailList.stream().filter(i -> i.getSection() == 2).findAny().orElse(null) == null ?
								BigDecimal.ZERO : teamDetailList.stream().filter(i -> i.getSection() == 1).findAny().orElse(null).getGrandTotal() : BigDecimal.ZERO;

						//选择收益小的区域
						BigDecimal score = A.compareTo(B) > 0 ? B : A ;

						//收益大于0
						if(score.compareTo(BigDecimal.ZERO) > 0) {

							FProductEntity fProductEntity = fProductService.getById(item.getProductId());

							//计算真实收益,最多能获取自身排单产品2倍收益
							BigDecimal isScore = score.multiply(new BigDecimal(0.4)).compareTo(fProductEntity.getScore().multiply(new BigDecimal(2))) > 0 ?
									fUserScoreEntity.getScore().multiply(new BigDecimal(2)): score.multiply(new BigDecimal(0.4));
							boolean res2 = scoreService.updateScore(fUserScoreEntity, isScore,
									OperateType.ADD, ScoreType.EARNINGS_SCORE, ScoreStatus.SUCC_IN_ORDER_AB, "返现-增加收益余额-AB区动态收益", TimeUtils.getDateNow());

							if( !res2) {
								throw new RRException("返现失败！");
							}

							//更新状态
							FUserTeamScoreDetailEntity fUserTeamScoreDetailEntity = new FUserTeamScoreDetailEntity.Builder()
									.updateTime(TimeUtils.getDateNow())
									.status(2)
									.build();

							boolean res3 = fUserTeamScoreDetailService.update(fUserTeamScoreDetailEntity,new UpdateWrapper<FUserTeamScoreDetailEntity>().and(i ->
									i.eq("user_id",item.getUserId())
									 .eq("product_id",item.getProductId())
									 .eq("data_time",data)));
							if( !res3) {
								throw new FeelException("返现失败！");
							}
						}else {
							//返现失败
							FUserTeamScoreDetailEntity fUserTeamScoreDetailEntity = new FUserTeamScoreDetailEntity.Builder()
									.updateTime(TimeUtils.getDateNow())
									.status(3)
									.build();

							boolean res3 = fUserTeamScoreDetailService.update(fUserTeamScoreDetailEntity,new UpdateWrapper<FUserTeamScoreDetailEntity>().and(i ->
									i.eq("user_id",item.getUserId())
											.eq("product_id",item.getProductId())
											.eq("data_time",data)));
							if( !res3) {
								throw new FeelException("返现失败！");
							}
						}
					}else {
						//返现失败
						FUserTeamScoreDetailEntity fUserTeamScoreDetailEntity = new FUserTeamScoreDetailEntity.Builder()
								.updateTime(TimeUtils.getDateNow())
								.status(3)
								.build();

						boolean res3 = fUserTeamScoreDetailService.update(fUserTeamScoreDetailEntity,new UpdateWrapper<FUserTeamScoreDetailEntity>().and(i ->
								i.eq("user_id",item.getUserId())
										.eq("product_id",item.getProductId())
										.eq("data_time",data)));
						if( !res3) {
							throw new FeelException("返现失败！");
						}
					}
				}

			});
		}

		logger.debug("BonusBackTask定时任务正在执行，参数为：{}", params);
	}
}

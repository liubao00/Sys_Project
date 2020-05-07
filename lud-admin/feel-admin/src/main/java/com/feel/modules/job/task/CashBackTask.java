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
import com.feel.common.utils.IdUtils;
import com.feel.common.utils.TimeUtils;
import com.feel.modules.app.entity.*;
import com.feel.modules.app.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时任务(收益返现)
 *
 * CashBackTask为spring bean的名称
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component("CashBackTask")
public class CashBackTask implements ITask {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private FUserScoreDetailService fUserScoreDetailService;

	@Autowired
	private FUserScoreService fUserScoreService;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private FRepeatService fRepeatService;

	@Autowired
	private FProductService fProductService;

	@Autowired
	private FUserService fUserService;

	@Autowired
	private FOrderService fOrderService;

	@Autowired
	private FUserSignService fUserSignService;

	@Autowired
	private FUserRelationShipService fUserRelationShipService;

	@Autowired
	private FUserTeamScoreService fUserTeamScoreService;

	@Autowired
	private FUserTeamScoreDetailService fUserTeamScoreDetailService;


	@Override
	@Transactional
	public void run(String params){

		//获取当前时间
		Date dataNow = TimeUtils.getDateNow();
		String data = TimeUtils.formatDate(dataNow,"yyyy-MM-dd");

		//查询到期冻结流水
		List<FUserScoreDetailEntity> list = fUserScoreDetailService.list(new QueryWrapper<FUserScoreDetailEntity>()
		.and(i -> i.eq("status",ScoreStatus.FREEZING_IN_ORDER_EARNING).eq("score_type",ScoreType.EARNINGS_SCORE).le("back_time",dataNow)));

		List<FUserScoreDetailEntity> scoreDetailUpdate = new ArrayList<>();

		List<FUserSignEntity> userSignList = fUserSignService.list();

		if(list.size() >0 ){
			list.stream().forEach(userScoreDetail -> {
				FUserScoreEntity fUserScoreEntity = fUserScoreService.getOne(new QueryWrapper<FUserScoreEntity>().eq("user_id",userScoreDetail.getUserId()));
				Integer id = fUserScoreEntity.getId();

				if(OrePoolConfig.yu_liu_jiang_jin_pool.equals(id)) {//预留奖金池

					boolean res = scoreService.updateScore(fUserScoreEntity,userScoreDetail.getOperateScore(),
							OperateType.ADD,ScoreType.EARNINGS_SCORE,ScoreStatus.SUCC_IN_ORDER_EARNING, ScoreRemark.CASH_BACK_ADD_RESERVE_POOL , TimeUtils.getDateNow());


					if( !res){
						throw new FeelException("积分修改失败！");
					}else {
						userScoreDetail.setUpdateTime(TimeUtils.getDateNow());
						userScoreDetail.setStatus(ScoreStatus.SUCC_IN_ORDER_EARNING);
						scoreDetailUpdate.add(userScoreDetail);

					}

				} else { //收益计算
					FUserSignEntity userSign = userSignList.size() > 0 ? userSignList.stream().filter(i ->i.getUserId() == userScoreDetail.getUserId() ).findAny().orElse(null) : null;
					//判断是否签到
					if(userSign != null){
						FRepeatEntity fRepeatEntity = fRepeatService.getOne(new QueryWrapper<FRepeatEntity>().and(i ->
                                i.eq("user_id",userScoreDetail.getUserId())
                                 .eq("product_id",userScoreDetail.getProductId())));

						FUserScoreDetailEntity fUserScoreDetailEntity = new FUserScoreDetailEntity.Builder()
								.id(userScoreDetail.getId())
								.updateTime(TimeUtils.getDateNow())
								.status(ScoreStatus.SUCC_IN_ORDER_EARNING)
								.build();
						scoreDetailUpdate.add(fUserScoreDetailEntity);

						boolean res2 = true;
						boolean res3 = true;
						boolean res4 = true;

						//文政说返现收益余额，不需要减少冻结收益余额,此处注释掉
//						boolean res1 = scoreService.updateScore(fUserScoreEntity,item.getOperateScore(),
//								2, ScoreType.EARNINGS_SCORE, ScoreStatus.FREEZE_SUCC_IN_EARNING,"减少冻结收益余额");
						//判断用户是否签到，签到才能获取收益
						List list1 = JSONArray.parseArray(userSign.getResults());
						String sign = list1.get(Integer.valueOf(data.substring(8))-1).toString();
						if(sign.equals("1")) {
							//返现-增加收益余额
							res2 = scoreService.updateScore(fUserScoreEntity, userScoreDetail.getOperateScore(),
									OperateType.ADD, ScoreType.EARNINGS_SCORE, ScoreStatus.SUCC_IN_ORDER_EARNING, "返现-增加收益余额", TimeUtils.getDateNow());

							//统计奖金池A/B区
							//----------------------------------------------------------------------------------

							//查层级关系，用户对应A/B区
							FUserRelationShipEntity fUserRelationShipEntity = fUserRelationShipService.getOne(new QueryWrapper<FUserRelationShipEntity>().eq("user_id",userScoreDetail.getUserId()));

							//查询累计奖金池
							FUserTeamScoreEntity fUserTeamScoreEntity = fUserTeamScoreService.getOne(new QueryWrapper<FUserTeamScoreEntity>().eq("user_id",fUserRelationShipEntity.getParentId()));


							//计算累计奖金池
							if(fUserTeamScoreEntity == null){
								FUserTeamScoreEntity userTeam = new FUserTeamScoreEntity.Builder()
										.userId(fUserRelationShipEntity.getParentId())
										.createTime(TimeUtils.getDateNow())
										.section(fUserRelationShipEntity.getSection())
                                        .grandTotal(userScoreDetail.getOperateScore())
										.build();
								boolean res = fUserTeamScoreService.save(userTeam);
								if( !res) {
									throw new FeelException("奖金池修改失败！");
								}
							}else if(fUserRelationShipEntity.getSection() != null){
								FUserTeamScoreEntity userTeam = new FUserTeamScoreEntity.Builder()
										.updateTime(TimeUtils.getDateNow())
										.grandTotal(fUserTeamScoreEntity.getGrandTotal().add(userScoreDetail.getOperateScore()))
										.build();

								boolean res = fUserTeamScoreService.update(userTeam,new UpdateWrapper<FUserTeamScoreEntity>().and(i ->
                                        i.eq("user_id",fUserRelationShipEntity.getParentId())
                                        .eq("section",fUserRelationShipEntity.getSection())));
								if( !res) {
									throw new FeelException("奖金池修改失败！");
								}
							}

							//生成当天动态奖励值
                            //----------------------------------------------------------------------------------

								//查询每日用户统计奖金表
								FUserTeamScoreDetailEntity fUserTeamScoreDetailEntity = fUserTeamScoreDetailService.getOne(new QueryWrapper<FUserTeamScoreDetailEntity>().and(i ->
										i.eq("user_id",fUserRelationShipEntity.getParentId())
												.eq("section",fUserRelationShipEntity.getSection()))
										.eq("data_time",data)
										.eq("product_id",userScoreDetail.getProductId()));


							//计算每日奖金池收益值
							if(fUserTeamScoreDetailEntity == null && fUserRelationShipEntity.getSection() != null){
                                    FUserTeamScoreDetailEntity userTeamDetail = new FUserTeamScoreDetailEntity.Builder()
                                            .userId(fUserRelationShipEntity.getParentId())
                                            .section(fUserRelationShipEntity.getSection())
                                            .grandTotal(userScoreDetail.getOperateScore())
                                            .productId(userScoreDetail.getProductId())
                                            .dataTime(data)
                                            .status(ScoreStatus.FREEZING_IN_ORDER_AB)
                                            .createTime(TimeUtils.getDateNow())
                                            .build();
                                    boolean res = fUserTeamScoreDetailService.save(userTeamDetail);
                                if( !res) {
                                    throw new FeelException("用户每日奖金池新增失败！");
                                }
                            }else if(fUserRelationShipEntity.getSection() != null) {
                                FUserTeamScoreDetailEntity userTeamDetail = new FUserTeamScoreDetailEntity.Builder()
                                        .grandTotal(fUserTeamScoreDetailEntity.getGrandTotal().add(userScoreDetail.getOperateScore()))
                                        .updateTime(TimeUtils.getDateNow())
                                        .build();
                                boolean res = fUserTeamScoreDetailService.update(userTeamDetail,new UpdateWrapper<FUserTeamScoreDetailEntity>().and(i ->
                                        i.eq("user_id",fUserRelationShipEntity.getParentId())
                                         .eq("section",fUserRelationShipEntity.getSection()))
                                         .eq("data_time",data)
                                         .eq("product_id",userScoreDetail.getProductId()));
                                if( !res) {
                                    throw new FeelException("用户每日奖金池修改失败！");
                                }
                            }


						}

						fRepeatEntity.setCreateTiem(null);
						fRepeatEntity.setUpdateTime(TimeUtils.getDateNow());
						if(fRepeatEntity.getProductId() != null && fRepeatEntity.getTimes() < 14){
							fRepeatEntity.setTimes(fRepeatEntity.getTimes()+1);
							fRepeatService.updateById(fRepeatEntity);
						}else if(fRepeatEntity.getProductId() != null && fRepeatEntity.getTimes() ==14) {

							//一轮游戏结束后，初始化
							fRepeatEntity.setTimes(0);
							fRepeatService.updateById(fRepeatEntity);

							FProductEntity fProductEntity = fProductService.getById(userScoreDetail.getProductId());
							//返现-增加可用投资余额
							res3 = scoreService.updateScore(fUserScoreEntity,fProductEntity.getScore(),
									OperateType.ADD,ScoreType.USABLE_SCORE,ScoreStatus.SUCC_IN_ORDER_USABLE,"返现-增加可用投资余额");

							//返现-减少冻结投资余额
							res4= scoreService.updateScore(fUserScoreEntity,fProductEntity.getScore(),
									OperateType.REDUCE,ScoreType.USABLE_SCORE,ScoreStatus.SUCC_IN_ORDER_USABLE,"返现-减少冻结投资余额");

							FUserScoreDetailEntity scoreDetail = fUserScoreDetailService.getOne(new QueryWrapper<FUserScoreDetailEntity>().and(i ->
								i
										.eq("product_id",userScoreDetail.getProductId())
										.eq("order_id",userScoreDetail.getOrderId())
							));

							scoreDetail.setUpdateTime(TimeUtils.getDateNow());
							scoreDetail.setStatus(ScoreStatus.SUCC_IN_ORDER_USABLE);



							//查询是否复投
							FUserEntity fUserEntity = fUserService.getById(userScoreDetail.getUserId());
							if(fUserEntity.getAutomaticOrder() == 1 && userScoreDetail.getProductId() != null) {


								//判断是否有排单资格
								//-----------------------------------------------------------------------------
								//获取商品列表
								List<FProductEntity> listProduct = fProductService.list(new QueryWrapper<FProductEntity>().and(i -> i.eq("status", 1).lt("score", fProductEntity.getScore())));
								List listerror = new ArrayList();
								//查询已排单
								List<FRepeatEntity> listRepeat = fRepeatService.list(new QueryWrapper<FRepeatEntity>().and(i -> i.eq("user_id", userScoreDetail.getUserId()).gt("times", 0)));
								listRepeat.stream().forEach(item -> {
									FProductEntity fProductEntity1 = listProduct.stream().filter(i -> i.getId() == item.getProductId()).findFirst().orElse(null);
									if (fProductEntity1 == null) {
										listerror.add(1);
									}
								});

								if (listerror.size() > 0) {
									logger.error(TimeUtils.getDateNow() + " " + userScoreDetail.getUserId() + "复投失败,请先排单金额小于: " + fProductEntity.getScore() + " 产品");
								} else {
									//复投，需要重新排单
									//-------------------------------------------------------------
									FOrderEntity fOrder = new FOrderEntity.Builder()
											.createTime(TimeUtils.getDateNow())
											.status(1)
											.userId(userScoreDetail.getUserId())
											.serialNo(IdUtils.getRandomIdByUUID())
											.times(1)
											.productId(fProductEntity.getId())
											.totalPrice(fProductEntity.getScore())
											.build();

									//开始排单
									boolean saveAll = fOrderService.saveAll(fOrder);
									if (!saveAll) {
										throw new FeelException("复投失败");
									}
								}
							}

						}
						if( !res2 || !res3 || !res4 ){
							throw new FeelException("积分修改失败！");
						}
					}
				}
			});

			//更新流水状态,收益返现完成
			boolean updateBatchById = true;
			if(scoreDetailUpdate.size() > 0){
				 updateBatchById = fUserScoreDetailService.updateBatchById(scoreDetailUpdate);
			}


			if(!updateBatchById){
				throw new FeelException("更新流水状态失败！");
			}
		}



		logger.debug("CashBackTask定时任务正在执行，参数为：{}", params);
	}
}

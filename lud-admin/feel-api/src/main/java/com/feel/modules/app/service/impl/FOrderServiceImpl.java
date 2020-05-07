package com.feel.modules.app.service.impl;


import com.feel.common.config.OperateType;
import com.feel.common.config.OrePoolConfig;
import com.feel.common.config.ScoreStatus;
import com.feel.common.config.ScoreType;
import com.feel.common.exception.RRException;
import com.feel.common.utils.SysPropertyUtils;
import com.feel.common.utils.TimeUtils;
import com.feel.modules.app.entity.*;
import com.feel.modules.app.service.*;
import net.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.Query;

import com.feel.modules.app.dao.FOrderDao;
import org.springframework.transaction.annotation.Transactional;


@Service("fOrderService")

public class FOrderServiceImpl extends ServiceImpl<FOrderDao, FOrderEntity> implements FOrderService {

    private Logger logger = LoggerFactory.getLogger(FOrderServiceImpl.class);

    @Autowired
    FUserScoreService fUserScoreService;

    @Autowired
    FRepeatService fRepeatService;

    @Autowired
    ScoreService scoreService;

    @Autowired
    FUserScoreDetailService fUserScoreDetailService;

    @Autowired
    FUserRelationShipService fUserRelationShipService;

    @Autowired
    FUserService fUserService;

    @Autowired
    FProductService fProductService;



    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FOrderEntity> page = this.page(
                new Query<FOrderEntity>().getPage(params),
                new QueryWrapper<FOrderEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 拍单
     * @param entity
     * @return
     */
    @Transactional
    public boolean saveAll(FOrderEntity entity) {
        //生成订单
        super.save(entity);

        FProductEntity fProductEntity = fProductService.getById(entity.getProductId());
        FUserRelationShipEntity fUserRelationShipEntity = fUserRelationShipService.getOne(new QueryWrapper<FUserRelationShipEntity>().eq("user_id",entity.getUserId()));
        FUserScoreEntity fUserScoreEntity = fUserScoreService.getOne(new QueryWrapper<FUserScoreEntity>().eq("user_id",entity.getUserId()));

        //获取订单自增Id
        FOrderEntity fOrderEntity = super.getOne(new QueryWrapper<FOrderEntity>().eq("serial_no",entity.getSerialNo()));

        FRepeatEntity repeatEntity = new FRepeatEntity.Builder()
                .userId(entity.getUserId())
                .productId(entity.getProductId())
                .times(1).build();
        FRepeatEntity repeatEntity1 = fRepeatService.getOne(new QueryWrapper<FRepeatEntity>().and(i -> i.eq("user_id",entity.getUserId()).eq("product_id",entity.getProductId())));
        FRepeatEntity repeatEntityParent = fRepeatService.getOne(
                new QueryWrapper<FRepeatEntity>().and(i -> i.eq("user_id",fUserRelationShipEntity.getParentId()).eq("product_id",entity.getProductId()).ne("times",0)));

        //根据userId+productId，新增或更新复投次数
        //存在则更新
        if(repeatEntity1 != null){
            repeatEntity.setId(repeatEntity1.getId());
            repeatEntity.setUpdateTime(TimeUtils.getDateNow());
            repeatEntity.setTimes(repeatEntity1.getTimes()+1);
            fRepeatService.updateById(repeatEntity);
        }else {
            //不存在，初始化数据
            repeatEntity.setCreateTiem(TimeUtils.getDateNow());
            fRepeatService.save(repeatEntity);
        }



        //获取复投次数
        int repeat = repeatEntity1 != null ? repeatEntity1.getTimes() + 1 : 1;

        //获取预留奖金池收益%
        String reserved = SysPropertyUtils.getInstance().getProp("reserved");

        //获取排单周期
        int cycle = Integer.parseInt(SysPropertyUtils.getInstance().getProp("cycle"));

        /*计算排单个人收益*/

        //根据复投次数计算收益
        BigDecimal trueScore;
        BigDecimal poolScore;
        if((repeat % 2) == 0){
            BigDecimal num = new BigDecimal (repeat/2)
                    .multiply(fProductEntity.getSingleBack().divide(new BigDecimal("100")))
                    .compareTo(fProductEntity.getMaxBack().divide(new BigDecimal("100"))) > 0 ?
                    fProductEntity.getMaxBack().divide(new BigDecimal("100"))
                    : new BigDecimal (repeat/2).multiply(fProductEntity.getDoubleBack().divide(new BigDecimal("100")));

            poolScore = num.multiply(entity.getTotalPrice())
                    .multiply((new BigDecimal(reserved)).divide(new BigDecimal("100")));

            trueScore = num.multiply(entity.getTotalPrice())
                    .multiply(new BigDecimal("1").subtract((new BigDecimal(reserved)).divide(new BigDecimal("100"))));
        }else {
            poolScore = fProductEntity.getSingleBack().divide(new BigDecimal("100"))
                    .multiply(entity.getTotalPrice()).multiply(new BigDecimal(reserved)).divide(new BigDecimal("100"));

            trueScore = fProductEntity.getSingleBack().divide(new BigDecimal("100"))
                    .multiply(entity.getTotalPrice()).multiply(new BigDecimal("1").subtract((new BigDecimal(reserved)).divide(new BigDecimal("100"))));
        }

        //生成返现流水
        List<FUserScoreDetailEntity> list = new ArrayList<>();

        boolean res2 = true;

        //FUserScoreEntity poolScoreEntiry = fUserScoreService.getById(OrePoolConfig.yu_liu_jiang_jin_pool);
        FUserScoreEntity poolScoreEntiry = fUserScoreService.getOne(new QueryWrapper<FUserScoreEntity>().eq("user_id",OrePoolConfig.yu_liu_jiang_jin_pool));

        for(int i =1 ; i <= cycle; i++){
            //预留奖金池收益
            boolean  resUpdate2 = scoreService.updateScore(poolScoreEntiry , poolScore,
                    OperateType.ADD,
                    ScoreType.EARNINGS_FREEZE_SCORE,
                    ScoreStatus.FREEZING_IN_ORDER_EARNING,
                    "排单-预留奖金池收益余额",
                    TimeUtils.getDateAfter(TimeUtils.getDateNow(),i),
                    entity.getProductId(),
                    fOrderEntity.getId());


            //需要增加参数，传商品ID，传订单ID
            boolean  resUpdate = scoreService.updateScore(fUserScoreEntity,trueScore,
                    OperateType.ADD,
                    ScoreType.EARNINGS_FREEZE_SCORE,
                    ScoreStatus.FREEZING_IN_ORDER_EARNING,
                    "排单-增加冻结收益余额",
                    TimeUtils.getDateAfter(TimeUtils.getDateNow(),i),
                    entity.getProductId(),
                    fOrderEntity.getId());
            //list.add(scoreDetail);
            if(!resUpdate || !resUpdate2){
                res2 = false;
            }
        }

        /*计算父级拍单收益*/
        //查询父级是否排单，如果排单，需要计算父级收益
        if(repeatEntityParent != null) {
            //动态奖励=直推用户真实收益× 10%
            BigDecimal scoreParent = trueScore.multiply(new BigDecimal("0.1"));
            fUserScoreEntity.setUserId(fUserRelationShipEntity.getParentId());
//            res3 = scoreService.updateScore(fUserScoreEntity,scoreParent.multiply(new BigDecimal(cycle)),
//                    OperateType.ADD,ScoreType.EARNINGS_SCORE,ScoreStatus.EARNING_SUCC_IN_FREEZE,"增加冻结收益余额");
            //推荐人奖励
            for(int i =1 ; i <= cycle; i++){
               boolean resUpdate = scoreService.updateScore(fUserScoreEntity,
                        scoreParent,
                        OperateType.ADD,
                        ScoreType.EARNINGS_FREEZE_SCORE,
                        ScoreStatus.FREEZING_IN_ORDER_RECOMMEND,
                        "排单-直推奖励-增加冻结收益余额",
                        TimeUtils.getDateAfter(TimeUtils.getDateNow(),i));
                //list.add(scoreDetail);
                if(!resUpdate){
                    res2 = false;
                }
            }
        }


        //更新可用余额、冻结金额

        boolean res = scoreService.updateScore(fUserScoreEntity,fOrderEntity.getTotalPrice(),
                OperateType.REDUCE, ScoreType.USABLE_SCORE, ScoreStatus.SUCC_IN_ORDER_USABLE,"排单-减少可用投资余额",TimeUtils.getDateNow(),entity.getProductId(),fOrderEntity.getId());
        boolean res1 = scoreService.updateScore(fUserScoreEntity,fOrderEntity.getTotalPrice(),
                OperateType.ADD,ScoreType.USABLE_FREEZE_SCORE,ScoreStatus.FREEZING_IN_ORDER_USABLE,"排单-增加冻结投资余额",TimeUtils.getDateNow(),entity.getProductId(),fOrderEntity.getId());
        if(!res || !res1 || !res2){
            throw new RRException("积分修改失败！");
        }

        return true;

       // return fUserScoreDetailService.saveBatch(list);
    }
}

package com.feel.modules.app.service.impl;

import com.feel.common.config.ScoreType;
import com.feel.common.exception.FeelException;
import com.feel.common.utils.TimeUtils;
import com.feel.modules.app.dao.FUserScoreDao;
import com.feel.modules.app.dao.FUserScoreDetailDao;
import com.feel.modules.app.entity.FUserScoreDetailEntity;
import com.feel.modules.app.entity.FUserScoreEntity;
import com.feel.modules.app.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: zz
 * @Description:  积分服务service
 * @Date: 8:01 PM 8/25/19
 * @Modified By
 */

@Service("ScoreService")
@Slf4j
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private FUserScoreDetailDao userScoreDetailDao;

    @Autowired
    private FUserScoreDao userScoreDao;


    @Override
    public Boolean updateScore(FUserScoreEntity userScore, BigDecimal operateScore, int operateType , int scoreType, int status, String remark) {
        return updateScore(userScore,operateScore,operateType, scoreType , status,remark,null);
    }

    @Override
    public Boolean updateScore(FUserScoreEntity userScore, BigDecimal operateScore, int operateType , int scoreType, int status, String remark , Date backTime) {
        return updateScore(userScore,operateScore,operateType, scoreType , status,remark,null,null,null);
    }


    /**
     *
     * @param userScore 用户积分表
     * @param operateScore 操作的积分(积分正负)
     * @param operateType 操作类型 1增加 2减少
     * @param scoreType 积分类型 1可用积分 2 收益积分 3.冻结可用积分 4.冻结收益积分
     * @param status 流水状态 1.冻结中，2.余额转冻结 3.收益转冻结 4.冻结结束转余额完成 5.冻结结束转收益完成 6.完成 7.冻结结算收益完成,
     * @param remark 描述
     * @param backTime 返现时间
     * @return
     */
    @Override
    public Boolean updateScore(FUserScoreEntity userScore, BigDecimal operateScore, int operateType , int scoreType, int status, String remark , Date backTime , Integer productId , Integer orderId) {
        userScore = userScoreDao.selectById(userScore.getId());

        BigDecimal baseScore = userScore.getScore();
        BigDecimal freezeScore = userScore.getFreezeScore();

        BigDecimal earningsScore = userScore.getEarningsScore();
        BigDecimal freezeEarnings = userScore.getFreezeEarnings();

        BigDecimal resultScore = null;
        operateScore = operateScore.abs();
        if(operateType == 2) {
            operateScore = operateScore.negate();
        }


        //流水明细
        FUserScoreDetailEntity.Builder scoreDetailBuilder = new FUserScoreDetailEntity.Builder();
        scoreDetailBuilder.createTime(TimeUtils.getDateNow())
                .operateScore(operateScore)
                .operateType(operateType)
                .scoreType(scoreType)
                .remark(remark)
                .userId(userScore.getUserId())
                .backTime(backTime)
                .orderId(orderId)
                .productId(productId);


        if(ScoreType.USABLE_SCORE == scoreType) {  //操作可用积分

            scoreDetailBuilder
                    .score(baseScore)
                    .freezeScore(freezeScore);

            resultScore = baseScore.add(operateScore);
            userScore.setScore(resultScore);

//            //判断积分状态
//            if(ScoreStatus.IN_FREEZING == status) {
//                //冻结中  可用
//                resultScore = freezeScore.add(operateScore);
//                userScore.setFreezeScore(resultScore);
//            }else if(ScoreStatus.USABLE_SUCC_IN_FREEZE == status) {
//                //余额   -> 冻结余额
//                resultScore = freezeScore.add(operateScore);
//                userScore.setFreezeScore(resultScore);
//
//            }else if(ScoreStatus.FREEZE_SUCC_IN_USABLE == status) {
//                //冻结余额  -> 余额
//                resultScore = baseScore.add(operateScore);
//                userScore.setScore(resultScore);
//            }else if(ScoreStatus.FFINISH == status) {
//                //冲币  可用
//                resultScore = baseScore.add(operateScore);
//                userScore.setScore(resultScore);
//            }



        } else if(ScoreType.EARNINGS_SCORE == scoreType) { //操作收益积分
            scoreDetailBuilder
                    .score(earningsScore)
                    .freezeScore(freezeEarnings);

            resultScore = earningsScore.add(operateScore);
            userScore.setEarningsScore(resultScore);


//            //判断积分状态
//            if(ScoreStatus.IN_FREEZING == status) {
//                //冻结中  收益
//                resultScore = freezeEarnings.add(operateScore);
//                userScore.setFreezeEarnings(resultScore);
//            }else if(ScoreStatus.EARNING_SUCC_IN_FREEZE == status) {
//                //收益 -> 冻结收益
//                resultScore = freezeEarnings.add(operateScore);
//                userScore.setFreezeEarnings(resultScore);
//            }else if(ScoreStatus.FREEZE_SUCC_IN_EARNING == status) {
//                //冻结收益 -> 收益
//                resultScore = earningsScore.add(operateScore);
//                userScore.setEarningsScore(resultScore);
//
//            }else if(ScoreStatus.FFINISH == status) {
//                //增加收益 提现
//                resultScore = earningsScore.add(operateScore);
//                userScore.setEarningsScore(resultScore);
//            }


        } else if (ScoreType.USABLE_FREEZE_SCORE == scoreType) { //操作可用冻结积分
            scoreDetailBuilder
                    .score(baseScore)
                    .freezeScore(freezeScore);

            resultScore = freezeScore.add(operateScore);
            userScore.setFreezeScore(resultScore);

        } else if (ScoreType.EARNINGS_FREEZE_SCORE == scoreType) { //操作收益冻结积分
            scoreDetailBuilder
                    .score(earningsScore)
                    .freezeScore(freezeEarnings);

            resultScore = freezeEarnings.add(operateScore);
            userScore.setFreezeEarnings(resultScore);

        } else {
            return false;
        }

        if(resultScore.compareTo(BigDecimal.ZERO) == -1) {
            log.info("insufficient of balance ~!");
            return false;
        }


        try {
            userScore.setUpdateTime(TimeUtils.getDateNow());
            scoreDetailBuilder.status(status);
            return updateUserAndScore(userScore,scoreDetailBuilder.build());
        } catch (Exception e) {
            scoreDetailBuilder.status(1);
            userScoreDetailDao.insert(scoreDetailBuilder.build());
        }
        return false;
    }


    @Transactional
    boolean updateUserAndScore(FUserScoreEntity userScore, FUserScoreDetailEntity userScoreDetail) throws Exception {
        int f1 = userScoreDao.updateById(userScore);
        int f2 = userScoreDetailDao.insert(userScoreDetail);
        if(f1 == 1 && f2 == 1) {
            return true;
        }
        throw new FeelException("save balance error~!");
    }





}

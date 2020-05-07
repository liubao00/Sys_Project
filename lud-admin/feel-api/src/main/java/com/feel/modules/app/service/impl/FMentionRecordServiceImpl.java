package com.feel.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.config.*;
import com.feel.common.utils.*;
import com.feel.modules.app.dao.FMentionRecordDao;
import com.feel.modules.app.entity.FMentionRecordEntity;
import com.feel.modules.app.entity.FUserScoreEntity;
import com.feel.modules.app.service.FMentionRecordService;
import com.feel.modules.app.service.FUserScoreService;
import com.feel.modules.app.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.Map;


@Service("fMentionRecordService")
public class FMentionRecordServiceImpl extends ServiceImpl<FMentionRecordDao, FMentionRecordEntity> implements FMentionRecordService {

    @Autowired
    private FMentionRecordDao mentionRecordDao;

    @Autowired
    private FUserScoreService userScoreService;

    @Autowired
    private ScoreService scoreService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FMentionRecordEntity> page = this.page(
                new Query<FMentionRecordEntity>().getPage(params),
                new QueryWrapper<FMentionRecordEntity>()
        );

        return new PageUtils(page);
    }


    /**
     * t
     * @param fMentionRecord
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R mentionCoin(FMentionRecordEntity fMentionRecord) {
        BigDecimal usdt_rate = new BigDecimal(SysPropertyUtils.getInstance().getProp("usdt_rate"));

        FUserScoreEntity userScore =  userScoreService.getOne(new QueryWrapper<FUserScoreEntity>().eq("user_id",fMentionRecord.getUserId()));

        BigDecimal realScore = null;
        BigDecimal currScore = fMentionRecord.getScore();

        Integer scoreType = null;
        Integer scoreStatus = null;
        String remark = null;

        Integer scoreType2 = null;
        Integer scoreStatus2 = null;
        String remark2 = null;


        if(MentionType.MENTION_COIN_USABLE == fMentionRecord.getType()) {
            realScore = userScore.getScore();
            scoreType = ScoreType.USABLE_SCORE;
            scoreStatus = ScoreStatus.FREEZING_IN_MENTION_USABLE;
            remark = ScoreRemark.MENTION_COIN_USABLE;

            scoreType2 = ScoreType.USABLE_FREEZE_SCORE;
            scoreStatus2 = ScoreStatus.FREEZING_IN_ORDER_USABLE;
            remark2 = ScoreRemark.MENTION_COIN_EARNINGS_FREEZE;

        }else if(MentionType.MENTION_COIN_EARNINGS == fMentionRecord.getType()) {
            realScore = userScore.getEarningsScore();
            scoreType = ScoreType.EARNINGS_SCORE;
            scoreStatus = ScoreStatus.SUCC_IN_MENTION_EARNING;
            remark = ScoreRemark.MENTION_COIN_EARNINGS;

            scoreType2 = ScoreType.EARNINGS_FREEZE_SCORE;
            scoreStatus2 = ScoreStatus.FREEZING_IN_MENTION_EARNING;
            remark2 = ScoreRemark.MENTION_COIN_USABLE_FREEZE;

        }


        if(realScore.compareTo(currScore) >= 0) {
            BigDecimal mention_limit_number = new BigDecimal(SysPropertyUtils.getInstance().getProp("mention_limit_number"));
            BigDecimal mention_rate = new BigDecimal(SysPropertyUtils.getInstance().getProp("mention_rate"));

            BigDecimal lud = currScore.multiply(usdt_rate);
            if(lud.compareTo(mention_limit_number) >= 0) {
                fMentionRecord.setNumber(lud);
                fMentionRecord.setFee(lud.multiply(mention_rate));
                fMentionRecord.setCreateTime(TimeUtils.getDateNow());
                fMentionRecord.setState(1);
                this.save(fMentionRecord);

                boolean res1 = scoreService.updateScore(userScore , currScore , OperateType.REDUCE , scoreType , scoreStatus , remark);

                boolean res2 = scoreService.updateScore(userScore , currScore , OperateType.ADD , scoreType2 , scoreStatus2 , remark2);

                if(res1 && res2) {
                    return R.ok("提现成功,等待客服审核~!");

                }else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return R.error("提现失败,请联系管理员~!");
                }
            } else {
                return R.error("最小提现额为" + mention_limit_number + "lud");
            }
        } else {
            return R.error("提现金额不足,无法提现~!");
        }

    }
}

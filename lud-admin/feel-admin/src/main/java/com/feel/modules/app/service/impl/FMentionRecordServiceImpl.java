package com.feel.modules.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.feel.common.config.*;
import com.feel.common.utils.*;
import com.feel.modules.app.entity.FUserScoreEntity;
import com.feel.modules.app.service.FUserScoreService;
import com.feel.modules.app.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.feel.modules.app.dao.FMentionRecordDao;
import com.feel.modules.app.entity.FMentionRecordEntity;
import com.feel.modules.app.service.FMentionRecordService;
import org.springframework.transaction.annotation.Transactional;


@Service("fMentionRecordService")
@Slf4j
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


    @Override
    public R submit(Integer id) {
        FMentionRecordEntity mentionRecord = mentionRecordDao.selectById(id);
        Integer type = mentionRecord.getType();

        String url = SysPropertyUtils.getInstance().getProp("mention_url");
        //提现

        BigDecimal mentionFee = mentionRecord.getFee();

        //实际提现数量
        BigDecimal number = mentionRecord.getNumber().subtract(mentionFee);
        String address = mentionRecord.getAccount();
        Integer userId = mentionRecord.getUserId();

        HttpClientHelper client = new HttpClientHelper();
        if(MentionType.MENTION_COIN_USABLE == type) {
            //TODO 可用余额提现



        } else if (MentionType.MENTION_COIN_EARNINGS == type){
            //收益提现

            Map<String,Object> param = new HashMap<>();

            param.put("number",number);
            param.put("toaddress",address);
            param.put("userId",userId);
            try {
                JSONObject result = client.doPost(url, param);
                Integer code = result.getInteger("code");
                if(0 == code) {
                    mentionRecord.setState(2);
                    mentionRecord.setUpdateTime(TimeUtils.getDateNow());
                    this.updateById(mentionRecord);
                }
            } catch (IOException e) {
                log.error("interface request error ~!",e);
            }

        }

        return R.ok();

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public R refuse(Integer id) {
        FMentionRecordEntity mentionRecord = mentionRecordDao.selectById(id);
        Integer userId = mentionRecord.getUserId();
        //减冻结
        //增余额
        FUserScoreEntity userScore = userScoreService.getById(userId);
        Integer type = mentionRecord.getType();
        boolean res1 = false;
        boolean res2 = false;
        mentionRecord.setState(4);
        mentionRecord.setUpdateTime(TimeUtils.getDateNow());

        if(MentionType.MENTION_COIN_USABLE == type) {
            res1 = scoreService.updateScore(userScore,mentionRecord.getScore(), OperateType.REDUCE, ScoreType.USABLE_FREEZE_SCORE , ScoreStatus.SUCC_IN_MENTION_USABLE ,ScoreRemark.MENTION_COIN_USABLE_REFUSE);
            res2 = scoreService.updateScore(userScore,mentionRecord.getScore(), OperateType.ADD , ScoreType.USABLE_SCORE , ScoreStatus.FREEZING_IN_ORDER_USABLE , ScoreRemark.MENTION_COIN_USABLE_REFUSE);
        } else if(MentionType.MENTION_COIN_EARNINGS == type) {
            res1 = scoreService.updateScore(userScore,mentionRecord.getScore(), OperateType.REDUCE, ScoreType.EARNINGS_FREEZE_SCORE , ScoreStatus.SUCC_IN_MENTION_EARNING ,ScoreRemark.MENTION_COIN_EARNINGS_REFUSE);
            res2 = scoreService.updateScore(userScore,mentionRecord.getScore(), OperateType.ADD , ScoreType.EARNINGS_SCORE , ScoreStatus.FREEZING_IN_MENTION_EARNING , ScoreRemark.MENTION_COIN_EARNINGS_REFUSE);
        }


        if(res1 && res2) {
            this.updateById(mentionRecord);
            return R.ok();
        }else {
            return R.error("提现拒绝失败~!");
        }

    }
}

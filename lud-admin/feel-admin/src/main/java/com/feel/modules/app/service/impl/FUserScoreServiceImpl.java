package com.feel.modules.app.service.impl;

import com.feel.common.config.OperateType;
import com.feel.common.config.ScoreStatus;
import com.feel.common.config.ScoreType;
import com.feel.common.utils.R;
import com.feel.modules.app.dto.ScoreDto;
import com.feel.modules.app.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.Query;

import com.feel.modules.app.dao.FUserScoreDao;
import com.feel.modules.app.entity.FUserScoreEntity;
import com.feel.modules.app.service.FUserScoreService;


@Service("fUserScoreService")
public class FUserScoreServiceImpl extends ServiceImpl<FUserScoreDao, FUserScoreEntity> implements FUserScoreService {

    @Autowired
    private ScoreService scoreService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FUserScoreEntity> page = this.page(
                new Query<FUserScoreEntity>().getPage(params),
                new QueryWrapper<FUserScoreEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    public R updateScore(ScoreDto scoreDto) {
        FUserScoreEntity userScore = this.getOne(new QueryWrapper<FUserScoreEntity>().eq("user_id",scoreDto.getUserId()));
        boolean flag = false;
        if(ScoreType.USABLE_SCORE == scoreDto.getScoreType()) {

            flag = scoreService.updateScore(userScore, scoreDto.getScore(), OperateType.ADD, ScoreType.USABLE_SCORE, ScoreStatus.SUCC_IN_UPDATE_USABLE, "拨币-人工拨币成功");

        }

        if(flag) {
            return R.ok("拨币成功~!");
        }else {
            return R.error("拨币失败~!");
        }

    }

}

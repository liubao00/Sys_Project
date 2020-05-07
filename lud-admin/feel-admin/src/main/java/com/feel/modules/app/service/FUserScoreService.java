package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.modules.app.dto.ScoreDto;
import com.feel.modules.app.entity.FUserScoreEntity;

import java.util.Map;

/**
 * 用户积分表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-24 00:14:55
 */
public interface FUserScoreService extends IService<FUserScoreEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R updateScore(ScoreDto socreDto);
}


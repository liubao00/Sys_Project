package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.modules.app.entity.FUserTeamScoreEntity;

import java.util.Map;

/**
 * AB区奖金统计表
 *
 * @author feel
 * @email feel.com
 * @date 2019-09-09 09:48:32
 */
public interface FUserTeamScoreService extends IService<FUserTeamScoreEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


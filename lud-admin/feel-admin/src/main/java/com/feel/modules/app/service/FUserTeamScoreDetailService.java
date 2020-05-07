package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.modules.app.entity.FUserTeamScoreDetailEntity;

import java.util.Map;

/**
 * AB区奖金统每日统计表
 *
 * @author feel
 * @email feel.com
 * @date 2019-09-11 15:45:26
 */
public interface FUserTeamScoreDetailService extends IService<FUserTeamScoreDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


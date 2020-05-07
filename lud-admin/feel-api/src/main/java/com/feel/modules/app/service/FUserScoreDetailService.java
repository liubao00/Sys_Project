package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.modules.app.entity.FUserScoreDetailEntity;

import java.util.Map;

/**
 * 用户积分表流水详情
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-27 13:50:10
 */
public interface FUserScoreDetailService extends IService<FUserScoreDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


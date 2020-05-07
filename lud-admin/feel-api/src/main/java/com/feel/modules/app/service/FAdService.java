package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.modules.app.entity.FAdEntity;

import java.util.Map;

/**
 * 广告表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-21 02:24:03
 */
public interface FAdService extends IService<FAdEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


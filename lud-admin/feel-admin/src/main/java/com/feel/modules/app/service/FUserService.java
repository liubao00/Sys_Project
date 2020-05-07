package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.modules.app.entity.FUserEntity;

import java.util.Map;

/**
 * 前端用戶表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-24 00:47:00
 */
public interface FUserService extends IService<FUserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


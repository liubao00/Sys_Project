package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.modules.app.entity.FUserCodeEntity;

import java.util.Map;

/**
 * 验证码
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-27 21:13:45
 */
public interface FUserCodeService extends IService<FUserCodeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


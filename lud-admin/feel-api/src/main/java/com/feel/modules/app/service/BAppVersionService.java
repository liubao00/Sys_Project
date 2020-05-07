package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.modules.app.entity.BAppVersionEntity;

import java.util.Map;

/**
 * app版本管理
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-30 19:53:12
 */
public interface BAppVersionService extends IService<BAppVersionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


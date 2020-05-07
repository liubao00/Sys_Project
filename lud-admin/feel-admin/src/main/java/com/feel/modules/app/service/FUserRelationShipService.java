package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.modules.app.entity.FUserRelationShipEntity;

import java.util.Map;

/**
 * 前端用戶层级关系表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-24 00:14:56
 */
public interface FUserRelationShipService extends IService<FUserRelationShipEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


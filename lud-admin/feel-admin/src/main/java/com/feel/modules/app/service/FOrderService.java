package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.modules.app.entity.FOrderEntity;

import java.util.Map;

/**
 * 限购订单详情表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-26 08:49:46
 */
public interface FOrderService extends IService<FOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean saveAll(FOrderEntity fOrderEntity);
}


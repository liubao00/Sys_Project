package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.modules.app.entity.BAddressUsdtEntity;

import java.util.Map;

/**
 * usdt地址池
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-26 19:35:56
 */
public interface BAddressUsdtService extends IService<BAddressUsdtEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void requestAddress(Integer num);

    String getAddress();
}


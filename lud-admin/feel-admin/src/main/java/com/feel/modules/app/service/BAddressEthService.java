package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.modules.app.entity.BAddressEthEntity;
import lombok.Synchronized;

import java.util.Map;

/**
 * eth地址池
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-31 01:39:07
 */
public interface BAddressEthService extends IService<BAddressEthEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void requestAddress();

    String getAddress();
}


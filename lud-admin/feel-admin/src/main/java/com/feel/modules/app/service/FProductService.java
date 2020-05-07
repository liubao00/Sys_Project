package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.modules.app.entity.FProductEntity;

import java.util.Map;

/**
 * 
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-24 15:58:09
 */
public interface FProductService extends IService<FProductEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


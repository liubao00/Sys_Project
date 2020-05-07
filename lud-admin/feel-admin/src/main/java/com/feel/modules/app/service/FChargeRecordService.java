package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.modules.app.entity.FChargeRecordEntity;

import java.util.Map;

/**
 * 提币记录表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-23 23:23:25
 */
public interface FChargeRecordService extends IService<FChargeRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


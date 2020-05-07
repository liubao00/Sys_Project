package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.modules.app.entity.FUserSignEntity;

import java.util.Map;

/**
 * 用户签到表
 *
 * @author feel
 * @email feel.com
 * @date 2019-09-02 22:48:16
 */
public interface FUserSignService extends IService<FUserSignEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 用户每日一签
     * @param userId
     */
    FUserSignEntity save(Integer userId);
}


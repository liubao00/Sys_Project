package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.modules.app.entity.FRepeatEntity;
import java.util.Map;

/**
 * 复投次数表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-29 15:34:27
 */
public interface FRepeatService extends IService<FRepeatEntity> {

    PageUtils queryPage(Map<String, Object> params);


   public int  selectByIds(String str);
}


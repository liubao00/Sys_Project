package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.modules.app.entity.FMentionRecordEntity;

import java.util.Map;

/**
 * 提币记录表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-24 00:04:31
 */
public interface FMentionRecordService extends IService<FMentionRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R mentionCoin(FMentionRecordEntity fMentionRecord);
}


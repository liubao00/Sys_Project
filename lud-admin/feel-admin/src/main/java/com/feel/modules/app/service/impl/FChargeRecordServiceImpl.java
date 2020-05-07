package com.feel.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.Query;

import com.feel.modules.app.dao.FChargeRecordDao;
import com.feel.modules.app.entity.FChargeRecordEntity;
import com.feel.modules.app.service.FChargeRecordService;


@Service("fChargeRecordService")
public class FChargeRecordServiceImpl extends ServiceImpl<FChargeRecordDao, FChargeRecordEntity> implements FChargeRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FChargeRecordEntity> page = this.page(
                new Query<FChargeRecordEntity>().getPage(params),
                new QueryWrapper<FChargeRecordEntity>()
        );

        return new PageUtils(page);
    }
}

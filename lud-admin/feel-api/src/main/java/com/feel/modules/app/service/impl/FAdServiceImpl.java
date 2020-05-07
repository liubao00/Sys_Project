package com.feel.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.Query;

import com.feel.modules.app.dao.FAdDao;
import com.feel.modules.app.entity.FAdEntity;
import com.feel.modules.app.service.FAdService;


@Service("fAdService")
public class FAdServiceImpl extends ServiceImpl<FAdDao, FAdEntity> implements FAdService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FAdEntity> page = this.page(
                new Query<FAdEntity>().getPage(params),
                new QueryWrapper<FAdEntity>()
        );

        return new PageUtils(page);
    }
}

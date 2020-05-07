package com.feel.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.Query;
import com.feel.modules.app.dao.FUserSignDao;
import com.feel.modules.app.entity.FUserSignEntity;
import com.feel.modules.app.service.FUserSignService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("fUserSignService")
public class FUserSignServiceImpl extends ServiceImpl<FUserSignDao, FUserSignEntity> implements FUserSignService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FUserSignEntity> page = this.page(
                new Query<FUserSignEntity>().getPage(params),
                new QueryWrapper<FUserSignEntity>()
        );

        return new PageUtils(page);
    }
}

package com.feel.modules.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.Query;

import com.feel.modules.app.dao.FProductDao;
import com.feel.modules.app.entity.FProductEntity;
import com.feel.modules.app.service.FProductService;


@Service("fProductService")
public class FProductServiceImpl extends ServiceImpl<FProductDao, FProductEntity> implements FProductService {

    @Autowired
    private FProductDao fProductDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FProductEntity> page = this.page(
                new Query<FProductEntity>().getPage(params),
                new QueryWrapper<FProductEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    public List<FProductEntity> selectByStatus(Map<String, Object> map) {
        return fProductDao.selectByStatus(map);
    }
}

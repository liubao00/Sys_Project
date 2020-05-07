package com.feel.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.Query;

import com.feel.modules.app.dao.BAppVersionDao;
import com.feel.modules.app.entity.BAppVersionEntity;
import com.feel.modules.app.service.BAppVersionService;


@Service("bAppVersionService")
public class BAppVersionServiceImpl extends ServiceImpl<BAppVersionDao, BAppVersionEntity> implements BAppVersionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BAppVersionEntity> page = this.page(
                new Query<BAppVersionEntity>().getPage(params),
                new QueryWrapper<BAppVersionEntity>()
        );

        return new PageUtils(page);
    }
}

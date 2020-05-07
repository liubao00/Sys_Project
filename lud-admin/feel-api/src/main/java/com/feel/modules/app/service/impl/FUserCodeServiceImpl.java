package com.feel.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.Query;

import com.feel.modules.app.dao.FUserCodeDao;
import com.feel.modules.app.entity.FUserCodeEntity;
import com.feel.modules.app.service.FUserCodeService;


@Service("fUserCodeService")
public class FUserCodeServiceImpl extends ServiceImpl<FUserCodeDao, FUserCodeEntity> implements FUserCodeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FUserCodeEntity> page = this.page(
                new Query<FUserCodeEntity>().getPage(params),
                new QueryWrapper<FUserCodeEntity>()
        );

        return new PageUtils(page);
    }
}

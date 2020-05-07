package com.feel.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.Query;

import com.feel.modules.app.dao.FUserDao;
import com.feel.modules.app.entity.FUserEntity;
import com.feel.modules.app.service.FUserService;


@Service("fUserService")
public class FUserServiceImpl extends ServiceImpl<FUserDao, FUserEntity> implements FUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FUserEntity> page = this.page(
                new Query<FUserEntity>().getPage(params),
                new QueryWrapper<FUserEntity>()
        );

        return new PageUtils(page);
    }
}

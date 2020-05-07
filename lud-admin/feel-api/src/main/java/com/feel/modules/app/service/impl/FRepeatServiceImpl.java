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

import com.feel.modules.app.dao.FRepeatDao;
import com.feel.modules.app.entity.FRepeatEntity;
import com.feel.modules.app.service.FRepeatService;


@Service("fRepeatService")
public class FRepeatServiceImpl extends ServiceImpl<FRepeatDao, FRepeatEntity> implements FRepeatService {

    @Autowired
    private FRepeatDao fRepeatDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FRepeatEntity> page = this.page(
                new Query<FRepeatEntity>().getPage(params),
                new QueryWrapper<FRepeatEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public int selectByIds(String str) {
        return fRepeatDao.selectByIds(str);
    }
}

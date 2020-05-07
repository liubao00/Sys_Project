package com.feel.modules.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.Query;

import com.feel.modules.app.dao.FUserRelationShipDao;
import com.feel.modules.app.entity.FUserRelationShipEntity;
import com.feel.modules.app.service.FUserRelationShipService;


@Service("fUserRelationShipService")
public class FUserRelationShipServiceImpl extends ServiceImpl<FUserRelationShipDao, FUserRelationShipEntity> implements FUserRelationShipService {

    @Autowired
    private FUserRelationShipDao fUserRelationShipDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FUserRelationShipEntity> page = this.page(
                new Query<FUserRelationShipEntity>().getPage(params),
                new QueryWrapper<FUserRelationShipEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<HashMap<String, Object>> selectBySection(Map<String,Object> map) {
        return fUserRelationShipDao.selectBySection(map);
    }
}

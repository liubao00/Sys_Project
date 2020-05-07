package com.feel.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.Query;
import com.feel.modules.app.dao.FUserTeamScoreDao;
import com.feel.modules.app.entity.FUserTeamScoreEntity;
import com.feel.modules.app.service.FUserTeamScoreService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("fUserTeamScoreService")
public class FUserTeamScoreServiceImpl extends ServiceImpl<FUserTeamScoreDao, FUserTeamScoreEntity> implements FUserTeamScoreService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FUserTeamScoreEntity> page = this.page(
                new Query<FUserTeamScoreEntity>().getPage(params),
                new QueryWrapper<FUserTeamScoreEntity>()
        );

        return new PageUtils(page);
    }
}

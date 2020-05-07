package com.feel.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.Query;
import com.feel.modules.app.dao.FUserTeamScoreDetailDao;
import com.feel.modules.app.entity.FUserTeamScoreDetailEntity;
import com.feel.modules.app.service.FUserTeamScoreDetailService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("fUserTeamScoreDetailService")
public class FUserTeamScoreDetailServiceImpl extends ServiceImpl<FUserTeamScoreDetailDao, FUserTeamScoreDetailEntity> implements FUserTeamScoreDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FUserTeamScoreDetailEntity> page = this.page(
                new Query<FUserTeamScoreDetailEntity>().getPage(params),
                new QueryWrapper<FUserTeamScoreDetailEntity>()
        );

        return new PageUtils(page);
    }
}

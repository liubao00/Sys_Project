package com.feel.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.Query;

import com.feel.modules.app.dao.FUserScoreDao;
import com.feel.modules.app.entity.FUserScoreEntity;
import com.feel.modules.app.service.FUserScoreService;


@Service("fUserScoreService")
public class FUserScoreServiceImpl extends ServiceImpl<FUserScoreDao, FUserScoreEntity> implements FUserScoreService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FUserScoreEntity> page = this.page(
                new Query<FUserScoreEntity>().getPage(params),
                new QueryWrapper<FUserScoreEntity>()
        );

        return new PageUtils(page);
    }
}

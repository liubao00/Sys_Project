package com.feel.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.Query;

import com.feel.modules.app.dao.FUserScoreDetailDao;
import com.feel.modules.app.entity.FUserScoreDetailEntity;
import com.feel.modules.app.service.FUserScoreDetailService;


@Service("fUserScoreDetailService")
public class FUserScoreDetailServiceImpl extends ServiceImpl<FUserScoreDetailDao, FUserScoreDetailEntity> implements FUserScoreDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FUserScoreDetailEntity> page = this.page(
                new Query<FUserScoreDetailEntity>().getPage(params),
                new QueryWrapper<FUserScoreDetailEntity>()
                        .like("create_time",params.get("date").toString())
                        .eq("user_id",params.get("userId").toString())
        );

        return new PageUtils(page);
    }
}

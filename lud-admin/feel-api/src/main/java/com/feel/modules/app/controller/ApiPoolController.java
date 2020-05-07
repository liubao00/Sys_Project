package com.feel.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feel.common.config.OrePoolConfig;
import com.feel.common.utils.R;
import com.feel.common.utils.SysPropertyUtils;
import com.feel.modules.app.entity.FUserScoreEntity;
import com.feel.modules.app.service.FUserScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zz
 * @Description: 矿池接口
 * @Date: 1:13 AM 9/10/19
 * @Modified By
 */
@RestController
@RequestMapping("api/f/pool")
@Api(tags="矿池接口",value = "矿池接口")
public class ApiPoolController {

    @Autowired
    private FUserScoreService userScoreService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "矿池列表", notes = "矿池列表")
    public R list(){

        List<FUserScoreEntity> pool  = userScoreService.list(new QueryWrapper<FUserScoreEntity>().in("user_id", OrePoolConfig.gong_shi_pool, OrePoolConfig.he_yue_hu_zhu_pool, OrePoolConfig.yu_liu_jiang_jin_pool));
        String pool_address = SysPropertyUtils.getInstance().getProp("pool_address");

        return R.ok().put("page", pool).put("pool_address",pool_address);
    }

}

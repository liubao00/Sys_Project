package com.feel.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feel.common.config.OrePoolConfig;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.Query;
import com.feel.common.utils.R;
import com.feel.common.utils.SysPropertyUtils;
import com.feel.modules.app.entity.FUserScoreEntity;
import com.feel.modules.app.service.FUserScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: zz
 * @Description:
 * @Date: 2:25 AM 9/11/19
 * @Modified By
 */
@RestController
@RequestMapping("v1/b/fpool")
@Api(tags="矿池管理",value = "矿池管理")
public class FPoolController {

    @Autowired
    private FUserScoreService userScoreService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("app:forder:list")
    @ApiOperation(value = "接口功能", notes = "接口功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = new PageUtils(
                userScoreService.page(
                        new Query<FUserScoreEntity>().getPage(params),
                        new QueryWrapper<FUserScoreEntity>().in("user_id", OrePoolConfig.gong_shi_pool, OrePoolConfig.he_yue_hu_zhu_pool, OrePoolConfig.yu_liu_jiang_jin_pool))
        );

//        List<FUserScoreEntity> pool  = userScoreService.list(new QueryWrapper<FUserScoreEntity>().in("user_id", OrePoolConfig.gong_shi_pool, OrePoolConfig.he_yue_hu_zhu_pool, OrePoolConfig.yu_liu_jiang_jin_pool));
        String pool_address = SysPropertyUtils.getInstance().getProp("pool_address");
        return R.ok().put("page", page);
    }
}

package com.feel.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feel.modules.app.entity.FUserTeamScoreDetailEntity;
import com.feel.modules.app.service.FUserTeamScoreDetailService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;


/**
 * AB区奖金统每日统计表
 *
 * @author feel
 * @email feel.com
 * @date 2019-09-11 15:45:26
 */

@RestController
@RequestMapping("v1/b/fuserteamscoredetail")
@Api(tags="表名字",value = "表名字")
public class FUserTeamScoreDetailController {

    @Autowired
    private FUserTeamScoreDetailService fUserTeamScoreDetailService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("app:fuserteamscoredetail:list")
    @ApiOperation(value = "接口功能", notes = "接口功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fUserTeamScoreDetailService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("app:fuserteamscoredetail:info")
    public R info(@PathVariable("id") Integer id){
        FUserTeamScoreDetailEntity fUserTeamScoreDetail = fUserTeamScoreDetailService.getById(id);

        return R.ok().put("fUserTeamScoreDetail", fUserTeamScoreDetail);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("app:fuserteamscoredetail:save")
    public R save(@RequestBody FUserTeamScoreDetailEntity fUserTeamScoreDetail){
        fUserTeamScoreDetailService.save(fUserTeamScoreDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("app:fuserteamscoredetail:update")
    public R update(@RequestBody FUserTeamScoreDetailEntity fUserTeamScoreDetail){
        ValidatorUtils.validateEntity(fUserTeamScoreDetail);
        fUserTeamScoreDetailService.updateById(fUserTeamScoreDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("app:fuserteamscoredetail:delete")
    public R delete(@RequestBody Integer[] ids){
        fUserTeamScoreDetailService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

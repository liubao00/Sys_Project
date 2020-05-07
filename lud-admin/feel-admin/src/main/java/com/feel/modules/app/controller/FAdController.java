package com.feel.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.feel.common.utils.TimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feel.modules.app.entity.FAdEntity;
import com.feel.modules.app.service.FAdService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;


/**
 * 广告表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-21 02:24:03
 */

@RestController
@RequestMapping("v1/b/ad")
@Api(tags="广告",value = "广告")
public class FAdController {

    @Autowired
    private FAdService fAdService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("app:fad:list")
    @ApiOperation(value = "接口功能", notes = "接口功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fAdService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("app:fad:info")
    public R info(@PathVariable("id") Integer id){
        FAdEntity fAd = fAdService.getById(id);

        return R.ok().put("fAd", fAd);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("app:fad:save")
    public R save(@RequestBody FAdEntity fAd){
        fAd.setCreateTime(TimeUtils.getDateNow());
        fAdService.save(fAd);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("app:fad:update")
    public R update(@RequestBody FAdEntity fAd){
        ValidatorUtils.validateEntity(fAd);
        fAd.setUpdateTime(TimeUtils.getDateNow());
        fAdService.updateById(fAd);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("app:fad:delete")
    public R delete(@RequestBody Integer[] ids){
        fAdService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

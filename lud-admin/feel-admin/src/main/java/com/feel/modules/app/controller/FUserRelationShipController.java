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

import com.feel.modules.app.entity.FUserRelationShipEntity;
import com.feel.modules.app.service.FUserRelationShipService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;


/**
 * 前端用戶层级关系表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-25 14:35:00
 */

@RestController
@RequestMapping("v1/b/fuserrelationship")
@Api(tags="前端用戶层级关",value = "前端用戶层级关")
public class FUserRelationShipController {

    @Autowired
    private FUserRelationShipService fUserRelationShipService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("app:fuserrelationship:list")
    @ApiOperation(value = "接口功能", notes = "接口功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fUserRelationShipService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("app:fuserrelationship:info")
    public R info(@PathVariable("id") Integer id){
        FUserRelationShipEntity fUserRelationShip = fUserRelationShipService.getById(id);

        return R.ok().put("fUserRelationShip", fUserRelationShip);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("app:fuserrelationship:save")
    public R save(@RequestBody FUserRelationShipEntity fUserRelationShip){
        fUserRelationShipService.save(fUserRelationShip);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("app:fuserrelationship:update")
    public R update(@RequestBody FUserRelationShipEntity fUserRelationShip){
        ValidatorUtils.validateEntity(fUserRelationShip);
        fUserRelationShipService.updateById(fUserRelationShip);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("app:fuserrelationship:delete")
    public R delete(@RequestBody Integer[] ids){
        fUserRelationShipService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

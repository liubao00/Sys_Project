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
import com.feel.modules.app.entity.FUserEntity;
import com.feel.modules.app.service.FUserService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;


/**
 * 前端用戶表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-24 00:47:00
 */

@RestController
@RequestMapping("v1/b/fuser")
@Api(tags="前端用戶",value = "前端用戶")
public class FUserController {

    @Autowired
    private FUserService fUserService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("app:fuser:list")
    @ApiOperation(value = "接口功能", notes = "接口功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fUserService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("app:fuser:info")
    public R info(@PathVariable("id") Integer id){
        FUserEntity fUser = fUserService.getById(id);

        return R.ok().put("fUser", fUser);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("app:fuser:save")
    public R save(@RequestBody FUserEntity fUser){
        fUserService.save(fUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("app:fuser:update")
    public R update(@RequestBody FUserEntity fUser){
        ValidatorUtils.validateEntity(fUser);
        fUserService.updateById(fUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("app:fuser:delete")
    public R delete(@RequestBody Integer[] ids){
        fUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

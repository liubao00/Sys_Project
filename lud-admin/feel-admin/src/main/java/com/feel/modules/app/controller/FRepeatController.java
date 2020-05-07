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

import com.feel.modules.app.entity.FRepeatEntity;
import com.feel.modules.app.service.FRepeatService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;


/**
 * 复投次数表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-29 15:34:27
 */

@RestController
@RequestMapping("v1/b/frepeat")
@Api(tags="表名字",value = "表名字")
public class FRepeatController {

    @Autowired
    private FRepeatService fRepeatService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("app:frepeat:list")
    @ApiOperation(value = "接口功能", notes = "接口功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fRepeatService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("app:frepeat:info")
    public R info(@PathVariable("id") Integer id){
        FRepeatEntity fRepeat = fRepeatService.getById(id);

        return R.ok().put("fRepeat", fRepeat);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("app:frepeat:save")
    public R save(@RequestBody FRepeatEntity fRepeat){
        fRepeatService.save(fRepeat);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("app:frepeat:update")
    public R update(@RequestBody FRepeatEntity fRepeat){
        ValidatorUtils.validateEntity(fRepeat);
        fRepeatService.updateById(fRepeat);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("app:frepeat:delete")
    public R delete(@RequestBody Integer[] ids){
        fRepeatService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

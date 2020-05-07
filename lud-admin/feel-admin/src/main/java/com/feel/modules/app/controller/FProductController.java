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

import com.feel.modules.app.entity.FProductEntity;
import com.feel.modules.app.service.FProductService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;


/**
 *
 * 排单商品表
 * @author feel
 * @email feel.com
 * @date 2019-08-24 15:58:09
 */

@RestController
@RequestMapping("v1/b/fproduct")
@Api(tags="排单商品",value = "排单商品")
public class FProductController {

    @Autowired
    private FProductService fProductService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("app:fproduct:list")
    @ApiOperation(value = "接口功能", notes = "接口功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fProductService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("app:fproduct:info")
    public R info(@PathVariable("id") Integer id){
        FProductEntity fProduct = fProductService.getById(id);

        return R.ok().put("fProduct", fProduct);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("app:fproduct:save")
    public R save(@RequestBody FProductEntity fProduct){
        fProduct.setCreateTime(TimeUtils.getDateNow());
        fProductService.save(fProduct);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("app:fproduct:update")
    public R update(@RequestBody FProductEntity fProduct){
        ValidatorUtils.validateEntity(fProduct);
        fProduct.setUpdateTime(TimeUtils.getDateNow());
        fProductService.updateById(fProduct);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("app:fproduct:delete")
    public R delete(@RequestBody Integer[] ids){
        fProductService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

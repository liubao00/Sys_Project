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

import com.feel.modules.app.entity.FOrderEntity;
import com.feel.modules.app.service.FOrderService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;


/**
 * 限购订单详情表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-26 08:49:46
 */

@RestController
@RequestMapping("v1/b/forder")
@Api(tags="表名字",value = "表名字")
public class FOrderController {

    @Autowired
    private FOrderService fOrderService;

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
        PageUtils page = fOrderService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("app:forder:info")
    public R info(@PathVariable("id") Integer id){
        FOrderEntity fOrder = fOrderService.getById(id);

        return R.ok().put("fOrder", fOrder);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("app:forder:save")
    public R save(@RequestBody FOrderEntity fOrder){
        fOrderService.save(fOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("app:forder:update")
    public R update(@RequestBody FOrderEntity fOrder){
        ValidatorUtils.validateEntity(fOrder);
        fOrderService.updateById(fOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("app:forder:delete")
    public R delete(@RequestBody Integer[] ids){
        fOrderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

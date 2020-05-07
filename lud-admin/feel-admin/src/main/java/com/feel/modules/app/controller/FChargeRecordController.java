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

import com.feel.modules.app.entity.FChargeRecordEntity;
import com.feel.modules.app.service.FChargeRecordService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;


/**
 * 冲币记录表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-23 23:23:25
 */

@RestController
@RequestMapping("v1/b/fchargerecord")
@Api(tags="冲币记录",value = "冲币记录")
public class FChargeRecordController {

    @Autowired
    private FChargeRecordService fChargeRecordService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("app:fchargerecord:list")
    @ApiOperation(value = "接口功能", notes = "接口功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fChargeRecordService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("app:fchargerecord:info")
    public R info(@PathVariable("id") Integer id){
        FChargeRecordEntity fChargeRecord = fChargeRecordService.getById(id);

        return R.ok().put("fChargeRecord", fChargeRecord);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("app:fchargerecord:save")
    public R save(@RequestBody FChargeRecordEntity fChargeRecord){
        fChargeRecordService.save(fChargeRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("app:fchargerecord:update")
    public R update(@RequestBody FChargeRecordEntity fChargeRecord){
        ValidatorUtils.validateEntity(fChargeRecord);
        fChargeRecordService.updateById(fChargeRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("app:fchargerecord:delete")
    public R delete(@RequestBody Integer[] ids){
        fChargeRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

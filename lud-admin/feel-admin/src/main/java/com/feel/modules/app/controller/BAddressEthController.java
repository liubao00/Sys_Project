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

import com.feel.modules.app.entity.BAddressEthEntity;
import com.feel.modules.app.service.BAddressEthService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;


/**
 * eth地址池
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-31 01:39:07
 */

@RestController
@RequestMapping("v1/b/baddresseth")
@Api(tags="表名字",value = "表名字")
public class BAddressEthController {

    @Autowired
    private BAddressEthService bAddressEthService;


    /**
     * 批量生成地址
     */
    @PostMapping("/saveBatch")
    @RequiresPermissions("app:baddressusdt:save")
    public R saveBatch(){
        bAddressEthService.requestAddress();

        return R.ok();
    }



    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("app:baddresseth:list")
    @ApiOperation(value = "接口功能", notes = "接口功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bAddressEthService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("app:baddresseth:info")
    public R info(@PathVariable("id") Integer id){
        BAddressEthEntity bAddressEth = bAddressEthService.getById(id);

        return R.ok().put("bAddressEth", bAddressEth);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("app:baddresseth:save")
    public R save(@RequestBody BAddressEthEntity bAddressEth){
        bAddressEthService.save(bAddressEth);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("app:baddresseth:update")
    public R update(@RequestBody BAddressEthEntity bAddressEth){
        ValidatorUtils.validateEntity(bAddressEth);
        bAddressEthService.updateById(bAddressEth);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("app:baddresseth:delete")
    public R delete(@RequestBody Integer[] ids){
        bAddressEthService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

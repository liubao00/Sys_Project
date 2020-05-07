package com.feel.modules.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feel.modules.app.entity.BAddressUsdtEntity;
import com.feel.modules.app.service.BAddressUsdtService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;


/**
 * usdt地址池
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-26 19:35:56
 */

@RestController
@RequestMapping("v1/b/baddressusdt")
@Api(tags="表名字",value = "表名字")
@Slf4j
public class BAddressUsdtController {

    @Autowired
    private BAddressUsdtService bAddressUsdtService;


    /**
     * 冲币
     */
    @PostMapping("/chargeCoin")
    @ApiOperation(value = "冲币", notes = "冲币")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "params", required = false, dataType = "json", paramType = "json"),
    })
    @ResponseBody
    public R chargeCoin(@RequestBody Map<String, Object> params){
        log.info("冲币请求　===" + params);


        if (params.get("data") != null) {
            String type = (String) params.get("type");
            if("omni".equals(type)) {
                try {
                    ArrayList arr = (ArrayList) params.get("data");
                    bAddressUsdtService.chargeCoin(arr);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if("erc20".equals(type)) {

            }

        }

        return R.ok();
    }



    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("app:baddressusdt:list")
    @ApiOperation(value = "接口功能", notes = "接口功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bAddressUsdtService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("app:baddressusdt:info")
    public R info(@PathVariable("id") Integer id){
        BAddressUsdtEntity bAddressUsdt = bAddressUsdtService.getById(id);

        return R.ok().put("bAddressUsdt", bAddressUsdt);
    }

    /**
     * 批量生成地址
     */
    @PostMapping("/saveBatch")
    @RequiresPermissions("app:baddressusdt:save")
    public R saveBatch(){
        bAddressUsdtService.requestAddress(null);

        return R.ok();
    }


    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("app:baddressusdt:save")
    public R save(@RequestBody BAddressUsdtEntity bAddressUsdt){
        bAddressUsdtService.save(bAddressUsdt);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("app:baddressusdt:update")
    public R update(@RequestBody BAddressUsdtEntity bAddressUsdt){
        ValidatorUtils.validateEntity(bAddressUsdt);
        bAddressUsdtService.updateById(bAddressUsdt);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("app:baddressusdt:delete")
    public R delete(@RequestBody Integer[] ids){
        bAddressUsdtService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

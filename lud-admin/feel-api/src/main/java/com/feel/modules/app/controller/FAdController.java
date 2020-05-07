package com.feel.modules.app.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feel.modules.app.service.FAdService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;


/**
 * 广告表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-21 02:24:03
 */

@RestController
@RequestMapping("api/f/ad")
@Api(tags="广告表",value = "广告表")
public class FAdController {

    @Autowired
    private FAdService fAdService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "前台广告表", notes = "前台广告表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fAdService.queryPage(params);

        return R.ok().put("page", page);
    }


}

package com.feel.modules.app.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feel.common.annotation.Login;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feel.modules.app.entity.FUserSignEntity;
import com.feel.modules.app.service.FUserSignService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;


/**
 * 用户签到表
 *
 * @author feel
 * @email feel.com
 * @date 2019-09-02 22:48:16
 */

@RestController
@RequestMapping("/api/f/fusersign")
@Api(tags="Api签到",value = "签到")
public class FUserSignController {

    @Autowired
    private FUserSignService fUserSignService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "Api签到列表", notes = "Api签到列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fUserSignService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 用户签到接口  每日签到接口
     */
    @Login
    @ApiOperation(value = "用户每日签到接口(保存用户签到信息)", notes = "保存用户签到信息")
    @GetMapping("/sign/{userId}")
    public R signByUser(@PathVariable("userId") Integer userId){
        FUserSignEntity fUserSign=fUserSignService.save(userId);

        return R.ok().put("data",fUserSign);
    }
    /**
     * 信息
     */
    @Login
    @ApiOperation(value = "获取用户签单接口根据userId(当前月份所有的签到记录)", notes = "获取用户签单接口")
    @GetMapping("/info/{userId}")
    public R info(@PathVariable("userId") Integer userId){
        Date date=new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        String month=format.format(date);
        FUserSignEntity fUserSign = fUserSignService.getOne(new QueryWrapper<FUserSignEntity>().eq("user_id", userId).eq("month", month));
        if(fUserSign==null){
            fUserSign=new FUserSignEntity();
            fUserSign.setResults("[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]");
        }
        return R.ok().put("data", fUserSign);
    }
    /**
     * 保存
     */
    @Login
    @PostMapping("/save")
    public R save(@RequestBody FUserSignEntity fUserSign){
        fUserSignService.save(fUserSign);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody FUserSignEntity fUserSign){
        ValidatorUtils.validateEntity(fUserSign);
        fUserSignService.updateById(fUserSign);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        fUserSignService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

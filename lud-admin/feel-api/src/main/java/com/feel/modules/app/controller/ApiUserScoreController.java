package com.feel.modules.app.controller;

import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feel.common.annotation.Login;
import com.feel.common.utils.StringUtils;
import com.feel.common.utils.TimeUtils;
import com.feel.modules.app.service.FUserScoreDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feel.modules.app.entity.FUserScoreEntity;
import com.feel.modules.app.service.FUserScoreService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;


/**
 * 用户积分表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-25 14:35:00
 */

@RestController
@RequestMapping("api/f/fuserscore")
@Api(tags="Api用户积分表",value = "Api用户积分表")
public class ApiUserScoreController {

    @Autowired
    private FUserScoreService fUserScoreService;

    @Autowired
    private FUserScoreDetailService userScoreDetailService;


    @GetMapping("/list/detail")
    @ApiOperation(value = "获取积分明细", notes = "获取积分明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "date", value = "date", required = false, dataType = "string", paramType = "json"),
            @ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "string", paramType = "json"),
            @ApiImplicitParam(name = "status", value = "userId", required = true, dataType = "string", paramType = "json")
    })
    public R listDetail(@RequestParam Map<String, Object> params){
        String date = (String) params.get("date");
        if(StringUtils.isNull(date)) {
            params.put("date", TimeUtils.getStringOfDate(new Date(),"yyyy-MM"));
        }
        PageUtils page = userScoreDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 获取用户积分信息
     */
    @Login
    @GetMapping("/queryScore/{userId}")
    @ApiOperation(value = "获取登录用户积分信息", notes = "获取用户积分信息")
    public R queryScore(@PathVariable("userId") Integer userId){
        FUserScoreEntity fUserScore = fUserScoreService.getOne(new QueryWrapper<FUserScoreEntity>().eq("user_id", userId));

        return R.ok().put("data", fUserScore);
    }


    /**
     * 列表
     */
/*    @GetMapping("/list")
    @ApiOperation(value = "积分", notes = "接口功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fUserScoreService.queryPage(params);

        return R.ok().put("page", page);
    }*/

    /**
     * 信息
     */
/*    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        FUserScoreEntity fUserScore = fUserScoreService.getById(id);

        return R.ok().put("fUserScore", fUserScore);
    }*/





    /**
     * 保存
     */
/*    @PostMapping("/save")
    public R save(@RequestBody FUserScoreEntity fUserScore){
        fUserScoreService.save(fUserScore);

        return R.ok();
    }*/

    /**
     * 修改
     */
/*    @PostMapping("/update")
    public R update(@RequestBody FUserScoreEntity fUserScore){
        ValidatorUtils.validateEntity(fUserScore);
        fUserScoreService.updateById(fUserScore);

        return R.ok();
    }*/

    /**
     * 删除
     */
/*    @DeleteMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        fUserScoreService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }*/

}

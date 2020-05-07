package com.feel.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.feel.modules.app.dto.ScoreDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feel.modules.app.entity.FUserScoreEntity;
import com.feel.modules.app.service.FUserScoreService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;

import javax.validation.Valid;


/**
 * 用户积分表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-25 14:35:00
 */

@RestController
@RequestMapping("v1/b/fuserscore")
@Api(tags="用户积分",value = "用户积分")
public class FUserScoreController {

    @Autowired
    private FUserScoreService fUserScoreService;



    /**
     * 拨积分
     */
    @PostMapping("/updateScore")
    @RequiresPermissions("app:fuserscore:update")
    @ApiOperation(value = "拨积分功能", notes = "拨积分功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = true, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R updateScore(@RequestBody ScoreDto socreDto) {
        return fUserScoreService.updateScore(socreDto);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("app:fuserscore:list")
    @ApiOperation(value = "接口功能", notes = "接口功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fUserScoreService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("app:fuserscore:info")
    public R info(@PathVariable("id") Integer id){
        FUserScoreEntity fUserScore = fUserScoreService.getById(id);

        return R.ok().put("fUserScore", fUserScore);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("app:fuserscore:save")
    public R save(@RequestBody FUserScoreEntity fUserScore){
        fUserScoreService.save(fUserScore);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("app:fuserscore:update")
    public R update(@RequestBody FUserScoreEntity fUserScore){
        ValidatorUtils.validateEntity(fUserScore);
        fUserScoreService.updateById(fUserScore);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("app:fuserscore:delete")
    public R delete(@RequestBody Integer[] ids){
        fUserScoreService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

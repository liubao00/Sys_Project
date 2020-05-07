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

import com.feel.modules.app.entity.FUserScoreDetailEntity;
import com.feel.modules.app.service.FUserScoreDetailService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;


/**
 * 用户积分表流水详情
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-27 13:50:10
 */

@RestController
@RequestMapping("v1/b/fuserscoredetail")
@Api(tags="表名字",value = "表名字")
public class FUserScoreDetailController {

    @Autowired
    private FUserScoreDetailService fUserScoreDetailService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("app:fuserscoredetail:list")
    @ApiOperation(value = "接口功能", notes = "接口功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fUserScoreDetailService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("app:fuserscoredetail:info")
    public R info(@PathVariable("id") Integer id){
        FUserScoreDetailEntity fUserScoreDetail = fUserScoreDetailService.getById(id);

        return R.ok().put("fUserScoreDetail", fUserScoreDetail);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("app:fuserscoredetail:save")
    public R save(@RequestBody FUserScoreDetailEntity fUserScoreDetail){
        fUserScoreDetailService.save(fUserScoreDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("app:fuserscoredetail:update")
    public R update(@RequestBody FUserScoreDetailEntity fUserScoreDetail){
        ValidatorUtils.validateEntity(fUserScoreDetail);
        fUserScoreDetailService.updateById(fUserScoreDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("app:fuserscoredetail:delete")
    public R delete(@RequestBody Integer[] ids){
        fUserScoreDetailService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

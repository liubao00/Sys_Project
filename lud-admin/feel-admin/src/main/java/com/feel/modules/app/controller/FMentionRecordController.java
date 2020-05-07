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

import com.feel.modules.app.entity.FMentionRecordEntity;
import com.feel.modules.app.service.FMentionRecordService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;


/**
 * 提币记录表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-24 00:04:31
 */

@RestController
@RequestMapping("v1/b/fmentionrecord")
@Api(tags="提币记录",value = "提币记录")
public class FMentionRecordController {

    @Autowired
    private FMentionRecordService fMentionRecordService;


    /**
     * 提币审核确认
     */
    @PostMapping("/submit/{id}")
    @RequiresPermissions("app:fmentionrecord:list")
    @ApiOperation(value = "提币审核确认", notes = "提币审核确认")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "json")
    })
    public R submit(@PathVariable("id") Integer id){
        fMentionRecordService.submit(id);
        return R.ok();
    }

    /**
     * 提币审核拒绝
     */
    @PostMapping("/refuse/{id}")
    @RequiresPermissions("app:fmentionrecord:list")
    @ApiOperation(value = "提币审核拒绝", notes = "提币审核拒绝")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "json")
    })
    public R refuse(@PathVariable("id") Integer id){
        return fMentionRecordService.refuse(id);
    }


    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("app:fmentionrecord:list")
    @ApiOperation(value = "接口功能", notes = "接口功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fMentionRecordService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("app:fmentionrecord:info")
    public R info(@PathVariable("id") Integer id){
        FMentionRecordEntity fMentionRecord = fMentionRecordService.getById(id);

        return R.ok().put("fMentionRecord", fMentionRecord);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("app:fmentionrecord:save")
    public R save(@RequestBody FMentionRecordEntity fMentionRecord){
        fMentionRecordService.save(fMentionRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("app:fmentionrecord:update")
    public R update(@RequestBody FMentionRecordEntity fMentionRecord){
        ValidatorUtils.validateEntity(fMentionRecord);
        fMentionRecordService.updateById(fMentionRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("app:fmentionrecord:delete")
    public R delete(@RequestBody Integer[] ids){
        fMentionRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

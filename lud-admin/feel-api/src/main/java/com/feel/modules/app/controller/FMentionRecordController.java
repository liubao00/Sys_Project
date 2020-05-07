package com.feel.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feel.common.config.MentionType;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.utils.SysPropertyUtils;
import com.feel.common.utils.TimeUtils;
import com.feel.common.validator.ValidatorUtils;
import com.feel.modules.app.entity.FMentionRecordEntity;
import com.feel.modules.app.entity.FUserScoreEntity;
import com.feel.modules.app.service.FMentionRecordService;
import com.feel.modules.app.service.FUserScoreService;
import com.feel.modules.app.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;


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
     * 提币申请
     */
    @PostMapping("/save")
    @ApiOperation(value = "提币申请", notes = "提币申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "用户id", value = "userId", required = true, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "提现积分", value = "score", required = true, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "提现地址" ,value = "account", required = true, dataType = "String", paramType = "json"),
            @ApiImplicitParam(name = "提现类别" ,value = "type", required = true, dataType = "int", paramType = "json")
    })
    public R save(@RequestBody FMentionRecordEntity fMentionRecord){

        return fMentionRecordService.mentionCoin(fMentionRecord);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
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
    public R info(@PathVariable("id") Integer id){
        FMentionRecordEntity fMentionRecord = fMentionRecordService.getById(id);

        return R.ok().put("fMentionRecord", fMentionRecord);
    }



    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody FMentionRecordEntity fMentionRecord){
        ValidatorUtils.validateEntity(fMentionRecord);
        fMentionRecordService.updateById(fMentionRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        fMentionRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

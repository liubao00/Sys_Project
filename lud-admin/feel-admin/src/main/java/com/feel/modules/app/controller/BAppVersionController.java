package com.feel.modules.app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feel.common.utils.StringUtils;
import com.feel.common.utils.TimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feel.modules.app.entity.BAppVersionEntity;
import com.feel.modules.app.service.BAppVersionService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;


/**
 * app版本管理
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-30 19:53:12
 */

@RestController
@RequestMapping("v1/b/bappversion")
@Api(tags="app版本管理接口",value = "app版本管理接口")
public class BAppVersionController {

    Logger logger = LoggerFactory.getLogger(BAppVersionController.class);

    @Autowired
    private BAppVersionService bAppVersionService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("app:bappversion:list")
    @ApiOperation(value = "历史版本列表接口", notes = "历史版本列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bAppVersionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 保存
     */
    @ApiOperation(value = "app版本更新接口")
    @PostMapping("/save")
    @RequiresPermissions("app:bappversion:save")
    public R save(@RequestBody BAppVersionEntity bAppVersion){
        try{
            bAppVersion.setCreateTime(TimeUtils.getDateNow());
            bAppVersion.setStatus(0);
            bAppVersionService.save(bAppVersion);
            return R.ok();
        }catch (Exception e){
            logger.error("save: "+e.toString());
            return R.error(404,"出错了");
        }

    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "app版本审核/下架接口")
    @RequiresPermissions("app:bappversion:update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "json"),
            @ApiImplicitParam(name = "status", value = "status", required = true, dataType = "String", paramType = "json")
    })
    public R update(@RequestBody JSONObject jsonObject){
        try{
            String id = jsonObject.getString("id");
            String status = jsonObject.getString("status");
            if(StringUtils.isNull(id) || StringUtils.isNull(status)){
                return R.error(202,"参数不能为空");
            }
            BAppVersionEntity bAppVersion = new  BAppVersionEntity.Builder()
                    .id(Integer.valueOf(id))
                    .status(Integer.valueOf(status))
                    .updateTime(TimeUtils.getDateNow()).build();
            bAppVersionService.updateById(bAppVersion);
            return R.ok();
        }catch (Exception e){
            logger.error("update: "+e.toString());
            return R.error(404,"出错了！");
        }

    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除接口")
    @RequiresPermissions("app:bappversion:delete")
    public R delete(@RequestBody Long[] ids){
        bAppVersionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


}

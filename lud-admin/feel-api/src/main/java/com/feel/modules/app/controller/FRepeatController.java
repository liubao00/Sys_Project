package com.feel.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.feel.common.annotation.Login;
import com.feel.common.utils.StringUtils;
import com.feel.common.utils.TimeUtils;
import com.feel.modules.app.entity.FUserEntity;
import com.feel.modules.app.service.FUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feel.modules.app.entity.FRepeatEntity;
import com.feel.modules.app.service.FRepeatService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 复投次数表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-29 15:34:27
 */

@RestController
@RequestMapping("api/f/frepeat")
@Api(tags="复投次数接口 ",value = "复投次数接口")
public class FRepeatController {

    @Autowired
    private FUserService fUserService;



    /**
     * 修改
     */
    @ApiOperation(value = "复投开启开关", notes = "复投开启开关")
    @Login
    @PostMapping("/update")
    public R update(@RequestBody JSONObject jsonObject, @ApiIgnore@RequestAttribute("userId") int userId){
        String automaticOrder = jsonObject.getString("automaticOrder");
        if(StringUtils.isNull(automaticOrder)){
            return R.error(1002,"automaticOrder不能为空");
        }
        FUserEntity fUserEntity = new FUserEntity();
        fUserEntity.setAutomaticOrder(Integer.valueOf(automaticOrder));
        fUserEntity.setUpdateTime(TimeUtils.getDateNow());
        fUserEntity.setId(userId);
        boolean res = fUserService.updateById(fUserEntity);
        if(res){
            return R.ok();
        }else {
            return R.error(1002,"修改失败");
        }

    }


}

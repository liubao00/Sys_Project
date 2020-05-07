package com.feel.modules.app.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feel.common.utils.R;
import com.feel.modules.app.entity.BAppVersionEntity;
import com.feel.modules.app.service.BAppVersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * app版本管理
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-30 19:53:12
 */

@RestController
@RequestMapping("api/f/bappversion")
@Api(tags="app版本管理接口",value = "app版本管理接口")
public class BAppVersionController {

    Logger logger = LoggerFactory.getLogger(BAppVersionController.class);

    @Autowired
    private BAppVersionService bAppVersionService;




    /**
     * 列表
     */
    @GetMapping("/newVersion/{type}")
    @ApiOperation(value = "最新app版本接口", notes = "最新app版本")
    public R newVersion(@PathVariable("type") Integer type){
        try{
            List<BAppVersionEntity> list = bAppVersionService.list(new QueryWrapper<BAppVersionEntity>()
                    .eq("status",1)
                    .orderBy(true,false,"create_time")
                    .last("limit 2"));
            if(list.size() > 0){
                logger.error("type"+type);
                BAppVersionEntity bAppVersionEntity = list.stream().filter(i -> i.getType()  == type).findAny().orElse(null);
                return R.ok().put("data",bAppVersionEntity);
            }else {
                return R.ok();
            }
        }catch (Exception e){
            logger.error("newVersion: "+e.toString());
            return R.error(404,"出错了，请联系管理员");
        }

    }

}

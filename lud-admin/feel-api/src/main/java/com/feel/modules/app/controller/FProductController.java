package com.feel.modules.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feel.common.annotation.Login;
import com.feel.common.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feel.modules.app.entity.FProductEntity;
import com.feel.modules.app.service.FProductService;
import com.feel.common.utils.R;


/**
 * 
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-24 15:58:09
 */

@RestController
@RequestMapping("api/f/fproduct")
@Api(tags="产品接口",value = "产品接口")
public class FProductController {

    private Logger logger = LoggerFactory.getLogger(FProductController.class);

    @Autowired
    private FProductService fProductService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取当前可以使用产品列表", notes = "获取当前可以使用产品列表")
    public R list(){
        try{
            List<FProductEntity> list = fProductService.list(new QueryWrapper<FProductEntity>().orderByAsc("score"));
            if(list.size() > 0){
                return R.ok().put("data",list);
            } else {
                return R.ok().put("data","");
            }
        }catch (Exception e){
            logger.error("list:  "+e.toString());
            return R.error(404,"出错了，请联系管理员");
        }

    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @Login
    @ApiOperation(value = "获取单个产品信息", notes = "获取单个产品信息")
    public R info(@PathVariable("id") Integer id){
        FProductEntity fProduct = fProductService.getById(id);

        return R.ok().put("data", fProduct);
    }

}

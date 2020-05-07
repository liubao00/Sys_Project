package com.feel.modules.app.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feel.common.annotation.Login;
import com.feel.common.config.CodeStatus;
import com.feel.common.utils.IdUtils;
import com.feel.common.utils.TimeUtils;
import com.feel.modules.app.entity.FProductEntity;
import com.feel.modules.app.entity.FRepeatEntity;
import com.feel.modules.app.entity.FUserScoreEntity;
import com.feel.modules.app.service.FProductService;
import com.feel.modules.app.service.FRepeatService;
import com.feel.modules.app.service.FUserScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feel.modules.app.entity.FOrderEntity;
import com.feel.modules.app.service.FOrderService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;


/**
 * 限购订单详情表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-26 08:49:46
 */

@RestController
@RequestMapping("api/f/forder")
@Api(tags="订单接口",value = "订单接口")
public class FOrderController {

    private Logger logger = LoggerFactory.getLogger(FOrderController.class);

    @Autowired
    private FOrderService fOrderService;

    @Autowired
    private FUserScoreService fUserScoreService;

    @Autowired
    private FProductService fProductService;

    @Autowired
    private FRepeatService fRepeatService;


    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "订单列表", notes = "订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    @Login
    public R list(@RequestParam Map<String, Object> params,@ApiIgnore @RequestAttribute("userId") long userId){

        String pageNo = params.get("pageIndex")== null ? "0" : params.get("pageIndex").toString();
        String pageSize = params.get("pageSize")== null ? "10" : params.get("pageSize").toString();
        IPage<FOrderEntity> page = new Page<>(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        return R.ok().put("page", fOrderService.page(page,new QueryWrapper<FOrderEntity>().and(i -> i.eq("user_id",userId).eq("status",1))));
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @Login
    public R info(@PathVariable("id") Integer id, @ApiIgnore @RequestAttribute("userId") long userId){
        QueryWrapper<FOrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(i -> i.eq("id",id).ne("userId",userId));
        FOrderEntity fOrder = fOrderService.getOne(queryWrapper);
        return R.ok().put("fOrder", fOrder);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "排单接口", notes = "排单接口")
    @Login
    public R save(@RequestBody FProductEntity fProductEntity, @ApiIgnore @RequestAttribute("userId") int userId){
//    public R save(@RequestBody FProductEntity fProductEntity){
//        int userId = 1;
        logger.info("save: "+ JSONObject.toJSONString(fProductEntity));

        try{

            if(CodeStatus.orderStatus == 0){
                return R.error();
            }

            //校验排单资格
            FRepeatEntity fRepeatEntity = fRepeatService.getOne(new QueryWrapper<FRepeatEntity>().and(i -> i.eq("user_id",userId).eq("product_id",fProductEntity.getId())));
            if(fRepeatEntity != null && fRepeatEntity.getTimes() != 0){
                return R.error(CodeStatus.product_error,"已排单，请本轮结束后再次排单");
            }


            FProductEntity fProduct = fProductService.getById(fProductEntity.getId());
            if(fProduct != null) {
                if(fProduct.getScore().compareTo(fProductEntity.getScore()) != 0){
                    return R.error(CodeStatus.product_error,"商品金额不匹配");
                }
                if(fProduct.getStatus() != 1){
                    return R.error(CodeStatus.product_error,"商品没有上线");
                }
            }else {
                return R.error(CodeStatus.product_error,"商品不存在");
            }


            //获取商品列表
            List<FProductEntity> listProduct = fProductService.list(new QueryWrapper<FProductEntity>().and(i -> i.eq("status",1).lt("score",fProductEntity.getScore())));
            List listerror = new ArrayList();
            //查询已排单
            List<FRepeatEntity> listRepeat = fRepeatService.list(new QueryWrapper<FRepeatEntity>().and(i -> i.eq("user_id",userId).gt("times",0)));

            if(listRepeat.size() == 0 && listProduct.size() != 0){
                listerror.add(1);
            }

            listProduct.stream().forEach(item -> {
               FRepeatEntity fRepeatEntity1 = listRepeat.stream().filter(i -> i.getProductId() == item.getId()).findFirst().orElse(null);
               if(fRepeatEntity1 == null){
                   listerror.add(1);
               }
            });

            if(listerror.size() > 0){
                return R.error(CodeStatus.product_error,"请先排单金额小于: "+fProductEntity.getScore()+" 产品");
            }


            FUserScoreEntity fUserScoreEntity = fUserScoreService.getOne(new QueryWrapper<FUserScoreEntity>().eq("user_id",userId));
            if(fProductEntity.getScore().compareTo(fUserScoreEntity.getScore()) <= 0){
                FOrderEntity fOrder = new FOrderEntity.Builder()
                        .createTime(TimeUtils.getDateNow())
                        .status(1)
                        .userId(userId)
                        .serialNo(IdUtils.getRandomIdByUUID())
                        .times(1)
                        .productId(fProductEntity.getId())
                        .totalPrice(fProduct.getScore())
                        .build();
                boolean res = fOrderService.saveAll(fOrder);
                if(res) {
                    return R.ok();
                } else {
                    return R.error(CodeStatus.product_error,"排单失败");
                }
            }else {
                return R.error(CodeStatus.product_error,"可用资产不足，请先充值");
            }
        }catch (Exception e){
            logger.error("save error: " , e);
            return R.error();
        }


    }

    /**0
     * 修改
     */
    @PostMapping("/update")
    @Login
    public R update(@RequestBody FOrderEntity fOrder, @ApiIgnore @RequestAttribute("userId") int userId){
        ValidatorUtils.validateEntity(fOrder);
        fOrder.setUserId(userId);
        fOrder.setUpdateTime(TimeUtils.getDateNow());
        fOrderService.updateById(fOrder);

        return R.ok();
    }

    /**0
     * 修改
     */
    @GetMapping("/updateOrderStatus/{status}")
    public R updateOrderStatus(@PathVariable("status") int status){
        CodeStatus.orderStatus = status;
        return R.ok().put("data",CodeStatus.orderStatus);
    }

//    /**
//     * 删除
//     */
//    @DeleteMapping("/delete")
//    @Login
//    public R delete(@RequestBody Integer[] ids){
//        fOrderService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }

}

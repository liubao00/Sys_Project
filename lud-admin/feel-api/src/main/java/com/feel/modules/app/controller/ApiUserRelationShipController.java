package com.feel.modules.app.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feel.common.annotation.Login;
import com.feel.common.form.FUserSection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.feel.modules.app.entity.FUserRelationShipEntity;
import com.feel.modules.app.service.FUserRelationShipService;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.validator.ValidatorUtils;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 前端用戶层级关系表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-25 14:35:00
 */

@RestController
@RequestMapping("api/f/fuserrelationship")
@Api(tags="Api用户层级",value = "Api用户层级")
public class ApiUserRelationShipController {

    Logger logger = LoggerFactory.getLogger(ApiUserRelationShipController.class);

    @Autowired
    private FUserRelationShipService fUserRelationShipService;

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
        PageUtils page = fUserRelationShipService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        FUserRelationShipEntity fUserRelationShip = fUserRelationShipService.getById(id);

        return R.ok().put("fUserRelationShip", fUserRelationShip);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody FUserRelationShipEntity fUserRelationShip){
        fUserRelationShipService.save(fUserRelationShip);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @PostMapping("/section")
    @ApiOperation(value = "当前用户下级用户分区(A区,B区)", notes = "接口功能")
    public R update(@RequestBody FUserSection fUserSection){
        ValidatorUtils.validateEntity(fUserSection);
        Integer userId = fUserSection.getUserId();
        Integer section = fUserSection.getSection();
        FUserRelationShipEntity fUserRelationShip = fUserRelationShipService.getOne(new QueryWrapper<FUserRelationShipEntity>().eq("user_id", userId));
        if(fUserRelationShip!=null) {
            fUserRelationShip.setSection(section);
            fUserRelationShipService.updateById(fUserRelationShip);
        }

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        fUserRelationShipService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 团队结构
     */
    @Login
    @GetMapping("/listSection/{section}")
    @ApiOperation(value = "当前用户下级用户分区(A区,B区)", notes = "接口功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R listSection(@ApiIgnore @RequestAttribute("userId") int userId, @PathVariable("section") int section, @RequestParam Map<String, Object> params){
    //public R listSection( @PathVariable("section") int section, @RequestParam Map<String, Object> params){
        //int userId = 10;
        try{
            Object pageIndex = params.get("pageIndex");
            Object pageSize = params.get("pageSize");
            Map<String,Object> map = new HashMap<>();
            map.put("pageIndex",pageIndex==null ? 0: Integer.valueOf(pageIndex.toString())-1);
            map.put("pageSize",pageSize==null ? 0: Integer.valueOf(pageSize.toString()));
            map.put("userId",userId);
            map.put("section",section);
            List<HashMap<String,Object>> list = fUserRelationShipService.selectBySection(map);

            return R.ok().put("data",list);
        }catch (Exception e){
            logger.error("listSection: "+e.toString());
            return R.error(1002,"出错了");
        }

    }

}

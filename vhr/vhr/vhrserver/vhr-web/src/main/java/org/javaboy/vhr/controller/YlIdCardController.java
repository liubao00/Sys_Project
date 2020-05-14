package org.javaboy.vhr.controller;

import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.YlIdCard;
import org.javaboy.vhr.service.YlIdCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 养老用户卡
 */
@RestController
@RequestMapping("/yl/idCard")
public class YlIdCardController {

    @Autowired
    private YlIdCardService ylIdCardService;

    /**
     * 新增养老用户卡
     * @param ylIdCard
     * @return
     */
    @PostMapping("/addYlIdCard")
    public RespBean addYlIdCard(YlIdCard ylIdCard) {
        if (ylIdCardService.addYlIdCard(ylIdCard) == 1) {
            return RespBean.ok("添加养老用户表成功");
        }
        return RespBean.error("添加养老用户表失败");
    }

    /**
     * 查询当前登录人名下的所有养老用户卡
     * @param id
     * @return
     */
    @GetMapping("/findAllYlIdCards")
    public RespBean findAllYlIdCards(@RequestParam("id") String id) {
        if (ylIdCardService.findAllYlIdCards(Integer.parseInt(id)) != null) {
            return RespBean.ok("查询成功").setObj(ylIdCardService.findAllYlIdCards(Integer.parseInt(id)));
        }
        return RespBean.error("查询失败");
    }

    /**
     * 删除养老用户卡
     * @param id
     * @return
     */
    @GetMapping("/deleteYlIdCard")
    public RespBean deleteYlIdCard(@RequestParam("id") String id) {
        if (ylIdCardService.deleteYlIdCard(Integer.parseInt(id)) == 1) {
            return RespBean.ok("删除养老用户表成功");
        }
        return RespBean.error("删除养老用户表失败");
    }

    /**
     * 修改养老用户卡
     * @param ylIdCard
     * @return
     */
    @PostMapping("/updateYlIdCard")
    public RespBean updateYlIdCard(YlIdCard ylIdCard) {
        if (ylIdCardService.updateYlIdCard(ylIdCard) == 1) {
            return RespBean.ok("修改养老用户表成功");
        }
        return RespBean.error("修改养老用户表失败");
    }
}

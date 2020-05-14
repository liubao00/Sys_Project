package org.javaboy.vhr.controller;

import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.YlUser;
import org.javaboy.vhr.service.YlIdCardService;
import org.javaboy.vhr.service.YlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 养老用户
 */
@RestController
@RequestMapping("/yl/user")
public class YlUserController {

    @Autowired
    private YlUserService ylUserService;

    @Autowired
    private YlIdCardService ylIdCardService;
    /**
     * 养老用户的登录
     * @param ylUser
     * @return
     */
    @PostMapping("/webLogin")
    public RespBean webLogin(YlUser ylUser) {
        if (ylUserService.webLogin(ylUser) == 1) {
            Integer id = ylUserService.getUserIdByPhoneNum(ylUser.getPhoneNum());
            return RespBean.ok("登录成功").setObj(id);
        }
        return RespBean.ok("登录失败");
    }

    /**
     * 养老用户的注册
     * @param ylUser
     */
    @PostMapping("/webRegister")
    public RespBean webRegister(YlUser ylUser) {
        if (ylUserService.webRegister(ylUser) == 1) {
            Integer cardId = ylIdCardService.getDefaultYlIdCardId(ylUser.getId());
            return RespBean.ok("注册成功").setObj(cardId);
        }
        return RespBean.ok("注册失败");
    }

    /**
     * 养老用户的密码找回
     * @param ylUser
     */
    @PostMapping("/backPassword")
    public RespBean backPassword(YlUser ylUser) {
        if (ylUserService.backPassword(ylUser) == 1) {
            return RespBean.ok("密码找回成功");
        }
        return RespBean.ok("密码找回失败");
    }
}

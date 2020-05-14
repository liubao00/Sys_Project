package org.javaboy.vhr.controller;

import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.YlUser;
import org.javaboy.vhr.service.YlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * web端用户的登录
 */
@RestController
@RequestMapping("/web/user")
public class YlUserController {

    @Autowired
    private YlUserService ylUserService;

    /**
     * 用户的登录
     * @param ylUser
     * @return
     */
    @PostMapping("/webLogin")
    public RespBean webLogin(YlUser ylUser) {
        if (ylUserService.webLogin(ylUser) == 1) {
            return RespBean.ok("登录成功");
        }
        return RespBean.ok("登录失败");
    }

    /**
     * 用户的注册
     * @param ylUser
     */
    @PostMapping("/webRegister")
    public RespBean webRegister(YlUser ylUser) {
        if (ylUserService.webRegister(ylUser) == 1) {
            return RespBean.ok("注册成功");
        }
        return RespBean.ok("注册失败");
    }

    /**
     * 用户的密码找回
     * @param ylUser
     */
    @PostMapping("/backPassword")
    public void backPassword(YlUser ylUser) {
        ylUserService.backPassword(ylUser);
    }
}

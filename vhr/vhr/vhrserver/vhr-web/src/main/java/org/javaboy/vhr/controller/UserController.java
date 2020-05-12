package org.javaboy.vhr.controller;

import org.javaboy.vhr.model.User;
import org.javaboy.vhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * web端用户的登录
 */
@RestController
@RequestMapping("/web/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户的登录
     * @param user
     * @return
     */
    @PostMapping("/webLogin")
    public Boolean webLogin(User user) {
        return userService.webLogin(user);
    }

    /**
     * 用户的注册
     * @param user
     */
    @PostMapping("/webRegister")
    public void webRegister(User user) {
        userService.webRegister(user);
    }

    /**
     * 用户的密码找回
     * @param user
     */
    @PostMapping("/backPassword")
    public void backPassword(User user) {
        userService.backPassword(user);
    }
}

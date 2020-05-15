package org.javaboy.vhr.mapper;

import org.javaboy.vhr.model.YlUser;

public interface YlUserMapper {
    /**
     * 养老用户登录
     * @param ylUser
     * @return
     */
    YlUser webLogin(YlUser ylUser);

    /**
     * 养老用户注册
     * @param ylUser
     * @return
     */
    Integer webRegister(YlUser ylUser);

    /***
     * 养老用户找回密码
     * @param ylUser
     */
    void backPassword(YlUser ylUser);

    /**
     * 根据手机号查询用户是否存在
     * @param phoneNum
     * @return
     */
    YlUser getUserByPhoneNum(String phoneNum);

    /**
     * 根据手机号查询养老用户的ID
     * @param phoneNum
     * @return
     */
    Integer getUserIdByPhoneNum(String phoneNum);
}

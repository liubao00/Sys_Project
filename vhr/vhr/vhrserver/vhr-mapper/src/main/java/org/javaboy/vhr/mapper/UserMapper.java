package org.javaboy.vhr.mapper;

import org.javaboy.vhr.model.User;

public interface UserMapper {

    User webLogin(User user);

    void webRegister(User user);

    void backPassword(User user);

    User getUserByPhoneNum(String phoneNum);
}

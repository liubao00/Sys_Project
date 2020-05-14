package org.javaboy.vhr.mapper;

import org.javaboy.vhr.model.YlUser;

public interface YlUserMapper {

    YlUser webLogin(YlUser ylUser);

    Integer webRegister(YlUser ylUser);

    void backPassword(YlUser ylUser);

    YlUser getUserByPhoneNum(String phoneNum);
}

package org.javaboy.vhr.service;

import org.javaboy.vhr.Enum.YlUserNameEnum;
import org.javaboy.vhr.mapper.YlIdCardMapper;
import org.javaboy.vhr.mapper.YlUserMapper;
import org.javaboy.vhr.model.YlIdCard;
import org.javaboy.vhr.model.YlUser;
import org.javaboy.vhr.utils.UserNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class YlUserService {

    @Autowired
    private YlUserMapper ylUserMapper;

    @Autowired
    private YlIdCardMapper ylIdCardMapper;
    /**
     * 用户的登录
     * @param ylUser
     */
    public Integer webLogin(YlUser ylUser) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passWord = ylUserMapper.getUserByPhoneNum(ylUser.getPhoneNum()).getUserPassword();
        boolean matches = bCryptPasswordEncoder.matches(ylUser.getUserPassword(), passWord);
        if (matches) {
            return 1;
        }
        return 0;
    }

    /**
     * 用户的注册
     * @param ylUser
     */
    public Integer webRegister(YlUser ylUser) {
        YlIdCard ylIdCard = new YlIdCard();
        YlUser userByPhoneNum = ylUserMapper.getUserByPhoneNum(ylUser.getPhoneNum());
        if (userByPhoneNum != null) {
            return 0;
        } else {
            ylUser.setUserPassword(new BCryptPasswordEncoder().encode(ylUser.getUserPassword()));
            ylUser.setUserName(UserNameUtils.getStringRandom(10));
            ylUserMapper.webRegister(ylUser);
            ylIdCard.setName(YlUserNameEnum.First_YlUserName);//设置第一个默认的账户名称
            ylIdCard.setUserId(ylUser.getId());
            ylIdCardMapper.addYlIdCard(ylIdCard);
            return 1;
        }
    }

    /**
     * 用户的密码找回
     * @param ylUser
     */
    public void backPassword(YlUser ylUser) {
    }
}

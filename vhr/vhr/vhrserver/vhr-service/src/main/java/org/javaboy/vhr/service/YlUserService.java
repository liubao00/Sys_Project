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
            ylIdCard.setIsdefult(YlUserNameEnum.Is_Defult); //默认账号选中：1
            ylIdCardMapper.addYlIdCard(ylIdCard);
            return 1;
        }
    }

    /**
     * 用户的密码找回
     * @param ylUser
     */
    public Integer backPassword(YlUser ylUser) {
        YlUser user = ylUserMapper.getUserByPhoneNum(ylUser.getPhoneNum());
        if (user != null) {
            user.setUserPassword(new BCryptPasswordEncoder().encode(ylUser.getUserPassword()));//重置密码
            try {
                ylUserMapper.backPassword(user);
                return 1;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 根据手机号查询用户是否存在
     * @param phoneNum
     * @return
     */
    public YlUser getUserByPhoneNum(String phoneNum) {
        return ylUserMapper.getUserByPhoneNum(phoneNum);
    }

    /**
     * 根据手机号查询养老用户的ID
     * @param phoneNum
     * @return
     */
    public Integer getUserIdByPhoneNum(String phoneNum) {
        return ylUserMapper.getUserIdByPhoneNum(phoneNum);
    }
}

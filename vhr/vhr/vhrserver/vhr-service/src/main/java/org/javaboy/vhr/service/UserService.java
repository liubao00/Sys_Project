package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.UserAccountMapper;
import org.javaboy.vhr.mapper.UserMapper;
import org.javaboy.vhr.model.User;
import org.javaboy.vhr.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAccountMapper userAccountMapper;
    /**
     * 用户的登录
     * @param user
     */
    public Boolean webLogin(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passWord = userMapper.getUserByPhoneNum(user.getPhoneNum()).getPassWord();
        boolean matches = bCryptPasswordEncoder.matches(user.getPassWord(), passWord);
        if (matches) {
            return true;
        }
        return false;
    }

    /**
     * 用户的注册
     * @param user
     */
    public void webRegister(User user) {
        UserAccount userAccount = new UserAccount();
        userAccount.setName("mm");//设置第一个默认的账户名称
        userAccountMapper.addUserAccount(userAccount);
        user.setPassWord(new BCryptPasswordEncoder().encode(user.getPassWord()));
        user.setAccountId(userAccountMapper.getUserAccountByName("mm").getId());
        userMapper.webRegister(user);
    }

    /**
     * 用户的密码找回
     * @param user
     */
    public void backPassword(User user) {
    }
}

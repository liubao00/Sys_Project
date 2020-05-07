package com.feel.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feel.common.form.FUserFrom;
import com.feel.common.form.FUserPwdFrom;
import com.feel.common.form.LoginForm;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.modules.app.entity.FUserEntity;
import com.feel.modules.app.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 前端用戶表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-25 14:35:00
 */
public interface FUserService extends IService<FUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据用户登录名获取用户信息
     * @param userName
     * @return
     */
    FUserEntity queryUserByName(@Param("userName") String userName);

    /**
     * 注册
     * @param user
     */
    R register(FUserEntity user);


    FUserEntity queryByMobile(String mobile);

    /**
     * 用户登录
     * @param form    登录表单
     * @return        返回登录信息
     */
    Map<String, Object> login(LoginForm form);

    /**
     * 修改用户密码
     */
    void modifyPwd(FUserPwdFrom pwd);
}


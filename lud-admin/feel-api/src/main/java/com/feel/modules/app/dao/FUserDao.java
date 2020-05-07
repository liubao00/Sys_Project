package com.feel.modules.app.dao;

import com.feel.modules.app.entity.FUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 前端用戶表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-25 14:35:00
 */
@Mapper
public interface FUserDao extends BaseMapper<FUserEntity> {

    /**
     * 根据用户登录名获取用户信息
     * @param userName
     * @return
     */
    FUserEntity queryUserByName(@Param("userName") String userName);

}

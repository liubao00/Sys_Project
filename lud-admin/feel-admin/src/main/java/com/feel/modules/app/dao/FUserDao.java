package com.feel.modules.app.dao;

import com.feel.modules.app.entity.FUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 前端用戶表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-24 00:47:00
 */
@Mapper
public interface FUserDao extends BaseMapper<FUserEntity> {

}

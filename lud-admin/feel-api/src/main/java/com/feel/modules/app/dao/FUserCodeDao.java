package com.feel.modules.app.dao;

import com.feel.modules.app.entity.FUserCodeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 验证码
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-27 21:13:45
 */
@Mapper
public interface FUserCodeDao extends BaseMapper<FUserCodeEntity> {

}

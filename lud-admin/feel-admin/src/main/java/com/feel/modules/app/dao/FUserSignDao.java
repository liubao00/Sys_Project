package com.feel.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feel.modules.app.entity.FUserSignEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户签到表
 *
 * @author feel
 * @email feel.com
 * @date 2019-09-02 22:48:16
 */
@Mapper
public interface FUserSignDao extends BaseMapper<FUserSignEntity> {

}

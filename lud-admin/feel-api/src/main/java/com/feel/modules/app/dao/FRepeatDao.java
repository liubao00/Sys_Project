package com.feel.modules.app.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.feel.modules.app.entity.FRepeatEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 复投次数表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-29 15:34:27
 */
@Mapper
public interface FRepeatDao extends BaseMapper<FRepeatEntity> {

    @Select("select count(*) from (select DISTINCT user_id from f_repeat where times >=1 and user_id in(#{str}))a")
    int selectByIds(String str);

}

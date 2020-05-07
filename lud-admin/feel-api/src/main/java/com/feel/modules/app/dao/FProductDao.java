package com.feel.modules.app.dao;

import com.feel.modules.app.entity.FProductEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-24 15:58:09
 */
@Mapper
public interface FProductDao extends BaseMapper<FProductEntity> {

    List<FProductEntity> selectByStatus(Map<String,Object> map);

}

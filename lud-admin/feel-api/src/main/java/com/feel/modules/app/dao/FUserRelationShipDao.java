package com.feel.modules.app.dao;

import com.feel.modules.app.entity.FUserRelationShipEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 前端用戶层级关系表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-25 14:35:00
 */
@Mapper
public interface FUserRelationShipDao extends BaseMapper<FUserRelationShipEntity> {

    List<HashMap<String,Object>> selectBySection(Map<String,Object> map);

}

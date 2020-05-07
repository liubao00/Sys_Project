package com.feel.modules.app.dao;

import com.feel.modules.app.entity.FOrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 限购订单详情表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-26 08:49:46
 */
@Mapper
public interface FOrderDao extends BaseMapper<FOrderEntity> {

}

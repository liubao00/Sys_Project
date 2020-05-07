package com.feel.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feel.modules.app.entity.BAppVersionEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * app版本管理
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-30 19:53:12
 */
@Mapper
public interface BAppVersionDao extends BaseMapper<BAppVersionEntity> {

}

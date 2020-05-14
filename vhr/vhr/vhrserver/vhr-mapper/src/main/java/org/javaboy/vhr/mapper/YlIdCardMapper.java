package org.javaboy.vhr.mapper;


import org.javaboy.vhr.model.YlIdCard;

import java.util.List;

public interface YlIdCardMapper {

    /**
     * 添加养老卡
     * @param ylIdCard
     * @return
     */
    Integer addYlIdCard(YlIdCard ylIdCard);

    /**
     * 查询当前登录人名下的全部养老卡
     * @param id
     * @return
     */
    List<YlIdCard> findAllYlIdCards(Integer id);

    /**
     * 删除养老卡
     * @param id
     * @return
     */
    Integer deleteYlIdCard(Integer id);

    /**
     * 修改养老卡
     * @param ylIdCard
     * @return
     */
    Integer updateYlIdCard(YlIdCard ylIdCard);

    /**
     * 查询当前用户下的默认的养老卡账号的ID
     * @param id
     * @return
     */
    Integer getDefaultYlIdCardId(Integer id);
}

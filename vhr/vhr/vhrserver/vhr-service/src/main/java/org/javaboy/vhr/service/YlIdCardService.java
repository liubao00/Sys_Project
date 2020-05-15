package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.YlIdCardMapper;
import org.javaboy.vhr.model.YlIdCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class YlIdCardService {

    @Autowired
    private YlIdCardMapper ylIdCardMapper;

    /**
     * 添加养老卡
     * @param ylIdCard
     * @return
     */
    public Integer addYlIdCard(YlIdCard ylIdCard){
        return ylIdCardMapper.addYlIdCard(ylIdCard);
    }

    /**
     * 查询当前登录人名下的全部养老卡
     * @param id
     * @return
     */
    public List<YlIdCard> findAllYlIdCards(Integer id){
        List<YlIdCard> allYlIdCards = new ArrayList<>();
        try {
             allYlIdCards = ylIdCardMapper.findAllYlIdCards(id);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return allYlIdCards;
    }

    /**
     * 删除养老卡
     * @param id
     * @return
     */
    public Integer deleteYlIdCard(Integer id){
        return ylIdCardMapper.deleteYlIdCard(id);
    }

    /**
     * 修改养老卡
     * @param ylIdCard
     * @return
     */
    public Integer updateYlIdCard(YlIdCard ylIdCard){
        return ylIdCardMapper.updateYlIdCard(ylIdCard);
    }

    /**
     * 查询当前用户下的默认的养老卡账号的ID
     * @param id
     * @return
     */
    public Integer getDefaultYlIdCardId(Integer id) {
        return ylIdCardMapper.getDefaultYlIdCardId(id);
    }
}

package org.javaboy.vhr.mapper;


import org.javaboy.vhr.model.YlIdCard;

public interface YlIdCardMapper {

    Integer addYlIdCard(YlIdCard ylIdCard);

    YlIdCard getYlIdCardByName(String name);
}

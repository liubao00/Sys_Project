package com.feel.modules.app.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.Query;

import com.feel.modules.app.dao.FUserSignDao;
import com.feel.modules.app.entity.FUserSignEntity;
import com.feel.modules.app.service.FUserSignService;


@Service("fUserSignService")
public class FUserSignServiceImpl extends ServiceImpl<FUserSignDao, FUserSignEntity> implements FUserSignService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FUserSignEntity> page = this.page(
                new Query<FUserSignEntity>().getPage(params),
                new QueryWrapper<FUserSignEntity>()
        );

        return new PageUtils(page);
    }

//    public static void main(String[] args){
//        Date date=new Date();
//        Calendar now = Calendar.getInstance();
//        int day=now.get(Calendar.DAY_OF_MONTH)-1;
//        DateFormat format = new SimpleDateFormat("yyyy-MM");
//        String month=format.format(date);
//        System.out.println(month);
//        String signs="[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]";
//        List list= JSON.parseArray(signs);
//        list.set(day,1);
//        System.out.println(list.size());
//        list.set(20,1);
//        System.out.println(list.toString());
//        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
//
//    }
    /**
     * 用户每日一签
     * @param userId
     */
    public FUserSignEntity save(Integer userId){
        FUserSignEntity fUserSignEntity=null;
        Date date=new Date();
        Calendar now = Calendar.getInstance();
        int day=now.get(Calendar.DAY_OF_MONTH)-1;
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        String month=format.format(date);
         fUserSignEntity=baseMapper.selectOne(new QueryWrapper<FUserSignEntity>().eq("user_id", userId).eq("month", month));
         if(fUserSignEntity==null){
             fUserSignEntity=new FUserSignEntity();
             fUserSignEntity.setCreateTime(date);
             fUserSignEntity.setUpdateTime(date);
             fUserSignEntity.setMonth(month);
             String signs="[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]";
             List list= JSON.parseArray(signs);
             list.set(day,1);//签到一次0改为1
             fUserSignEntity.setResults(list.toString());
             fUserSignEntity.setStatus(1);
             fUserSignEntity.setUserId(userId);
             baseMapper.insert(fUserSignEntity);
         }else{
             String signs=fUserSignEntity.getResults();
             List list= JSON.parseArray(signs);
             list.set(day,1);//签到一次0改为1
             fUserSignEntity.setResults(list.toString());
             baseMapper.updateById(fUserSignEntity);
         }
        return fUserSignEntity;

    }
}

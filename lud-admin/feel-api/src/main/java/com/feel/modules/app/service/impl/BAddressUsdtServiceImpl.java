package com.feel.modules.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feel.common.utils.*;
import com.feel.modules.app.dao.BAddressUsdtDao;
import com.feel.modules.app.entity.BAddressUsdtEntity;
import com.feel.modules.app.service.BAddressUsdtService;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("bAddressUsdtService")
@Slf4j
public class BAddressUsdtServiceImpl extends ServiceImpl<BAddressUsdtDao, BAddressUsdtEntity> implements BAddressUsdtService {

    @Autowired
    private BAddressUsdtDao addressUsdtDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BAddressUsdtEntity> page = this.page(
                new Query<BAddressUsdtEntity>().getPage(params),
                new QueryWrapper<BAddressUsdtEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    public void requestAddress(Integer nums) {
        String url = SysPropertyUtils.getInstance().getProp("chain_url");
        Integer num = Integer.valueOf(SysPropertyUtils.getInstance().getProp("addressNum"));
        HttpClientHelper client = new HttpClientHelper();
        HashMap<String,Object> param = new HashMap<>();
        String UUID = null ;
        try {

            for (int i = 0 ; i < num ; i++) {
                UUID = IDGenerator.UUID.generate();
                param.put("account", UUID);
                param.put("userId", UUID);

                JSONObject result = client.doPost(url, param);
                Integer status = result.getInteger("status");
                if(status == 1) {
                    String address = result.getString("address");
                    BAddressUsdtEntity addressUsdtEntity = new BAddressUsdtEntity.Builder()
                            .address(address)
                            .createTime(TimeUtils.getDateNow())
                            .status(0+"")
                            .remark(UUID)
                            .build();
                    save(addressUsdtEntity);
                }
            }


        } catch (IOException e) {
            log.error("address create error ~!",e);
        }
    }

    @Override
    @Synchronized
    public String getAddress() {
        List<BAddressUsdtEntity> list = addressUsdtDao.selectList(new QueryWrapper<BAddressUsdtEntity>().eq("status", 0));
        if(list != null && list.size() > 0) {
            BAddressUsdtEntity entity = list.get(0);
            entity.setUpdateTime(TimeUtils.getDateNow());
            entity.setStatus(1+"");
            updateById(entity);
            return entity.getAddress();
        }else {
            requestAddress(1);
            return null;
        }
    }



}

package com.feel.modules.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.feel.common.utils.*;
import com.feel.common.utils.generate.IDGenerator;
import com.feel.modules.app.entity.BAddressUsdtEntity;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.feel.modules.app.dao.BAddressEthDao;
import com.feel.modules.app.entity.BAddressEthEntity;
import com.feel.modules.app.service.BAddressEthService;


@Service("bAddressEthService")
@Slf4j
public class BAddressEthServiceImpl extends ServiceImpl<BAddressEthDao, BAddressEthEntity> implements BAddressEthService {

    @Autowired
    private BAddressEthDao bAddressEthDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BAddressEthEntity> page = this.page(
                new Query<BAddressEthEntity>().getPage(params),
                new QueryWrapper<BAddressEthEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void requestAddress() {
        String url = SysPropertyUtils.getInstance().getProp("chain_url_eth");
        Integer num = Integer.valueOf(SysPropertyUtils.getInstance().getProp("addressNum"));
        HttpClientHelper client = new HttpClientHelper();
        HashMap<String,Object> param = new HashMap<>();
        String UUID = null ;
        try {

            for (int i = 0 ; i < num ; i++) {
                UUID = IDGenerator.UUID.generate();
                param.put("account", UUID);
                param.put("userId", UUID);

                JSONObject result = client.doGet(url, param);
                Integer status = result.getInteger("status");
                if(status == 1) {
                    String address = result.getString("address");
                    BAddressEthEntity addressUsdtEntity = new BAddressEthEntity.Builder()
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
        List<BAddressEthEntity> list = bAddressEthDao.selectList(new QueryWrapper<BAddressEthEntity>().eq("status", 0));
        if(list != null && list.size() > 0) {
            return list.get(0).getAddress();
        }
        return null;
    }
}

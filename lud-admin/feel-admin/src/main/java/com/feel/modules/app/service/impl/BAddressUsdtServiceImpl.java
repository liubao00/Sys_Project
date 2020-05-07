package com.feel.modules.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.feel.common.config.OperateType;
import com.feel.common.config.ScoreRemark;
import com.feel.common.config.ScoreStatus;
import com.feel.common.config.ScoreType;
import com.feel.common.utils.*;
import com.feel.modules.app.dao.FChargeRecordDao;
import com.feel.modules.app.dao.FUserDao;
import com.feel.modules.app.dao.FUserScoreDao;
import com.feel.modules.app.entity.FChargeRecordEntity;
import com.feel.modules.app.entity.FUserEntity;
import com.feel.modules.app.entity.FUserScoreEntity;
import com.feel.modules.app.service.ScoreService;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.feel.modules.app.dao.BAddressUsdtDao;
import com.feel.modules.app.entity.BAddressUsdtEntity;
import com.feel.modules.app.service.BAddressUsdtService;
import org.springframework.transaction.annotation.Transactional;


@Service("bAddressUsdtService")
@Slf4j
public class BAddressUsdtServiceImpl extends ServiceImpl<BAddressUsdtDao, BAddressUsdtEntity> implements BAddressUsdtService {

    @Autowired
    private BAddressUsdtDao addressUsdtDao;

    @Autowired
    private FUserDao fUserDao;

    @Autowired
    private FUserScoreDao fUserScoreDao;

    @Autowired
    private ScoreService scoreDService;

    @Autowired
    private FChargeRecordDao fChargeRecordDao;

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

                JSONObject result = client.doGet(url, param);
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
            return list.get(0).getAddress();
        }
        return null;
    }

    @Override
    public R chargeCoin(ArrayList list) {
        if (list != null && !list.isEmpty()) {
            log.info("冲币量 ===",list.size());

            for (int i = 0 ; i < list.size() ; i++) {
                try {
                    chargeUsdtCoin((Map<String, Object>) list.get(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Transactional
    void chargeUsdtCoin(Map<String, Object> bean) {
        String toaddress = (String) bean.get("address");
        String hash = (String) bean.get("txid");
        BigDecimal number = new BigDecimal((String) bean.get("amount"));

        FUserEntity user = fUserDao.selectOne(new QueryWrapper<FUserEntity>().eq("address_usdt", toaddress));
        if(null != user) {
            BigDecimal usdtRate = new BigDecimal(SysPropertyUtils.getInstance().getProp("charge_rate"));
            BigDecimal score = number.multiply(usdtRate);

            FChargeRecordEntity fChargeRecordEntity = new FChargeRecordEntity.Builder()
                    .account(toaddress)
                    .createTime(TimeUtils.getDateNow())
                    .hash(hash)
                    .number(number)
                    .state(2)
                    .score(score)
                    .userId(user.getId())
                    .build();
            int insert = fChargeRecordDao.insert(fChargeRecordEntity);

            if(insert == 1) {
                FUserScoreEntity fUserScoreEntity = fUserScoreDao.selectOne(new QueryWrapper<FUserScoreEntity>().eq("user_id",user.getId()));
                //用户到账
                scoreDService.updateScore(fUserScoreEntity,score, OperateType.ADD ,ScoreType.CHARGE_COIN, ScoreStatus.SUCC_IN_CHARGE_USABLE, ScoreRemark.CHARGE_COIN);
            }

        }

    }


}

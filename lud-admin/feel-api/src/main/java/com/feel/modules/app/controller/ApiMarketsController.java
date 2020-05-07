package com.feel.modules.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.feel.common.utils.HttpClientHelper;
import com.feel.common.utils.R;
import com.feel.common.utils.SysPropertyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zz
 * @Description:
 * @Date: 6:11 AM 9/10/19
 * @Modified By
 */
@RestController
@RequestMapping("/api/f")
@Api(tags="行情接口")
@Slf4j
public class ApiMarketsController {

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "行情接口", notes = "行情接口")
    public R list(){
        HttpClientHelper client = new HttpClientHelper();
        try {
            Map<String,Object> param = new HashMap<>();
            param.put("market","LUD_USDT");
            JSONObject market_lud_url = client.doGet(SysPropertyUtils.getInstance().getProp("market_lud_url"), param);
            param.put("market","ETH_USDT");
            JSONObject market_eth_url = client.doGet(SysPropertyUtils.getInstance().getProp("market_eth_url"), param);
            return R.ok().put("lud", market_lud_url.getJSONObject("data")).put("eth",market_eth_url.getJSONObject("data"));
        } catch (IOException e) {
            log.error("market interface request error~!");
        }
        return R.error("market interface request error~!");

    }





}

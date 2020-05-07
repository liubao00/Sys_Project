package com.feel.common.utils;

import com.alibaba.fastjson.JSON;
import com.feel.common.utils.sms.MD5;
import com.feel.common.utils.sms.SSLClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liaijun
 * @createTime 19/8/27 下午9:50
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
public class SendMsg {
    public static final String charset = "utf-8";
    // 用户平台API账号(非登录账号,示例:N1234567)
    public static String account = "N6641776";
    // 用户平台API密码(非登录密码)
    public static String password = "Hfa9AxK02";

    private static CloseableHttpClient httpclient;
    /**
     * 手机发送短信息
     * @param phone
     * @return
     */
    public static String message1(String phone,String code) throws UnsupportedEncodingException {
        //请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
        String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";
        // 短信内容
        String msg = "【LUD】您好,你的验证码是"+code;

        //状态报告
        String report= "true";

        SmsSendRequest smsSingleRequest = new SmsSendRequest(account, password, msg, phone,report);

        String requestJson = JSON.toJSONString(smsSingleRequest);

        //System.out.println("before request string is: " + requestJson);

        String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);

        System.out.println("response after request result is :" + response);

        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);

        System.out.println("response  toString is :" + smsSingleResponse);
        return smsSingleResponse.toString();
    }
    public static void message(String mobile,String code){
        try {
            httpclient = SSLClient.createSSLClientDefault();
            //接口地址
            String url = "https://dx.ipyy.net/sms.aspx";
            //用户ID。
            String userid = "";
            //用户账号名
            String account = "ZZ00273";
            //接口密码
            String password = "ZZ0027200";
            //多个手机号用逗号分隔
            //String mobile = "13774317852";
            String text = "【OKCEX】您正在进行注册操作,验证码为:"+code+"。如非本人操作,请忽略。";
            String sendTime = "";
            //扩展号，没有请留空
            String extno = "";
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-type", "application/x-www-form-urlencoded");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("action", "send"));
            nvps.add(new BasicNameValuePair("userid", userid));
            nvps.add(new BasicNameValuePair("account", account));
            nvps.add(new BasicNameValuePair("password", MD5.GetMD5Code(password)));
            nvps.add(new BasicNameValuePair("mobile", mobile));
            nvps.add(new BasicNameValuePair("content", text));
            nvps.add(new BasicNameValuePair("sendTime", sendTime));
            nvps.add(new BasicNameValuePair("extno", extno));
            post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            HttpResponse response = httpclient.execute(post);
            HttpEntity entity = response.getEntity();
            String returnString = EntityUtils.toString(entity);
            System.out.println(returnString);
            EntityUtils.consume(entity);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

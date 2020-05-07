/*
 *  HTTPS接口发送短信
 */
package com.feel.common.utils.sms;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SendViaAspx {
	private static CloseableHttpClient httpclient;
	public final static void main(String[] args) throws Exception {
		httpclient = SSLClient.createSSLClientDefault();
		//接口地址
		String url = "https://dx.ipyy.net/sms.aspx";
		//用户ID。
		String userid="";
		//用户账号名
		String account="ZZ00273";
		//接口密码
		String password="ZZ0027200";
		//多个手机号用逗号分隔
		String mobile="13774317852";
		String text = "【OKCEX】您正在进行注册操作,验证码为:8888。如非本人操作,请忽略。";
		String sendTime="";
		//扩展号，没有请留空
		String extno="";
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-type", "application/x-www-form-urlencoded");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("action","send"));
		nvps.add(new BasicNameValuePair("userid", userid));
		nvps.add(new BasicNameValuePair("account", account));
		nvps.add(new BasicNameValuePair("password", MD5.GetMD5Code(password)));
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("content", text));
		nvps.add(new BasicNameValuePair("sendTime", sendTime));
		nvps.add(new BasicNameValuePair("extno", extno));
		post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
		HttpResponse response = httpclient.execute(post);
		HttpEntity entity = response.getEntity();
		String returnString=EntityUtils.toString(entity);
		System.out.println(returnString);
		EntityUtils.consume(entity);
	}



}
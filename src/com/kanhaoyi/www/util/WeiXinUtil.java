package com.kanhaoyi.www.util;

import java.net.URLEncoder;

import com.alibaba.fastjson.JSONObject;

public class WeiXinUtil {

	public static String appid = "wxf46eb8128a00b10d"; // 
	public static String appSecret = "08ef8a13ce13849738d9b3c2e57954b6"; // 微信签名
	
	public static void main(String[] args) {
		String code = "061WFXzm15Zr9p0i5Uym1BwmAm1WFXz1";
		String getTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
		String getTokenParam = "appid="+WeiXinUtil.appid+"&secret="+WeiXinUtil.appSecret+"&code="+code+"&grant_type=authorization_code";
		String tokenResult = HTTPUtil.httpsRequest(getTokenUrl, "GET", getTokenParam);
		System.out.println(tokenResult);
		
		JSONObject tokenJsonResult = JSONObject.parseObject(tokenResult);
		System.out.println(tokenJsonResult);
	}
	
}

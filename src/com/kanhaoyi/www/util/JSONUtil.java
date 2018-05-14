package com.kanhaoyi.www.util;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;

public class JSONUtil {

	/**
	 * @desctiption ajax返回的结果拼接
	 * @author zhuziming
	 * @time 2018年4月18日下午2:59:24
	 */
	public static String returnJson(String success, String msg){
		JSONObject jo = new JSONObject();
		jo.put("success", success);
		if(!StringUtils.isNullOrEmpty(msg)){
			jo.put("msg", msg);
		}
		return jo.toString();
	}
}

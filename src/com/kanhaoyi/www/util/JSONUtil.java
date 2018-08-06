package com.kanhaoyi.www.util;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;

public class JSONUtil {

	/**
	 * @desctiption ajax返回的结果拼接
	 * @author zhuziming
	 * @param success 返回状态[1:成功][2:失败][3:异常]
	 * @param msg 返回内容
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
	/**
	 * @desctiption ajax返回的结果拼接
	 * @author zhuziming
	 * @param success 返回状态[1:成功][2:失败][3:异常]
	 * @param msg 返回内容
	 * @param param 返回的参数
	 * @time 2018年4月18日下午2:59:24
	 */
	public static String returnJson(String success, String msg,JSONObject param){
		JSONObject jo = new JSONObject();
		jo.put("success", success);
		if(!StringUtils.isNullOrEmpty(msg)){
			jo.put("msg", msg);
		}
		if(param!=null){
			jo.put("param", param);
		}
		return jo.toString();
	}
}

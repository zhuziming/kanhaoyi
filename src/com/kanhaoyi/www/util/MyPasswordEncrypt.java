package com.kanhaoyi.www.util;

import org.apache.shiro.codec.Base64;

/**
 * @discription 密码的加密类
 * @author zhuziming
 * @time 2018年4月27日上午11:31:37
 */
public class MyPasswordEncrypt {
	// 盐值
	private static final String SALT = "d3d3Lmthbmhhb3lpLmNvbQ==";
	// 加密密码
	public static String encryptPassword(String password){
		return new MD5Code().getMD5ofStr(password + "{{"+SALT+"}}");
	}
	
	
	public static void main(String[] args) {
		// 对域名进行base64后，与密码拼接后在md5加密
		String val = "www.kanhaoyi.com";
		System.out.println(new String(Base64.encode(val.getBytes())));
	}
}

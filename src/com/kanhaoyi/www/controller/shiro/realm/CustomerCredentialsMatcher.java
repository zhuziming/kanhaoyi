package com.kanhaoyi.www.controller.shiro.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.kanhaoyi.www.util.MyPasswordEncrypt;


/**
 * @discription 密码加密后 要重写shiro中密码的证认方法
 * @author zhuziming
 * @time 2018年4月27日下午2:41:35
 */
public class CustomerCredentialsMatcher extends SimpleCredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		// 取得原始的输入数据信息
		Object tokenCredentials = MyPasswordEncrypt.encryptPassword(super.toString(token.getCredentials())).getBytes();
		// 取得认证数据库中的数据
		Object accountCredentials = super.getCredentials(info) ;
		return super.equals(tokenCredentials, accountCredentials);
	}
	
}

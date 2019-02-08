package com.kanhaoyi.www.model;

import org.apache.shiro.authc.UsernamePasswordToken;

public class WeiXinToken extends UsernamePasswordToken {

	private String username;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return this.getPassword();
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return this.username;
	}
	
}

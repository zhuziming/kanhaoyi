package com.kanhaoyi.www.model;

import java.sql.Timestamp;

public class User {
	
	private Integer id;
	private String account; //  账号 长度20
	private String nickname; // 昵称 长度20
	private String password; // 密码 长度40
	private String phone;    //手机号 长度11
	private String email;  //email 长度255
	private String picture;//   照片 长度255
	private String sex;		//  性别 长度1
	private Integer infoNum;    // 消息数量
	private Timestamp time; //  时间
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getInfoNum() {
		return infoNum;
	}
	public void setInfoNum(Integer infoNum) {
		this.infoNum = infoNum;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
}

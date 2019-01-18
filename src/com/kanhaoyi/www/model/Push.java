package com.kanhaoyi.www.model;

import java.sql.Timestamp;

/**
 * @description 推广记录
 * @author zhuziming
 * @time 2018年12月27日 下午3:40:51
 */
public class Push {

	private Integer id;
	private Integer userID; // 用户id
	private String ip; // ip地址
	private Timestamp time; // 浏览时间
	private Integer lottery; // 奖劢的一次抽奖[0:默认，表示未抽奖][1:已使用]
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Integer getLottery() {
		return lottery;
	}
	public void setLottery(Integer lottery) {
		this.lottery = lottery;
	}
	
}

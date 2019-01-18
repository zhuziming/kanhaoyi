package com.kanhaoyi.www.model;

import java.sql.Timestamp;

/**
 * 
 * @description 奖品兑换记录
 * @author zhuziming
 * @time 2018年12月25日 下午4:50:36
 */
public class PrizeExchangeRecord {
	
	private Integer id;
	private Integer userID; // 用户id
	private Integer prizeRecordID; // 奖品记录id
	private String prizeRecordName; // 奖品记录名字
	private Timestamp createTime; // 创建时间
	
	
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
	public Integer getPrizeRecordID() {
		return prizeRecordID;
	}
	public void setPrizeRecordID(Integer prizeRecordID) {
		this.prizeRecordID = prizeRecordID;
	}
	public String getPrizeRecordName() {
		return prizeRecordName;
	}
	public void setPrizeRecordName(String prizeRecordName) {
		this.prizeRecordName = prizeRecordName;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}

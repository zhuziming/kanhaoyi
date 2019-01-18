package com.kanhaoyi.www.model;

import java.sql.Timestamp;

/**
 * 
 * @description 中奖记录
 * @author zhuziming
 * @time 2018年12月25日 下午3:45:50
 */
public class PrizeRecord {
	private Integer id;
	private Integer userID; // 用户id
	private String prizeName; // 奖品名字
	private Timestamp createTime; // 创建时间
	private Integer status; // 使用[0:默认值，未兑换][1:已兑换]
	private Integer type; // 类型 [0:1金币][1:2金币][3:50元优惠券][4:拿心]
	
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
	public String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}

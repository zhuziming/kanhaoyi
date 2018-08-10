package com.kanhaoyi.www.model;

/**
 * 
 * @discription 用户  角色 关联表
 * @author zhuziming
 * @time 2018年8月10日下午3:04:40
 */
public class UserRole {

	private Integer id;
	private Integer uid; // 用户id
	private Integer rid; // 角色id
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
}

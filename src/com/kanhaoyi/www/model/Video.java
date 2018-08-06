package com.kanhaoyi.www.model;

import java.sql.Timestamp;

/**
 * @description 上传视频
 * @author zhuziming
 * @time 2018年6月9日 上午11:36:22
 */
public class Video {
	private Integer id;
	private String name; // 用户起的名字
	private String letterName; // 系统生成的名字 UUID
	private Integer accountID; // 用户id
	private Integer groupID; // 属于那个组
	private String remove; // 是否删除 [0:未删除，初始值] [1:删除]
	private Timestamp createTime; // 创建时间
	
	
	private String groupName; // 组的名字，数据库中没有这一列，为页面显示而添加
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLetterName() {
		return letterName;
	}
	public void setLetterName(String letterName) {
		this.letterName = letterName;
	}
	public Integer getAccountID() {
		return accountID;
	}
	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}
	public Integer getGroupID() {
		return groupID;
	}
	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}
	public String getRemove() {
		return remove;
	}
	public void setRemove(String remove) {
		this.remove = remove;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}

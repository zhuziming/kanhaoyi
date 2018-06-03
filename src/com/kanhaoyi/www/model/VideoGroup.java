package com.kanhaoyi.www.model;


/**
 * @description 视频组<br/>
 * 用户如果上传视频过多，展示时不方便，可以存入不同的组来减少视频的展示
 * @author zhuziming
 * @time 2018年6月2日 下午6:27:14
 */
public class VideoGroup {
	private Integer id;
	private String groupName; // 组的名字
	private Integer userID; // 用户id
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
}

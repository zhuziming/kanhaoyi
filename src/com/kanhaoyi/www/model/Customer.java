package com.kanhaoyi.www.model;

import java.sql.Timestamp;

/**
 * @description 客服类，记录课程与用户之间的客服时间
 * @author zhuziming
 * @time 2018年12月8日 下午5:32:18
 */
public class Customer {

	private Integer id;
	private Integer userID; // 用户id
	private Integer courseID; // 课程id
	private Timestamp createTime; // 数据创建时间
	private Timestamp removeTime; // 数据删除时间
	private Timestamp beginTime; // 开始时间
	private Timestamp endTime; // 结束时间
	private Integer cancel; // 是否取消[0:默认，不取消][1:取消]
	
	
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
	public Integer getCourseID() {
		return courseID;
	}
	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getRemoveTime() {
		return removeTime;
	}
	public void setRemoveTime(Timestamp removeTime) {
		this.removeTime = removeTime;
	}
	public Timestamp getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Integer getCancel() {
		return cancel;
	}
	public void setCancel(Integer cancel) {
		this.cancel = cancel;
	}
	
	
	
}

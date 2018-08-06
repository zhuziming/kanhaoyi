package com.kanhaoyi.www.model;

import java.sql.Timestamp;

/**
 * @discription 课程
 * @author zhuziming
 * @time 2018年4月17日下午2:47:40
 */
public class Course {

	private Integer id;
	private String courseName; // 课程名字
	private Integer courseTypeID; // 课程类型id
	private Integer userID;  // 作者id
	private Timestamp time; // 创建时间
	private Integer clickVolume; // 点击量
	private String picturePath; // 封面地址
	private String coursePath; // 课程页面地址，记录课程详情里，第一个课程的地址
	private Integer quantity; // 数量，有几集
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getCourseTypeID() {
		return courseTypeID;
	}
	public void setCourseTypeID(Integer courseTypeID) {
		this.courseTypeID = courseTypeID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Integer getClickVolume() {
		return clickVolume;
	}
	public void setClickVolume(Integer clickVolume) {
		this.clickVolume = clickVolume;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String getCoursePath() {
		return coursePath;
	}
	public void setCoursePath(String coursePath) {
		this.coursePath = coursePath;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}

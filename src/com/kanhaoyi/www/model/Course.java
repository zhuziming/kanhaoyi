package com.kanhaoyi.www.model;

import java.sql.Timestamp;

/**
 * @discription 课程
 * @author zhuziming
 * @time 2018年4月17日下午2:47:40
 */
public class Course {

	private int id;
	private String courseName; // 课程名字
	private int courseTypeID; // 课程类型id
	private String courseType; // 课程类型
	private int userID;  // 作者id
	private String userNickname; // 作者名称
	private Timestamp time; // 创建时间
	private int clickVolume; // 点击量
	private String picturePath; // 封面地址
	private String coursePath; // 课程页面地址，记录课程详情里，第一个课程的地址
	private int quantity; // 数量，有几集
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCourseTypeID() {
		return courseTypeID;
	}
	public void setCourseTypeID(int courseTypeID) {
		this.courseTypeID = courseTypeID;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getClickVolume() {
		return clickVolume;
	}
	public void setClickVolume(int clickVolume) {
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}

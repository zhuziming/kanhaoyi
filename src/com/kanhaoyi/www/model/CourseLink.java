package com.kanhaoyi.www.model;

import java.sql.Timestamp;

/**
 * @description 课程下方的网页链接
 * @author zhuziming
 * @time 2018年10月28日 下午3:49:43
 */
public class CourseLink {

	private Integer id;
	private Integer courseID; // 课程id
	private String intro; // 链接简介
	private String webLocation; // 网页链接地址
	private Integer picture; // 图片名称 一个课程目前只对应4个外链，所以图片的名字为1234
	private String format; // 图片格式
	private Timestamp createTime; // 创建时间
	private Timestamp endTime; // 到期时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCourseID() {
		return courseID;
	}
	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getWebLocation() {
		return webLocation;
	}
	public void setWebLocation(String webLocation) {
		this.webLocation = webLocation;
	}
	public Integer getPicture() {
		return picture;
	}
	public void setPicture(Integer picture) {
		this.picture = picture;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
}

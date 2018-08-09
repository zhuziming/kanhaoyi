package com.kanhaoyi.www.model;

import java.sql.Timestamp;

/**
 * @discription 课程详情
 * @author zhuziming
 * @time 2018年4月17日下午2:58:53
 */
public class CourseDetail {

	private Integer id;
	private Integer courseID; // 课程id
	private String courseDetailName; // 这一集的名字
	private String coursePath; // 页面地址
	private Integer videoID; // mp4文件 id
	private Integer sequence; // 课程展示顺序
	private Integer clickVolume; // 点击量
	private Timestamp createTime; // 创建时间
	
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
	public String getCourseDetailName() {
		return courseDetailName;
	}
	public void setCourseDetailName(String courseDetailName) {
		this.courseDetailName = courseDetailName;
	}
	public String getCoursePath() {
		return coursePath;
	}
	public void setCoursePath(String coursePath) {
		this.coursePath = coursePath;
	}
	public Integer getVideoID() {
		return videoID;
	}
	public void setVideoID(Integer videoID) {
		this.videoID = videoID;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Integer getClickVolume() {
		return clickVolume;
	}
	public void setClickVolume(Integer clickVolume) {
		this.clickVolume = clickVolume;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}

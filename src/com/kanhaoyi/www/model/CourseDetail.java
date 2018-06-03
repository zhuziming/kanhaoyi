package com.kanhaoyi.www.model;


/**
 * @discription 课程详情
 * @author zhuziming
 * @time 2018年4月17日下午2:58:53
 */
public class CourseDetail {

	private Integer id;
	private Integer courseID; // 课程id
	private String courseName; // 课程名字
	private String coursePath; // 页面地址
	private String videoPath; // mp4文件地址
	private Integer sequence; // 课程展示顺序
	private Integer clickVolume; // 点击量
	
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
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCoursePath() {
		return coursePath;
	}
	public void setCoursePath(String coursePath) {
		this.coursePath = coursePath;
	}
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
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

}

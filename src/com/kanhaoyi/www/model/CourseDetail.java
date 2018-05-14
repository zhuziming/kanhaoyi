package com.kanhaoyi.www.model;


/**
 * @discription 课程详情
 * @author zhuziming
 * @time 2018年4月17日下午2:58:53
 */
public class CourseDetail {

	private int id;
	private int courseID; // 课程id
	private String courseName; // 课程名字
	private String coursePath; // 页面地址
	private String videoPath; // mp4文件地址
	private int sequence; // 课程展示顺序
	private int clickVolume; // 点击量
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
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
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public int getClickVolume() {
		return clickVolume;
	}
	public void setClickVolume(int clickVolume) {
		this.clickVolume = clickVolume;
	}
}

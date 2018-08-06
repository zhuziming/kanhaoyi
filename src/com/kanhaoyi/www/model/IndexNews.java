package com.kanhaoyi.www.model;


/**
 * @description 首页新闻轮播图
 * @author zhuziming
 * @time 2018年7月29日 下午5:16:05
 */
public class IndexNews {

	private Integer id; // 
	private Integer courseID; // 课程id
	private String img;  // 图片路径
	private String title; // 新闻标题
	private String context; // 新闻内容
	
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	
}

package com.kanhaoyi.www.model;

import java.sql.Timestamp;

/**
 * @description 课程评论表
 * @author zhuziming
 * @time 2018年7月22日 下午5:01:18
 */
public class CourseComment {

	private Integer id;
	private Integer courseID; // 课程id
	private Integer userID; // 用户id
	private String content; // 评论内容
	private Integer praise; // 赞美数量
	private Timestamp time; //  时间
	
	
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
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getPraise() {
		return praise;
	}
	public void setPraise(Integer praise) {
		this.praise = praise;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
}

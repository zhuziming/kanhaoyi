package com.kanhaoyi.www.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 
 * @description 课程评论“赞”表
 * @author zhuziming
 * @time 2018年7月28日 下午6:32:25
 */
public class CourseCommentPraise {

	private Integer id; 
	private Integer userID; // 用户id
	private Integer commentID; // 评论id
	private Timestamp time; // 赞美时间
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
	public Integer getCommentID() {
		return commentID;
	}
	public void setCommentID(Integer commentID) {
		this.commentID = commentID;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}

	
	
}

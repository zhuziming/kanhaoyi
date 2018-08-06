package com.kanhaoyi.www.service;

import java.util.List;
import java.util.Map;

import com.kanhaoyi.www.model.CourseComment;

public interface ICourseCommentService {

	public int insert(CourseComment courseComment);
	/**
	 * @description 取最新评论，跟据课程id
	 * @author zhuziming
	 * @time 2018年7月22日 下午6:24:58
	 * @param courseID 课程id
	 * @param commentID 从那一个消息开始取，可以为null
	 * @param size 取几条，如不传默认5条
	 * @return
	 */
	public List<Map<String,Object>> getListByCourseID(Integer courseID,Integer commentID,Integer size);
	
	/**
	 * @description 把评论数据转为html
	 * @author zhuziming
	 * @time 2018年7月28日 上午11:58:20
	 * @param map
	 * @return
	 */
	public StringBuffer getHtml(List<Map<String,Object>> list);
	/**
	 * 
	 * @description 取赞最多的评论
	 * @author zhuziming
	 * @time 2018年7月28日 下午12:04:03
	 * @param courseID 课程id
	 * @param size 取几条，如不传默认5条
	 * @return
	 */
	public List<Map<String,Object>> getListByCourseIDPraise(Integer courseID,Integer size);
	/**
	 * 
	 * @description 跟据id取评论
	 * @author zhuziming
	 * @time 2018年7月28日 下午12:42:22
	 * @param CourseCommentId
	 * @return
	 */
	public Map<String,Object> getOneByCourseCommentId(Integer courseCommentId);
	
	/**
	 * @description 评论加1
	 * @author zhuziming
	 * @time 2018年7月28日 下午5:42:54
	 * @param commentID 评论id
	 * @return
	 */
	public int praiseAdd(Integer commentID);
	/**
	 * @description 评论减1
	 * @author zhuziming
	 * @time 2018年7月28日 下午7:27:59
	 * @param commentID
	 * @return
	 */
	public int praiseMinus(Integer commentID);
	
}

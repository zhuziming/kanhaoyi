package com.kanhaoyi.www.dao;

import java.util.List;
import java.util.Map;

import com.kanhaoyi.www.model.CourseComment;

public interface ICourseCommentDao {

	public int insert(CourseComment courseComment);
	
	/**
	 * @description 跟据课程id取最新数据
	 * @author zhuziming
	 * @time 2018年7月28日 上午10:05:31
	 * @param courseID 课程id
	 * @param size 取几条
	 * @return
	 */
	public List<Map<String,Object>> getListByCourseID(Map<String,Integer> map);
	/**
	 * @description 跟据课程id与 评论id取之后的数据
	 * @author zhuziming
	 * @time 2018年7月28日 上午10:05:57
	 * @param courseID 课程id
	 * @param commentID 评论id
	 * @param size 取几条
	 * @return
	 */
	public List<Map<String,Object>> getListByCourseIDAndCommentID(Map<String,Integer> map);
	
	/**
	 * @description 跟据课程id取赞最多的评论
	 * @author zhuziming
	 * @time 2018年7月28日 下午12:08:52
	 * @param courseID 课程id
	 * @param size 取几条
	 * @return
	 */
	public List<Map<String,Object>> getListByCourseIDPraise(Map<String,Integer> map);
	
	/**
	 * @description 跟据id取评论，返回map用于在评论展示
	 * @author zhuziming
	 * @time 2018年7月28日 下午12:43:53
	 * @param CourseCommentId 评论id
	 * @return
	 */
	public Map<String, Object> getOneByCourseCommentId(Integer courseCommentId);
	
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

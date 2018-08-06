package com.kanhaoyi.www.service;

import java.util.List;

import com.kanhaoyi.www.model.CourseCommentPraise;

public interface ICourseCommentPraiseService {
	
	/**
	 * @description 取列表，跟据用户id 评论id
	 * @author zhuziming
	 * @time 2018年7月28日 下午6:55:57
	 * @return
	 */
	public List<CourseCommentPraise> selectListByUserIdAndCommentId(CourseCommentPraise courseCommentPraise);
	/**
	 * @description 插入一条新记录
	 * @author zhuziming
	 * @time 2018年7月28日 下午6:56:26
	 * @param courseCommentPraise
	 * @return
	 */
	public int insert(CourseCommentPraise courseCommentPraise);
	/**
	 * @description 删除赞，跟据用户id 评论id
	 * @author zhuziming
	 * @time 2018年7月28日 下午6:56:43
	 * @param courseCommentPraise
	 * @return
	 */
	public int deleteById(Integer id);
	
}

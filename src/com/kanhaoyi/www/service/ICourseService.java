package com.kanhaoyi.www.service;

import java.util.List;

import com.kanhaoyi.www.model.Course;

public interface ICourseService {
	public int insert(Course course);
	
	public int update(Course course);
	
	public Course getOneByID(Integer id);
	
	public List<Course> getListByUserID(Integer userID);
	/**
	 * @description 生成课程网页
	 * @author zhuziming
	 * @time 2018年7月29日 下午3:42:41
	 * @param course
	 * @return
	 */
	public boolean createCourseHtml(Course course);
}

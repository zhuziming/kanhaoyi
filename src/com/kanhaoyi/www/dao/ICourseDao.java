package com.kanhaoyi.www.dao;

import java.util.List;
import java.util.Map;

import com.kanhaoyi.www.model.Course;

public interface ICourseDao {

	public int insert(Course course);
	
	public int update(Course course);
	
	public Course getOneByID(Integer id);
	
	public List<Course> getListByUserID(Integer userID);
	/**
	 * @desctiption 跟据课程类型id查询列表，并排序
	 * @author zhuziming
	 * @param map [courseTypeID:课程类型id][link:列名称][sort:排序 DESC | ASC][num:取几条]
	 * @time 2018年8月7日上午10:14:39
	 */
	public List<Course> getListByCourseTypeID(Map<String, Object> map);
	/**
	 * @desctiption 跟据列名排序取相应的条数
	 * @author zhuziming
	 * @param map [link:列名称][sort:排序 DESC | ASC][num:取几条]
	 * @time 2018年8月8日上午11:18:07
	 */
	public List<Course> getListByLinkSort(Map<String, Object> map);
}

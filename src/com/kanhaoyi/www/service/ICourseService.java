package com.kanhaoyi.www.service;

import java.util.List;
import java.util.Map;

import com.kanhaoyi.www.model.Course;

public interface ICourseService {
	public int insert(Course course);
	
	public int update(Course course);
	
	public Course getOneByID(Integer id);
	
	public List<Course> getListByUserID(Integer userID);
	
	/**
	 * @desctiption 跟据课程类型id查询列表，并排序
	 * @author zhuziming
	 * @param courseTypeID:课程类型id
	 * @param link:列名称
	 * @param sort:排序 DESC | ASC
	 * @param num :取几条
	 * @time 2018年8月7日上午10:14:39
	 */
	public List<Course> getListByCourseTypeID(Integer courseTypeID,String link, String sort,Integer num);
	
	/**
	 * @desctiption 跟据列名排序取相应的条数
	 * @author zhuziming
	 * @param link:列名称
	 * @param sort:排序 DESC | ASC
	 * @param begin:开始的条数
	 * @param end:结束的条数
	 * @time 2018年8月8日上午11:14:19
	 */
	public List<Course> getListByLinkSort(String link, String sort,Integer begin,Integer end);
	
	/**
	 * @description 生成课程网页
	 * @author zhuziming
	 * @time 2018年7月29日 下午3:42:41
	 * @param course
	 * @return
	 */
	public boolean createCourseHtml(Course course);
}

package com.kanhaoyi.www.service;

import java.util.List;
import java.util.Map;

import com.kanhaoyi.www.model.CourseDetail;

public interface ICourseDetailService {
	
	public int insert(CourseDetail courseDetail);
	
	public int update(CourseDetail courseDetail);
	
	/**
	 * @description 取集合跟据课程id，并对sequence列进行排序
	 * @author zhuziming
	 * @time 2018年7月22日 下午3:41:16
	 * @param courseID 课程id
	 * @param orderBy 排序DESC | ASC
	 * @return
	 */
	public List<CourseDetail> getListByCourseIdAndSequence(Integer courseID, String orderBy); 
	/**
	 * @description 取排序后的第一个
	 * @author zhuziming
	 * @time 2018年7月21日 下午12:28:26
	 * @param colums 排序的列名
	 * @param orderBy 排序的规则 desc | asc
	 * @return
	 */
	public CourseDetail getOneOrderBy(String colums, String orderBy, String courseID);
	/**
	 * @description 取一个，跟据id
	 * @author zhuziming
	 * @time 2018年7月22日 下午3:49:32
	 * @param id
	 * @return
	 */
	public CourseDetail getOneById(Integer id);
	
	/**
	 * @description 得到列表 
	 * @author zhuziming
	 * @time 2020年10月12日 下午3:41:57
	 * @param limit 得到几条
	 * @return
	 */
	public List<CourseDetail> getListByLimit(String limit);
	
}

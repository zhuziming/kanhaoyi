package com.kanhaoyi.www.dao;

import java.util.List;
import java.util.Map;

import com.kanhaoyi.www.model.CourseDetail;

public interface ICourseDetailDao {

	public int insert(CourseDetail courseDetail);
	
	public int update(CourseDetail courseDetail);
	
	public List<CourseDetail> getListByCourseIdAndSequence(Map<String,Object> map);
	
	public CourseDetail getOneOrderBy(Map<String,String> map);
	
	public CourseDetail getOneById(Integer id);
}

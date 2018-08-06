package com.kanhaoyi.www.dao;

import java.util.List;

import com.kanhaoyi.www.model.Course;

public interface ICourseDao {

	public int insert(Course course);
	
	public int update(Course course);
	
	public Course getOneByID(Integer id);
	
	public List<Course> getListByUserID(Integer userID);
}

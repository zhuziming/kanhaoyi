package com.kanhaoyi.www.dao;

import java.util.List;

import com.kanhaoyi.www.model.CourseType;

public interface ICourseTypeDao {

	public List<CourseType> getAll();
	
	public CourseType getOneByID(Integer id);
}

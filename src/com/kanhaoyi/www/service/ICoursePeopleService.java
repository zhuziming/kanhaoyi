package com.kanhaoyi.www.service;

import java.util.List;

import com.kanhaoyi.www.model.Course;
import com.kanhaoyi.www.model.CoursePeople;
import com.kanhaoyi.www.service.father.IServiceFather;

public interface ICoursePeopleService extends IServiceFather<CoursePeople> {

	public List<Course> getCourseListByPeoplePartID(Integer peoplePartID);
	
	public int deleteByCourseID(Integer courseID);
	
}

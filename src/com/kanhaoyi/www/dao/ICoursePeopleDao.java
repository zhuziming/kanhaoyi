package com.kanhaoyi.www.dao;

import java.util.List;

import com.kanhaoyi.www.dao.father.IDaoFather;
import com.kanhaoyi.www.model.Course;
import com.kanhaoyi.www.model.CoursePeople;

public interface ICoursePeopleDao extends IDaoFather<CoursePeople> {
	
	public List<Course> getCourseListByPeoplePartID(Integer peoplePartID);
	
	public int deleteByCourseID(Integer courseID);

}

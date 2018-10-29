package com.kanhaoyi.www.service;

import java.util.List;

import com.kanhaoyi.www.model.CourseLink;
import com.kanhaoyi.www.service.father.IServiceFather;

public interface ICourseLinkService extends IServiceFather<CourseLink> {
	
	public List<CourseLink> getListByCourseID(Integer courseID);

}

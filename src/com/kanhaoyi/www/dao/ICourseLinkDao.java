package com.kanhaoyi.www.dao;

import java.util.List;

import com.kanhaoyi.www.dao.father.IDaoFather;
import com.kanhaoyi.www.model.CourseLink;

public interface ICourseLinkDao extends IDaoFather<CourseLink> {

	public List<CourseLink> getListByCourseID(Integer courseID);
}

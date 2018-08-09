package com.kanhaoyi.www.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.ICourseTypeDao;
import com.kanhaoyi.www.model.Course;
import com.kanhaoyi.www.model.CourseType;
import com.kanhaoyi.www.service.ICourseService;
import com.kanhaoyi.www.service.ICourseTypeService;
import com.kanhaoyi.www.util.FreeMarkerUtil;

/**
 * @description 科室类别service
 * @author zhuziming
 * @time 2018年7月8日 下午12:47:17
 */
@Service("courseTypeServiceImpl")
public class CourseTypeServiceImpl implements ICourseTypeService {

	@Resource
	private ICourseTypeDao iCourseTypeDao;
	
	
	
	@Override
	public List<CourseType> getAll() {
		return iCourseTypeDao.getAll();
	}

	@Override
	public CourseType getOneByID(Integer id) {
		return iCourseTypeDao.getOneByID(id);
	}

	
}

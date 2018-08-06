package com.kanhaoyi.www.service;

import java.util.List;

import com.kanhaoyi.www.model.CourseType;

public interface ICourseTypeService {

	/**
	 * @description 获取全部的科室
	 * @author zhuziming
	 * @time 2018年7月8日 下午3:38:30
	 * @return
	 */
	public List<CourseType> getAll();
	
	/**
	 * @description 获取一个跟据id
	 * @author zhuziming
	 * @time 2018年7月15日 下午5:14:13
	 * @return
	 */
	public CourseType getOneByID(Integer id);
	
}

package com.kanhaoyi.www.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.ICourseDetailDao;
import com.kanhaoyi.www.model.CourseDetail;
import com.kanhaoyi.www.service.ICourseDetailService;

@Service("courseDetailServiceImpl")
public class CourseDetailServiceImpl implements ICourseDetailService {

	@Resource
	private ICourseDetailDao courseDetailDao;
	
	@Override
	public int insert(CourseDetail courseDetail) {
		return courseDetailDao.insert(courseDetail);
	}

	@Override
	public int update(CourseDetail courseDetail) {
		return courseDetailDao.update(courseDetail);
	}

	@Override
	public List<CourseDetail> getListByCourseIdAndSequence(Integer courseID, String orderBy) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("courseID", courseID);
		map.put("orderBy", orderBy);
		return courseDetailDao.getListByCourseIdAndSequence(map);
	}

	@Override
	public CourseDetail getOneOrderBy(String colums, String orderBy,String courseID) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("colums", colums);
		map.put("orderBy", orderBy);
		map.put("courseID", courseID);
		return courseDetailDao.getOneOrderBy(map);
	}

	@Override
	public CourseDetail getOneById(Integer id) {
		return courseDetailDao.getOneById(id);
	}

	@Override
	public List<CourseDetail> getListByLimit(String limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("limit", limit);
		return courseDetailDao.getListByLimit(map);
	}

}

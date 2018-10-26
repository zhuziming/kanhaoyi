package com.kanhaoyi.www.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.ICourseDao;
import com.kanhaoyi.www.model.Course;
import com.kanhaoyi.www.model.CourseDetail;
import com.kanhaoyi.www.model.CourseType;
import com.kanhaoyi.www.model.Video;
import com.kanhaoyi.www.service.ICourseCommentService;
import com.kanhaoyi.www.service.ICourseDetailService;
import com.kanhaoyi.www.service.ICourseService;
import com.kanhaoyi.www.service.ICourseTypeService;
import com.kanhaoyi.www.service.IVideoService;
import com.kanhaoyi.www.util.FreeMarkerUtil;

@Service("courseServiceImpl")
public class CourseServiceImpl implements ICourseService {

	@Resource
	private ICourseDao courseDao;

	
	@Override
	public int insert(Course course) {
		return courseDao.insert(course);
	}

	@Override
	public int update(Course course) {
		return courseDao.update(course);
	}

	@Override
	public Course getOneByID(Integer id) {
		return courseDao.getOneByID(id);
	}

	@Override
	public List<Course> getListByUserID(Integer userID) {
		return courseDao.getListByUserID(userID);
	}

	@Override
	public Integer getCountByUserID(Integer userID) {
		return this.courseDao.getCountByUserID(userID);
	}
	
	@Override
	public List<Map> getListByUserIDLeftCourseType(Integer userID,String link, String sort,Integer pageCount,Integer pageIndex) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userID", userID);
		map.put("link", link);
		map.put("sort", sort);
		map.put("pageCount", pageCount);
		map.put("pageIndex", pageIndex);
		return this.courseDao.getListByUserIDLeftCourseType(map);
	}
	public List<Map> getListLeftCourseType(String link, String sort,Integer pageCount,Integer pageIndex) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("link", link);
		map.put("sort", sort);
		map.put("pageCount", pageCount);
		map.put("pageIndex", pageIndex);
		return this.courseDao.getListLeftCourseType(map);
	}
	
	
	@Override
	public List<Course> getListByCourseTypeID(Integer courseTypeID,String link, String sort) {
		Map<String ,Object> map = new HashMap<String, Object>();
		map.put("courseTypeID", courseTypeID);
		map.put("link", link);
		map.put("sort", sort);
		List<Course> list = courseDao.getListByCourseTypeID(map);
		return list;
	}

	@Override
	public List<Course> getListByLinkSort(String link, String sort, Integer begin, Integer end) {
		Map<String ,Object> map = new HashMap<String, Object>();
		map.put("link", link);
		map.put("sort", sort);
		map.put("begin", begin);
		map.put("end", end);
		List<Course> list = courseDao.getListByLinkSort(map);
		return list;
	}

	@Override
	public List<Course> getAll() {
		return courseDao.getAll();
	}

	@Override
	public Integer getCountAll() {
		return courseDao.getCountAll();
	}

	@Override
	public int deleteByID(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Course selectByID(int id) {
		return courseDao.getOneByID(id);
	}

	@Override
	public int addClickVolume(int courseID) {
		return courseDao.addClickVolume(courseID);
	}

	
}

package com.kanhaoyi.www.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.ICourseLinkDao;
import com.kanhaoyi.www.model.CourseLink;
import com.kanhaoyi.www.service.ICourseLinkService;

@Service("courseLinkServiceImpl")
public class CourseLinkServiceImpl implements ICourseLinkService {

	@Resource
	private ICourseLinkDao courseLinkDao;
	
	@Override
	public int insert(CourseLink t) {
		// TODO Auto-generated method stub
		return courseLinkDao.insert(t);
	}

	@Override
	public int deleteByID(int id) {
		// TODO Auto-generated method stub
		return courseLinkDao.deleteByID(id);
	}

	@Override
	public int update(CourseLink t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CourseLink selectByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseLink> getListByLinkSort(String link, String sort, Integer pageCount, Integer pageIndex) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("link", link);
		map.put("sort", sort);
		map.put("pageCount", pageCount);
		map.put("pageIndex", pageIndex);
		return this.courseLinkDao.getListByLinkSort(map);
	}

	@Override
	public Integer getCountAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseLink> getListByCourseID(Integer courseID) {
		return courseLinkDao.getListByCourseID(courseID);
	}

}

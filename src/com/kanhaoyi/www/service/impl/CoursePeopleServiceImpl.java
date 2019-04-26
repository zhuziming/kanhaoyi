package com.kanhaoyi.www.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.ICoursePeopleDao;
import com.kanhaoyi.www.model.Course;
import com.kanhaoyi.www.model.CoursePeople;
import com.kanhaoyi.www.service.ICoursePeopleService;

@Service("coursePeopleServiceImpl")
public class CoursePeopleServiceImpl implements ICoursePeopleService {

	@Resource
	private ICoursePeopleDao coursePeopleDao;
	
	@Override
	public int insert(CoursePeople t) {
		// TODO Auto-generated method stub
		return this.coursePeopleDao.insert(t);
	}

	@Override
	public int deleteByID(int id) {
		// TODO Auto-generated method stub
		return this.coursePeopleDao.deleteByID(id);
	}

	@Override
	public int update(CoursePeople t) {
		// TODO Auto-generated method stub
		return this.coursePeopleDao.update(t);
	}

	@Override
	public CoursePeople selectByID(int id) {
		// TODO Auto-generated method stub
		return this.coursePeopleDao.selectByID(id);
	}

	@Override
	public List<CoursePeople> getListByLinkSort(String link, String sort, Integer begin, Integer end) {
		Map<String ,Object> map = new HashMap<String, Object>();
		map.put("link", link);
		map.put("sort", sort);
		map.put("begin", begin);
		map.put("end", end);
		return this.coursePeopleDao.getListByLinkSort(map);
	}

	@Override
	public List<Course> getCourseListByPeoplePartID(Integer peoplePartID) {
		return this.coursePeopleDao.getCourseListByPeoplePartID(peoplePartID);
	}

	@Override
	public Integer getCountAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByCourseID(Integer courseID) {
		return coursePeopleDao.deleteByCourseID(courseID);
	}

}

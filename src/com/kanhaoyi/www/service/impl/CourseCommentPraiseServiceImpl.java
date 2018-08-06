package com.kanhaoyi.www.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.ICourseCommentPraiseDao;
import com.kanhaoyi.www.model.CourseCommentPraise;
import com.kanhaoyi.www.service.ICourseCommentPraiseService;

@Service("courseCommentPraiseServiceImpl")
public class CourseCommentPraiseServiceImpl implements ICourseCommentPraiseService {

	@Resource
	private ICourseCommentPraiseDao courseCommentPraiseDao;
	
	@Override
	public List<CourseCommentPraise> selectListByUserIdAndCommentId(CourseCommentPraise courseCommentPraise) {
		return courseCommentPraiseDao.selectListByUserIdAndCommentId(courseCommentPraise);
	}

	@Override
	public int insert(CourseCommentPraise courseCommentPraise) {
		return courseCommentPraiseDao.insert(courseCommentPraise);
	}

	@Override
	public int deleteById(Integer id) {
		return courseCommentPraiseDao.deleteById(id);
	}

}

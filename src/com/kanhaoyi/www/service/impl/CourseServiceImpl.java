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
	@Resource
	private ICourseTypeService courseTypeService;
	@Resource
	private ICourseDetailService courseDetailService;
	@Resource
	private ICourseCommentService courseCommentService;
	@Resource
	private IVideoService viceoService;
	
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
	public boolean createCourseHtml(Course course) {
		try{
			// 生成html页面
			// 准备数据 课程
			Course course_ = this.getOneByID(course.getId());
			// 课程类型列表，网页中导航部分用
			List<CourseType> courseTypeList_ = courseTypeService.getAll();
			// 当前课程类型，面包屑导航用
			CourseType courseType_ = courseTypeService.getOneByID(course.getCourseTypeID());
			// 课程详情列表，右则课程列表显示用
			List<CourseDetail> courseDetailList_ = courseDetailService.getListByCourseIdAndSequence(course.getId(), "ASC");
			// 当前课程，为了对右则课程列表高亮显示
			//CourseDetail courseDetail_ = courseDetailService.getOneById(courseDetail.getId());
			// 得到最多的赞评论 5条
			List<Map<String,Object>> list_ = courseCommentService.getListByCourseIDPraise(Integer.valueOf(course.getId()), 5);
			// 把赞评论转为html
			StringBuffer GoodPraise_ = courseCommentService.getHtml(list_); // 赞最多的评论
			
			for(CourseDetail courseDetail_2 : courseDetailList_){
				Video video_ = viceoService.getOneByID(courseDetail_2.getVideoID());
				
				// 生成网页
				FreeMarkerUtil.createCourseHTML(courseDetail_2, courseDetailList_, 
						courseTypeList_, courseType_, course_, list_, GoodPraise_,video_);
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public List<Course> getListByCourseTypeID(Integer courseTypeID,String link, String sort,Integer num) {
		Map<String ,Object> map = new HashMap<String, Object>();
		map.put("courseTypeID", courseTypeID);
		map.put("link", link);
		map.put("sort", sort);
		map.put("num", num);
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



}

package com.kanhaoyi.www.controller.front;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kanhaoyi.www.model.Course;
import com.kanhaoyi.www.service.ICourseService;
import com.kanhaoyi.www.util.InitUtil;


/**
 * @description 前台首页的所有方法类
 * @author zhuziming
 * @time 2018年11月4日 上午11:56:44
 */
@Controller
@RequestMapping("/frontIndex")
public class IndexController {

	@Resource
	private ICourseService courseService;
	
	/**
	 * @description 首页课程查询
	 * @author zhuziming
	 * @time 2018年11月4日 下午12:02:21
	 * @return
	 */
	@RequestMapping("/indexQueryCourse.action")
	public String indexQueryCourse(Model model,String keyword){
		keyword = keyword.replace("", "%");
		List<Course> courseList = courseService.vagueQueryByCourseName(keyword);
		
		InitUtil.iniSystem(model);
		model.addAttribute("courseList",courseList);
		return "front/template-vague-query";
	}
	
}

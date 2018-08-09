package com.kanhaoyi.www.controller.manage;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kanhaoyi.www.model.Course;
import com.kanhaoyi.www.model.CourseType;
import com.kanhaoyi.www.model.PeoplePart;
import com.kanhaoyi.www.service.ICourseService;
import com.kanhaoyi.www.service.ICourseTypeService;
import com.kanhaoyi.www.service.IPeoplePartService;
import com.kanhaoyi.www.util.FreeMarkerUtil;
import com.kanhaoyi.www.util.InitUtil;
import com.kanhaoyi.www.util.JSONUtil;


/**
 * @discription 后台管理
 * @author zhuziming
 * @time 2018年8月7日上午9:44:33
 */
@Controller
@RequestMapping("/manage")
public class ManagerIndex {
	
	@Resource
	private ICourseTypeService courseTypeService;
	@Resource
	private ICourseService courseService;
	@Resource
	private IPeoplePartService peoplePartService;
	
	/**
	 * @desctiption 后台首页
	 * @author zhuziming
	 * @time 2018年8月7日上午9:44:47
	 */
	@RequestMapping("/index.action")
	public String index(Model model){
		InitUtil.iniSystem(model);
		return "manage/index";
	}
	/**
	 * @desctiption 系统页列表
	 * @author zhuziming
	 * @time 2018年8月7日上午9:44:58
	 */
	@RequestMapping("/systemList.action")
	public String systemList(Model model){
		
		List<CourseType> courseTypeList = courseTypeService.getAll();// 科室列表
		List<PeoplePart> peoplePartList = peoplePartService.getAll();// 部位列表
		model.addAttribute("courseTypeList",courseTypeList); 
		model.addAttribute("peoplePartList",peoplePartList);
		InitUtil.iniSystem(model);
		return "manage/systemList";
	}
	/**
	 * @desctiption 创建人体部位导航页，用于对二级导航页面的重新生成
	 * @author zhuziming
	 * @param peoplePartID : 人体部位id
	 * @time 2018年8月8日上午9:18:37
	 */
	@RequestMapping("/createPeoplePartNav.action")
	@ResponseBody
	public String createPeoplePartNav(Model model, String peoplePartID){
		try{
			// 当前人体部位
			PeoplePart peoplePart = peoplePartService.getOneByID(Integer.valueOf(peoplePartID));
			// 课程列表
			List<Course> courseList = new ArrayList<>();
			// 人体部位列表
			List<PeoplePart> peoplePartList = peoplePartService.getAll(); // 人体部位列表
			FreeMarkerUtil.createPeoplePartHTML(courseList, peoplePartList, peoplePart);
			return JSONUtil.returnJson("1", "生成完毕");
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("3", "异常了");
		}
	}
	
	
	/**
	 * @desctiption 创建课程类型导航页，用于对二级导航页面的重新生成
	 * @author zhuziming
	 * @param courseTypeID : 课程类型id
	 * @time 2018年8月7日上午9:59:13
	 */
	@RequestMapping("/createCourseTypeNav.action")
	@ResponseBody
	public String createCourseTypeNav(Model model, String courseTypeID){
		try{
			// 当前课程类型
			CourseType courseType = courseTypeService.getOneByID(Integer.valueOf(courseTypeID));
			// 课程列表
			List<Course> courseList = courseService.getListByCourseTypeID(courseType.getId(), "click_volume", "DESC",30);
			// 课程类型列表
			List<CourseType> courseTypeList = courseTypeService.getAll();
			FreeMarkerUtil.createCourseTypeHTML(courseList,courseTypeList,courseType);
			return JSONUtil.returnJson("1", "生成完毕");
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("3", "异常了");
		}
	}
	
	/**
	 * @desctiption 创建首页，每个导航栏目查询点击量最高的6个
	 * @author zhuziming
	 * @time 2018年8月7日下午2:45:22
	 */
	@RequestMapping("/createIndex.action")
	@ResponseBody
	public String createIndex(){
		try{
			// 查点击量最高的6个
			List<Course> maxClickList = courseService.getListByLinkSort("click_volume", "DESC", 0,6);
			// 查时间最新的6个
			List<Course> newTimeList  = courseService.getListByLinkSort("time", "DESC", 0,6);

			FreeMarkerUtil.createIndexHTML(maxClickList,newTimeList);
			return JSONUtil.returnJson("1", "生成完毕");
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("3", "异常了");
		}
	}
	
	/**
	 * @desctiption 课程页列表
	 * @author zhuziming
	 * @param pageIndex:页数
	 * @param pageSize:一页有几条
	 * @time 2018年8月8日下午2:59:00
	 */
	@RequestMapping("/courseList.action")
	public String courseList(Model model,Integer pageIndex,Integer pageSize){
		// 课程列表
		List<Course> courseList = courseService.getListByLinkSort("click_volume", "DESC", 0,6);
		model.addAttribute("courseList", courseList);
		InitUtil.iniSystem(model);
		return "manage/courseList";
	}
	
	
			
}

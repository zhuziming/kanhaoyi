package com.kanhaoyi.www.controller.front;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kanhaoyi.www.service.ICourseService;
import com.kanhaoyi.www.util.JSONUtil;

/**
 * @description 课程点击量类
 * @author zhuziming
 * @time 2018年10月25日 下午3:28:03
 */

@Controller
@RequestMapping("/frontCourse")
public class CourseClickVolumeController {

	@Resource
	private ICourseService courseService;
	
	/**
	 * @description 增加点击量
	 * @author zhuziming
	 * @time 2018年10月25日 下午3:31:23
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addClickVolume.action")
	public String AddClickVolume(int courseID){
		try{
			courseService.addClickVolume(courseID);
			return JSONUtil.returnJson("1", "");
		}catch(Exception e){
			return JSONUtil.returnJson("2", "");
		}
	}
	
}

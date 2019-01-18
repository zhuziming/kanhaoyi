package com.kanhaoyi.www.controller.front;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kanhaoyi.www.model.Course;
import com.kanhaoyi.www.service.ICourseService;
import com.kanhaoyi.www.util.JSONUtil;
import com.kanhaoyi.www.util.PropertiesUtil;

/**
 * 
 * @description 产品
 * @author zhuziming
 * @time 2019年1月1日 下午4:03:14
 */
@RequestMapping("/product")
@Controller
public class ProductController {

	@Resource
	private ICourseService courseService;
	
	/**
	 * 
	 * @description 取链接产品，课程列表
	 * @author zhuziming
	 * @time 2019年1月1日 下午4:06:01
	 * @return
	 */
	@RequestMapping("/getProductLinkList.action")
	@ResponseBody
	public String getProductList(){
		String imgpath = PropertiesUtil.getValue("system.properties", "imgpath");
		List<Course> maxClickList = courseService.getListByLinkSort("click_volume", "DESC", 24,0);
		StringBuffer sb = new StringBuffer();
		for (Course course : maxClickList) {
			sb.append("<div class=\"col-xl-2 col-lg-3 col-md-3 col-sm-4 col-6\">");
			sb.append("<div class=\"card mb-4 box-shadow\">");
			sb.append("<img class=\"card-img-top\" src=\""+imgpath+"/courseImg"+course.getPicturePath()+"\" alt=\"Card image cap\">");
			sb.append("<div class=\"card-body\">");
			sb.append("<p class=\"card-text\">"+course.getCourseName()+"</p>");
			sb.append("<div class=\"d-flex justify-content-between align-items-center\">");
			sb.append("<div class=\"btn-group\">");
			sb.append("<button type=\"button\" class=\"btn btn-sm btn-outline-secondary\">立即购买</button>");
			sb.append("</div>");
			sb.append("</div>");
			sb.append("</div>");
			sb.append("</div>");
			sb.append("</div>");
		}
		
		
		return JSONUtil.returnJson("1", sb.toString());
	}
	
}

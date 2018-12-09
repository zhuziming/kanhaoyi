package com.kanhaoyi.www.controller.back;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kanhaoyi.www.model.Essay;
import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.service.IEssayService;
import com.kanhaoyi.www.service.IUserService;
import com.kanhaoyi.www.util.InitUtil;
import com.kanhaoyi.www.util.PropertiesUtil;

/**
 * @discription 个人中心首页
 * @author zhuziming
 * @time 2018年4月28日下午1:38:22
 */
@Controller
@RequestMapping("/back")
public class PersonalIndex {
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IEssayService essayService;
	
	@RequestMapping("/index.action")
	public String index(Model model,HttpSession session){
		User user = userService.getSessionUser(session);
		
		Subject subject = SecurityUtils.getSubject();
		// 如果是老师，在页面中显示老师拥有的菜单
		if(subject.hasRole("teacher")){
			model.addAttribute("teacher","teacher");
		}
		// 如果是管理员，在页面中显示管理员拥有的菜单
		if(subject.hasRole("admin")){
			model.addAttribute("admin","admin");
		}
		// 如果是客服，在页面中显示客服拥有的菜单
		if(subject.hasRole("customerService")){
			model.addAttribute("customerService","customerService");
		}
				
		Essay essay = essayService.getRandom();
		model.addAttribute("user",user);  
		if(essay==null){
			model.addAttribute("essay","这里空空如也");
		}else{
			model.addAttribute("essay",essay.getEssay());
		}
		InitUtil.iniSystem(model);
		return "back/index";
	}
	
	
}

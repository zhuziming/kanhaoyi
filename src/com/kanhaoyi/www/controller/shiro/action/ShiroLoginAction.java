package com.kanhaoyi.www.controller.shiro.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.service.IUserService;
import com.kanhaoyi.www.util.InitUtil;
import com.kanhaoyi.www.util.JSONUtil;
import com.kanhaoyi.www.util.PropertiesUtil;

@Controller
public class ShiroLoginAction {
	
	@Resource
	private IUserService userService;
	
	/**
	 * @desctiption 登录页
	 * @author zhuziming
	 * @time 2018年4月28日上午9:12:21
	 */
	@RequestMapping("/loginUrl.action")
	public String loginUrl(Model model,HttpServletRequest request){
		System.out.println("登录页");
		// 取出抛出的异常
		Object exceptionObj = request.getAttribute("shiroLoginFailure");
		if(exceptionObj != null){
			if(IncorrectCredentialsException.class.getName().equals(exceptionObj.toString())){
				request.setAttribute("bugMsg", "密码错误");
			}else if(UnknownAccountException.class.getName().equals(exceptionObj.toString())){
				request.setAttribute("bugMsg", "该用户不存在");
			}
		}
		InitUtil.iniSystem(model);
		return "front/sign_in";
	}
	@RequestMapping("/loginPage.action")
	public String loginPage(Model model){
		InitUtil.iniSystem(model);
		return "front/sign_in";
	}
	
	/**
	 * @desctiption 登录出错页
	 * @author zhuziming
	 * @time 2018年4月28日上午9:12:33
	 */
	@RequestMapping("/unauthUrl")
	public ModelAndView unauthUrl(){
		System.out.println("登录出错页 /unauthUrl");
		return new ModelAndView("role");
	}
	/**
	 * @desctiption 登录成功
	 * @author zhuziming
	 * @time 2018年4月28日上午9:12:46
	 */
	@RequestMapping("/successUrl")
	public String successUrl(Model model, HttpSession session){
		System.out.println("登录成功跳转页");
		userService.getSessionUser(session);
		
		InitUtil.iniSystem(model);
		return "redirect:index.html";
	}
}

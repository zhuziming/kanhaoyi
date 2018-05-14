package com.kanhaoyi.www.controller.shiro.action;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.kanhaoyi.www.model.UserModel;
import com.kanhaoyi.www.service.UserService;
import com.kanhaoyi.www.util.JSONUtil;
import com.kanhaoyi.www.util.PropertiesUtil;

@Controller
public class ShiroLoginAction {
	
	@Resource
	private UserService userService;
	
	/**
	 * @desctiption 登录页
	 * @author zhuziming
	 * @time 2018年4月28日上午9:12:21
	 */
	@RequestMapping("/loginUrl.action")
	public String loginUrl(Model model){
		String indexpath = PropertiesUtil.getValue("system.properties", "indexpath");
		
		model.addAttribute("indexpath",indexpath);
		return "front/sign_in";
	}
	/**
	 * @desctiption 登录出错页
	 * @author zhuziming
	 * @time 2018年4月28日上午9:12:33
	 */
	@RequestMapping("/unauthUrl")
	public ModelAndView unauthUrl(){
		return new ModelAndView("role");
	}
	/**
	 * @desctiption 登录成功
	 * @author zhuziming
	 * @time 2018年4月28日上午9:12:46
	 */
	@RequestMapping("/successUrl")
	public String successUrl(Model model, HttpSession session){
		String indexpath = PropertiesUtil.getValue("system.properties", "indexpath");
		
		model.addAttribute("indexpath",indexpath);
		return "redirect:index.html";
	}
	/**
	 * @desctiption 得到用户名
	 * @author zhuziming
	 * @time 2018年4月28日上午10:18:22
	 */
	@RequestMapping("/getusername.action")
	@ResponseBody
	public String getUsername(HttpSession session){
		//ajax返回格式{success:'',msg:''}
		//success取值[1:成功][2:失败][3:异常]
		//		msg只有在success为2时，才有值
		
		Object nickname = SecurityUtils.getSubject().getPrincipal();
		if(nickname==null){
			return JSONUtil.returnJson("2", "");
		}else{
			return JSONUtil.returnJson("1", userService.getSessionNickname(session));
		}
	}
}

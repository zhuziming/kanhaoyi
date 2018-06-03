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
		String indexpath = PropertiesUtil.getValue("system.properties", "indexpath");
		
		model.addAttribute("indexpath",indexpath);
		return "front/sign_in";
	}
	@RequestMapping("/loginPage.action")
	public String loginPage(Model model){
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
		
		String indexpath = PropertiesUtil.getValue("system.properties", "indexpath");
		model.addAttribute("indexpath",indexpath);
		return "redirect:index.html";
	}
	/**
	 * @desctiption 得到昵称，消息数
	 * @author zhuziming
	 * @time 2018年4月28日上午10:18:22
	 */
	@RequestMapping("/getusername.action")
	@ResponseBody
	public String getUsername(HttpSession session){
		//ajax返回格式{success:'',msg:''}
		//success取值[1:成功][2:失败][3:异常]
		//		msg只有在success为2时，才有值
		
		Object account = SecurityUtils.getSubject().getPrincipal();
		if(account==null){
			return JSONUtil.returnJson("2", "");
		}else{
			String infoNum = userService.getSessionInfoNum(session); // 消息数
			String nickName= userService.getSessionNickname(session); // 昵称
			JSONObject jo = new JSONObject();
			jo.put("nickName", nickName);
			jo.put("infoNum", infoNum);
			return JSONUtil.returnJson("1", jo.toString());
		}
	}
}

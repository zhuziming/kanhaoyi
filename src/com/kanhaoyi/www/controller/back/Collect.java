package com.kanhaoyi.www.controller.back;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.service.IUserService;
import com.kanhaoyi.www.util.InitUtil;
import com.kanhaoyi.www.util.PropertiesUtil;

/**
 * @discription 收藏
 * @author zhuziming
 * @time 2018年4月28日下午2:07:27
 */
@Controller
@RequestMapping("/back")
public class Collect {
	
	@Resource
	private IUserService userService;

	@RequestMapping("/collect.action")
	public String collect(Model model,HttpSession session){
		String account = SecurityUtils.getSubject().getPrincipal().toString();
		String picture =null;
		try {
			User user = userService.getUserByAccount(account);
			picture = user.getPicture(); // 照片
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(picture==null){
			picture="default.jpg";
		}
		model.addAttribute("picture",picture);
		model.addAttribute("account",account); // 账户
		model.addAttribute("nickname",userService.getSessionNickname(session));  // 昵称
		InitUtil.iniSystem(model);
		return "back/collect";
	}
	
}

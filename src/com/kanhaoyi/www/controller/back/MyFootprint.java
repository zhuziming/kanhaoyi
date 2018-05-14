package com.kanhaoyi.www.controller.back;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kanhaoyi.www.model.UserModel;
import com.kanhaoyi.www.service.UserService;
import com.kanhaoyi.www.util.InitUtil;
import com.kanhaoyi.www.util.PropertiesUtil;

/**
 * @discription 我的足迹
 * @author zhuziming
 * @time 2018年4月28日下午2:00:29
 */
@Controller
@RequestMapping("/back")
public class MyFootprint {
	
	@Resource
	private UserService userService;
	
	
	@RequestMapping("/myFootprint.action")
	public String myFootprint(Model model,HttpSession session){
		String account = SecurityUtils.getSubject().getPrincipal().toString();
		String picture =null;
		try {
			UserModel user = userService.getUserByAccount(account);
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
		return "back/myFootprint";
	}

}

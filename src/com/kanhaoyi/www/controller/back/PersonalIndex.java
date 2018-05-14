package com.kanhaoyi.www.controller.back;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kanhaoyi.www.model.Essay;
import com.kanhaoyi.www.model.UserModel;
import com.kanhaoyi.www.service.IEssayService;
import com.kanhaoyi.www.service.UserService;
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
	private UserService userService;
	
	@Resource
	private IEssayService essayService;
	
	@RequestMapping("/index.action")
	public String index(Model model,HttpSession session){
		String account = SecurityUtils.getSubject().getPrincipal().toString();
		Essay essay = essayService.getRandom();
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
		if(essay==null){
			model.addAttribute("essay","这里空空如也");
		}else{
			model.addAttribute("essay",essay.getEssay());
		}
		InitUtil.iniSystem(model);
		return "back/index";
	}
	
	
}

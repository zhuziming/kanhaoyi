package com.kanhaoyi.www.controller.teacher;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.service.UserService;
import com.kanhaoyi.www.util.InitUtil;

/**
 * @discription 老师操作类
 * @author zhuziming
 * @time 2018年5月7日上午11:09:54
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Resource
	private UserService userService;
	
	
	@RequestMapping("/uploadVideoPage.action")
	public String uploadVideo(Model model,HttpSession session){
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
		return "teacher/uploadVideoPage";
	}
	
	@RequestMapping("/publishCoursePage.action")
	public String publishCourse(Model model,HttpSession session){
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
		return "teacher/publishCoursePage";
	}
	
	/**
	 * @desctiption 我的课程
	 * @author zhuziming
	 * @time 2018年5月10日下午5:38:00
	 */
	@RequestMapping("/myCoursePage.action")
	public String myCoursePage(Model model,HttpSession session){
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
		
		return "teacher/myCoursePage";
	}
	
	
}

package com.kanhaoyi.www.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kanhaoyi.www.util.InitUtil;

@Controller
@RequestMapping("/manage")
public class ManagerIndex {
	
	
	
	@RequestMapping("/index.action")
	public String index(Model model){
		
		
		
		InitUtil.iniSystem(model);
		return "manage/index";
	}
	
}

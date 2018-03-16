package com.kanhaoyi.www.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Test {
	// test.html
	@RequestMapping(value="/test.html") 
	public String test(Model model){
		model.addAttribute("name", "老谭");
		model.addAttribute("names", Arrays.asList("Tom", "Jack", "Rose"));
		return "test";
	}

}

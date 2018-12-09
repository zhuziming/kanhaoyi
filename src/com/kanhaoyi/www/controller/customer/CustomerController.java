package com.kanhaoyi.www.controller.customer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kanhaoyi.www.model.Customer;
import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.service.ICustomerService;
import com.kanhaoyi.www.service.IUserService;
import com.kanhaoyi.www.util.InitUtil;
import com.kanhaoyi.www.util.JSONUtil;


/**
 * @description 客服管理类
 * @author zhuziming
 * @time 2018年12月9日 下午4:23:08
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Resource
	private IUserService userService;
	@Resource
	private ICustomerService customerService;
	
	/**
	 * @description 客服首页
	 * @author zhuziming
	 * @time 2018年12月9日 下午4:29:42
	 * @return
	 */
	@RequestMapping("/indexPage.action")
	public String indexPage(Model model,HttpSession session){
		User user = userService.getSessionUser(session);
		List<Map> listMap = customerService.getMapListLeftCourseByUserID(user.getId());
		InitUtil.iniSystem(model);
		model.addAttribute("listMap", listMap);
		return "customer/customerIndex";
	}
	
	/**
	 * @description 前向客服页页面，开启socket连接
	 * @author zhuziming
	 * @time 2018年12月9日 下午5:20:39
	 * @return
	 */
	@RequestMapping("/customerSocket.action")
	public String customerSocket(Model model,HttpSession session,Integer courseID){
		User user = userService.getSessionUser(session);
		Customer customer = new Customer();
		customer.setCourseID(courseID);
		customer.setCancel(0);
		List<Customer> list = customerService.selectListByCourseIDAndCancel(customer);
		for (Customer customer2 : list) {
			if(customer2.getUserID()==user.getId()){
				InitUtil.iniSystem(model);
				model.addAttribute("courseID",courseID);
				return "customer/customerCourse";
			}
		}
		return JSONUtil.returnJson("2", "非法操作");
	}
	
}

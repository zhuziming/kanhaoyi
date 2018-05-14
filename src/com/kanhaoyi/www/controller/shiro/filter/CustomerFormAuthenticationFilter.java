package com.kanhaoyi.www.controller.shiro.filter;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * @discription shiro中用来检测验证码是否正确
 * @author zhuziming
 * @time 2018年4月27日下午2:52:56
 */
public class CustomerFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		System.out.println("** 验证码 **");
		// 1、如果要想取得在Session中出现的验证码，则必须取得HttpSession接口对象
		HttpServletRequest req = (HttpServletRequest) request ;
		HttpSession session = req.getSession() ;	// 取得当前的session对象
		String sessionCode = (String) session.getAttribute("code") ;	// 取得生成的验证码
		// 2、取得用户提交表单过来的验证码数据
		String code = request.getParameter("code") ;
		if (sessionCode == null || code == null || "".equals(sessionCode) || "".equals(code)) {
			request.setAttribute("code", "验证码不允许为空！");
			return true ;	// 拒绝访问，不再进行用户名或密码的检测
		} else {
			if (!code.equalsIgnoreCase(sessionCode)) {	// 验证码输入错误
				request.setAttribute("code", "验证码输入错误！");
				return true ;
			}
		}
		return super.onAccessDenied(request, response) ; // 操作继续向后执行
	}
	
}

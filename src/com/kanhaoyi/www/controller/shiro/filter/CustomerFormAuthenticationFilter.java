package com.kanhaoyi.www.controller.shiro.filter;


import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.service.IUserService;

/**
 * @discription shiro中用来检测验证码是否正确
 * @author zhuziming
 * @time 2018年4月27日下午2:52:56
 */
public class CustomerFormAuthenticationFilter extends FormAuthenticationFilter {

	@Resource
	private IUserService userService;
	
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
			request.setAttribute("bugMsg", "验证码不允许为空！");
			return true ;	// 拒绝访问，不再进行用户名或密码的检测
		} else {
			if (!code.equalsIgnoreCase(sessionCode)) {	// 验证码输入错误
				request.setAttribute("bugMsg", "验证码输入错误！");
				return true ;
			}
		}
		return super.onAccessDenied(request, response) ; // 操作继续向后执行
	}

	/**
	 * 
	 * @description 当登录成功后，把用户放入到session中<br/>
	 * 在记住我状态下，这个方法不会被执行，要写一个过滤器，如果shiro中有值，则放入session
	 * @author zhuziming
	 * @time 2018年6月9日 上午9:19:22
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		
		Object username = subject.getPrincipal(); // 得到登录名
		User user = userService.getUserByAccount(username.toString()); // 查询用户
		HttpSession session = ((HttpServletRequest) request).getSession(); // 得到session
		session.setAttribute("user", user); // 把用户放入session
		return super.onLoginSuccess(token, subject, request, response);
	}
	
	
	
}

package com.kanhaoyi.www.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * @description 针对js注入的过滤器
 * @author zhuziming
 * @time 2018年10月25日 下午12:07:43
 */
public class XSSFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try{
			String requestURI = ((HttpServletRequest)request).getServletPath();
			// 老师在修改详情页数据时，因为要插入html文本，所以这里不再过滤
			if("/teacher/modifyCourseDetail.action".hashCode() == requestURI.hashCode()
					&& "/teacher/modifyCourseDetail.action".equals(requestURI)){
				chain.doFilter(request, response);
			}else{
				chain.doFilter(new OnRequestWrap((HttpServletRequest)request), response);
			}
		}catch(Exception e){
			chain.doFilter(new OnRequestWrap((HttpServletRequest)request), response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

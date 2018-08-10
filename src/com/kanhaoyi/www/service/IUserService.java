package com.kanhaoyi.www.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.service.father.IServiceFather;



public interface IUserService extends IServiceFather<User> {

	/**
	 * @desctiption 用户是否存在
	 * @author zhuziming
	 * @return 存在：true | 不存在：false
	 * @time 2018年4月18日下午2:30:21
	 */
	public boolean selectUserIsExist(String account);
	
	
	/**
	 * @desctiption 此方法是留给realm进行认证使用的
	 * @author zhuziming
	 * @time 2018年4月24日下午2:34:27
	 */
	public User getUserByAccount(String account);
	
	/**
	 * @desctiption 此方法是留给realm进行授权处理的，返回所有的角色与权限
	 * @author zhuziming
	 * <li>key = allRoles, value = 所有的角色</li>
	 * <li>key = allActions, value = 所有的权限</li>
	 * @time 2018年4月24日下午2:37:14
	 */
	public Map<String ,Object> listAuthByAccount(String account) throws Exception;
	
	/**
	 * @desctiption 从session中取昵称
	 * @author zhuziming
	 * @time 2018年4月28日下午3:59:01
	 */
	public String getSessionNickname(HttpSession session);
	
	/**
	 * @description 从session中取消息数
	 * @author zhuziming
	 * @time2018年5月27日 下午6:04:43
	 */
	public String getSessionInfoNum(HttpSession session);

	/**
	 * @description 在session中取user
	 * @author zhuziming
	 * @time 2018年6月3日 下午4:27:22
	 */
	public User getSessionUser(HttpSession session);
	
}

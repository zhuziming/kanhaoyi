package com.kanhaoyi.www.dao;

import java.util.Set;

import com.kanhaoyi.www.dao.father.IDaoFather;
import com.kanhaoyi.www.model.User;

public interface IUserDao extends IDaoFather<User> {

	
	public int selectAccountCount(String account);
	
	/**
	 * @desctiption 跟据账户查询单个用户
	 * @author zhuziming
	 * @time 2018年4月27日上午11:19:02
	 */
	public User selectOneByAccount(String account);
	/**
	 * @desctiption shiro使用，跟据账户查询所有角色
	 * @author zhuziming
	 * @time 2018年4月27日上午11:17:11
	 */
	public Set<String> findAllRoleByAccount(String account);
	/**
	 * @desctiption shiro使用，跟据账户查询所有权限
	 * @author zhuziming
	 * @time 2018年4月27日上午11:17:39
	 */
	public Set<String> findAllActionByAccount(String account);
}

package com.kanhaoyi.www.dao;

import com.kanhaoyi.www.dao.father.IDaoFather;
import com.kanhaoyi.www.model.UserRole;

/**
 * 
 * @discription 用户 角色 关联 Dao
 * @author zhuziming
 * @time 2018年8月10日下午3:13:35
 */
public interface IUserRoleDao extends IDaoFather<UserRole> {

	public int deleteByUserID(Integer userID);
	
}

package com.kanhaoyi.www.service;

import com.kanhaoyi.www.model.UserRole;
import com.kanhaoyi.www.service.father.IServiceFather;

public interface IUserRoleService extends IServiceFather<UserRole> {

	public int deleteByUserID(Integer userID);
	
}

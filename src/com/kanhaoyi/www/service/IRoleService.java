package com.kanhaoyi.www.service;

import java.util.List;

import com.kanhaoyi.www.model.Role;
import com.kanhaoyi.www.service.father.IServiceFather;

public interface IRoleService extends IServiceFather<Role>{

	public List<Role> getAll();
	
}

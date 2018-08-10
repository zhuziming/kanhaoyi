package com.kanhaoyi.www.dao;

import java.util.List;

import com.kanhaoyi.www.dao.father.IDaoFather;
import com.kanhaoyi.www.model.Role;

/**
 * 
 * @discription 角色接口
 * @author zhuziming
 * @time 2018年8月10日上午11:32:06
 */
public interface IRoleDao extends IDaoFather<Role> {

	public List<Role> getAll();
	
}

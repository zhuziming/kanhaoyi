package com.kanhaoyi.www.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.IRoleDao;
import com.kanhaoyi.www.model.Role;
import com.kanhaoyi.www.service.IRoleService;

@Service("roleServiceImpl")
public class RoleServiceImpl implements IRoleService {

	@Resource
	private IRoleDao roleDao;
	
	@Override
	public int insert(Role t) {
		return this.roleDao.insert(t);
	}

	@Override
	public int deleteByID(int id) {
		return this.roleDao.deleteByID(id);
	}

	@Override
	public int update(Role t) {
		return this.roleDao.update(t);
	}

	@Override
	public Role selectByID(int id) {
		return this.roleDao.selectByID(id);
	}

	@Override
	public List<Role> getListByLinkSort(String link, String sort, Integer begin, Integer end) {
		Map<String ,Object> map = new HashMap<String, Object>();
		map.put("link", link);
		map.put("sort", sort);
		map.put("begin", begin);
		map.put("end", end);
		List<Role> list = roleDao.getListByLinkSort(map);
		return this.roleDao.getListByLinkSort(map);
	}

	@Override
	public List<Role> getAll() {
		return this.roleDao.getAll();
	}

	@Override
	public Integer getCountAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

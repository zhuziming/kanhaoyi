package com.kanhaoyi.www.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.IUserRoleDao;
import com.kanhaoyi.www.model.UserRole;
import com.kanhaoyi.www.service.IUserRoleService;

@Service("userRoleServiceImpl")
public class UserRoleServiceImpl implements IUserRoleService {

	@Resource
	private IUserRoleDao userRoleDao;
	
	@Override
	public int insert(UserRole t) {
		return this.userRoleDao.insert(t);
	}

	@Override
	public int deleteByID(int id) {
		return this.userRoleDao.deleteByID(id);
	}

	@Override
	public int update(UserRole t) {
		return this.userRoleDao.update(t);
	}

	@Override
	public UserRole selectByID(int id) {
		return this.userRoleDao.selectByID(id);
	}

	@Override
	public List<UserRole> getListByLinkSort(String link, String sort, Integer begin, Integer end) {
		Map<String ,Object> map = new HashMap<String, Object>();
		map.put("link", link);
		map.put("sort", sort);
		map.put("begin", begin);
		map.put("end", end);
		List<UserRole> list = userRoleDao.getListByLinkSort(map);
		return list;
	}

	@Override
	public int deleteByUserID(Integer userID) {
		return this.userRoleDao.deleteByUserID(userID);
	}

	@Override
	public Integer getCountAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

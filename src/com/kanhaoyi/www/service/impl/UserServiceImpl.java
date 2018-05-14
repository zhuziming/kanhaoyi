package com.kanhaoyi.www.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.UserDao;
import com.kanhaoyi.www.model.UserModel;
import com.kanhaoyi.www.service.UserService;


@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	@Override
	public UserModel selectById(int id) {
		return userDao.selectById(id);
	}

	@Override
	public int insert(UserModel userModel) {
		return userDao.insert(userModel);
	}

	@Override
	public boolean selectUserIsExist(String account) {
		int num = userDao.selectAccountCount(account);
		if(num > 0){
			return true;
		}
		return false;
	}

	@Override
	public UserModel getUserByAccount(String account) throws Exception {
		return userDao.selectOneByAccount(account);
	}

	@Override
	public Map<String, Object> listAuthByAccount(String account) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allRoles", this.userDao.findAllRoleByAccount(account));
		map.put("allActions", this.userDao.findAllActionByAccount(account));
		return map;
	}

	@Override
	public String getSessionNickname(HttpSession session) {
		// 1 session中是否有值
		if(session.getAttribute("nickname")==null){
			Object user = SecurityUtils.getSubject().getPrincipal();
			try {
				// 2 如果用户登录，则去查昵称
				if(user!=null){
					UserModel um = userDao.selectOneByAccount(user.toString());
					session.setAttribute("nickname", um.getNickname()==null?"未知":um.getNickname());
				}else{
					// 3 如果未登录，设昵称为未登录
					session.setAttribute("nickname", "未登录");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 4 返回昵称
		return String.valueOf(session.getAttribute("nickname"));
	}

}

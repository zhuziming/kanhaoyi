package com.kanhaoyi.www.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.IUserDao;
import com.kanhaoyi.www.model.Course;
import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.service.IUserService;


@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;
	
	@Override
	public User selectByID(int id) {
		return userDao.selectByID(id);
	}

	@Override
	public int insert(User userModel) {
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
	public User getUserByAccount(String account){
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
		User user = (User) session.getAttribute("user"); // 在session中取user对象
		if(user==null){ // 如果为空，则去数据库中查
			Object account = SecurityUtils.getSubject().getPrincipal();
			if(account!=null){
				User um = userDao.selectOneByAccount(account.toString());
				session.setAttribute("user", um);
			}else{
				return "未登录";
			}
		}
		User user_1 = (User) session.getAttribute("user");
		String nickname = user_1.getNickname();
		return String.valueOf(nickname);
	}
	

	@Override
	public String getSessionInfoNum(HttpSession session) {
		User user = (User) session.getAttribute("user"); // 在session中取user对象
		if(user==null){ // 如果为空，则去数据库中查
			Object account = SecurityUtils.getSubject().getPrincipal();
			if(account!=null){
				User um = userDao.selectOneByAccount(account.toString());
				session.setAttribute("user", um);
			}else{
				return "0";
			}
		}
		User user_1 = (User) session.getAttribute("user");
		Integer num = user_1.getInfoNum();
		return String.valueOf(num);
	}

	@Override
	public User getSessionUser(HttpSession session) {
		User user = (User) session.getAttribute("user"); // 在session中取user对象
		if(user==null){ // 如果为空，则去数据库中查
			Object account = SecurityUtils.getSubject().getPrincipal();
			if(account!=null){
				User um = userDao.selectOneByAccount(account.toString());
				session.setAttribute("user", um);
			}else{
				return null;
			}
		}
		return (User) session.getAttribute("user");
	}

	@Override
	public int deleteByID(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> getListByLinkSort(String link, String sort, Integer pageCount,Integer pageIndex) {
		Map<String ,Object> map = new HashMap<String, Object>();
		map.put("link", link);
		map.put("sort", sort);
		map.put("pageCount", pageCount);
		map.put("pageIndex", pageIndex);
		List<User> list = userDao.getListByLinkSort(map);
		return list;
	}

	@Override
	public Integer getCountAll() {
		return userDao.getCountAll();
	}


}

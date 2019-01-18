package com.kanhaoyi.www.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.IPushDao;
import com.kanhaoyi.www.model.Push;
import com.kanhaoyi.www.service.IPushService;

@Service("pushServiceImpl")
public class PushServiceImpl implements IPushService {

	@Resource
	private IPushDao pushDao;
	
	@Override
	public int insert(Push t) {
		// TODO Auto-generated method stub
		return pushDao.insert(t);
	}

	@Override
	public int deleteByID(int id) {
		// TODO Auto-generated method stub
		return pushDao.deleteByID(id);
	}

	@Override
	public int update(Push t) {
		// TODO Auto-generated method stub
		return pushDao.update(t);
	}

	@Override
	public Push selectByID(int id) {
		// TODO Auto-generated method stub
		return pushDao.selectByID(id);
	}

	@Override
	public List<Push> getListByLinkSort(String link, String sort, Integer pageCount, Integer pageIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCountAll() {
		// TODO Auto-generated method stub
		return pushDao.getCountAll();
	}

	@Override
	public int getCountByIP(String ip) {
		// TODO Auto-generated method stub
		return pushDao.getCountByIP(ip);
	}
	@Override
	public int getLotteryCountByUserID(Integer userID){
		return pushDao.getLotteryCountByUserID(userID);
	}
	@Override
	public Push getFirstOneByUserID(Integer userID){
		return pushDao.getFirstOneByUserID(userID);
	}
}

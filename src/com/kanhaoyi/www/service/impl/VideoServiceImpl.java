package com.kanhaoyi.www.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.IVideoDao;
import com.kanhaoyi.www.model.Video;
import com.kanhaoyi.www.service.IVideoService;

@Service("videoServiceImpl")
public class VideoServiceImpl implements IVideoService {

	@Resource
	private IVideoDao videoDao;
	
	@Override
	public int insert(Video videl) {
		return videoDao.insert(videl);
	}


	@Override
	public List<Video> getListByAccountIdAndGroupId(String accountID, String groupID) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("accoutID", accountID);
		map.put("groupID", groupID);
		return videoDao.getListByAccountIdAndGroupId(map);
	}


	@Override
	public List<Video> getListByAccountId(Integer accountID, Integer pageCount, Integer pageIndex) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accountID", accountID);
		map.put("pageCount", pageCount);
		map.put("pageIndex", pageIndex);
		return videoDao.getListByAccountId(map);
	}


	@Override
	public Integer getListCountByAccountId(Integer accountID) {
		return videoDao.getListCountByAccountId(accountID);
	}

}

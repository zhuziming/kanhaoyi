package com.kanhaoyi.www.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.IVideoGroupDao;
import com.kanhaoyi.www.model.VideoGroup;
import com.kanhaoyi.www.service.IVideoGroupService;

@Service("videoGroupServiceImpl")
public class VideoGroupServiceImpl implements IVideoGroupService {
	
	@Resource
	public IVideoGroupDao videoGroupDao;

	@Override
	public int insert(VideoGroup videoGroup) {
		return videoGroupDao.insert(videoGroup);
	}

	@Override
	public int deleteByID(int videoGroupID) {
		return videoGroupDao.deleteByID(videoGroupID);
	}

	@Override
	public List<VideoGroup> selectListByUserID(int userID) {
		return videoGroupDao.selectListByUserID(userID);
	}

	@Override
	public List<VideoGroup> selectList(VideoGroup videoGroup) {
		return videoGroupDao.selectList(videoGroup);
	}
	
}

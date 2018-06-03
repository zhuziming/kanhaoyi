package com.kanhaoyi.www.service;

import java.util.List;

import com.kanhaoyi.www.model.VideoGroup;

/**
 * @description 接口，视频组
 * @author zhuziming
 * @time 2018年6月2日 下午6:32:54
 */
public interface IVideoGroupService {
	public int insert(VideoGroup videoGroup);
	public int deleteByID(int videoGroupID);
	public List<VideoGroup> selectListByUserID(int userID);
	public List<VideoGroup> selectList(VideoGroup videoGroup);
}

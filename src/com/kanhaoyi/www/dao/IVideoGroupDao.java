package com.kanhaoyi.www.dao;

import java.util.List;

import com.kanhaoyi.www.model.VideoGroup;

/**
 * @description 视频组Dao
 * @author zhuziming
 * @time 2018年6月2日 下午6:37:02
 */
public interface IVideoGroupDao {
	public int insert(VideoGroup videoGroup);
	public int deleteByID(int videoGroupID);
	public List<VideoGroup> selectListByUserID(int userID);
	public List<VideoGroup> selectList(VideoGroup videoGroup);
}

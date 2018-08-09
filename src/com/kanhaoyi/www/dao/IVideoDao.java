package com.kanhaoyi.www.dao;

import java.util.List;
import java.util.Map;

import com.kanhaoyi.www.model.Video;

/**
 * @description 视频dao
 * @author zhuziming
 * @time 2018年6月10日 上午9:23:27
 */
public interface IVideoDao {
	public int insert(Video videl);
	public List<Video> getListByAccountIdAndGroupId(Map<String, String> map) ;
	public List<Video> getListByAccountId(Map<String, Object> map);
	public Integer getListCountByAccountId(Integer accountID);
	public Video getOneByID(Integer ID);
}

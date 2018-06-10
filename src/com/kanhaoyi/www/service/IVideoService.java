package com.kanhaoyi.www.service;

import java.util.List;

import com.kanhaoyi.www.model.Video;

public interface IVideoService {
	public int insert(Video video);
	/**
	 * @description 查询视频列表，通过用户id与组id
	 * @author zhuziming
	 * @time 2018年6月10日 上午9:28:58
	 * @param accoutID
	 * @param groupID
	 * @return
	 */
	public List<Video> getListByAccountIdAndGroupId(String accoutID, String groupID);
	
	/**
	 * @description 跟据用户得到所有视频
	 * @author zhuziming
	 * @time 2018年6月10日 下午6:07:36
	 * @param accountID 用户id
	 * @param pageCount 一页有几条数据 如果为空：10条
	 * @param pageIndex 页号 如果为空0页
	 * @return
	 */
	public List<Video> getListByAccountId(Integer accountID, Integer pageCount,Integer pageIndex);
	/**
	 * @description 跟据用户查询一共有几个视频，用于分页
	 * @author zhuziming
	 * @time 2018年6月10日 下午6:52:56
	 * @param accountID
	 * @return
	 */
	public Integer getListCountByAccountId(Integer accountID);
}

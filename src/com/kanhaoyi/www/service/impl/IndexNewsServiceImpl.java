package com.kanhaoyi.www.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.IIndexNewsDao;
import com.kanhaoyi.www.model.IndexNews;
import com.kanhaoyi.www.service.IIndexNewsService;

@Service("indexNewsServiceImpl")
public class IndexNewsServiceImpl implements IIndexNewsService {

	@Resource
	private IIndexNewsDao indexNewsDao;
	
	@Override
	public int insert(IndexNews t) {
		return indexNewsDao.insert(t);
	}

	@Override
	public int deleteByID(int id) {
		return indexNewsDao.deleteByID(id);
	}

	@Override
	public int update(IndexNews t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IndexNews selectByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IndexNews> getListByLinkSort(String link, String sort, Integer pageCount, Integer pageIndex) {
		Map<String ,Object> map = new HashMap<String, Object>();
		map.put("link", link);
		map.put("sort", sort);
		map.put("pageCount", pageCount);
		map.put("pageIndex", pageIndex);
		List<IndexNews> list = indexNewsDao.getListByLinkSort(map);
		return list;
	}

	@Override
	public Integer getCountAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

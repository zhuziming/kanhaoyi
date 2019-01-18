package com.kanhaoyi.www.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.IPrizeRecordDao;
import com.kanhaoyi.www.model.PrizeRecord;
import com.kanhaoyi.www.service.IPrizeRecordService;

@Service("prizeRecordServiceImpl")
public class PrizeRecordServiceImpl implements IPrizeRecordService {

	@Resource
	private IPrizeRecordDao prizeRecordDao;
	
	@Override
	public int insert(PrizeRecord t) {
		return prizeRecordDao.insert(t);
	}

	@Override
	public int deleteByID(int id) {
		// TODO Auto-generated method stub
		return prizeRecordDao.deleteByID(id);
	}

	@Override
	public int update(PrizeRecord t) {
		// TODO Auto-generated method stub
		return prizeRecordDao.update(t);
	}

	@Override
	public PrizeRecord selectByID(int id) {
		// TODO Auto-generated method stub
		return prizeRecordDao.selectByID(id);
	}

	@Override
	public List<PrizeRecord> getListByLinkSort(String link, String sort, Integer pageCount, Integer pageIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCountAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoggeryRecord(Integer userID) {
		// TODO Auto-generated method stub
		return prizeRecordDao.getLoggeryRecord(userID);
	}

	@Override
	public List<PrizeRecord> getListByUserID(Integer userID, String link, String sort, Integer pageCount,
			Integer pageIndex) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userID", userID);
		map.put("link", link);
		map.put("sort", sort);
		map.put("pageCount", pageCount);
		map.put("pageIndex", pageIndex);
		return prizeRecordDao.getListByUserID(map);
	}

}

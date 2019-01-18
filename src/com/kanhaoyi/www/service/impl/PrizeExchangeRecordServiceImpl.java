package com.kanhaoyi.www.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.IPrizeExchangeRecordDao;
import com.kanhaoyi.www.model.PrizeExchangeRecord;
import com.kanhaoyi.www.service.IPrizeExchangeRecordService;

@Service("prizeExchangeRecordServiceImpl")
public class PrizeExchangeRecordServiceImpl implements IPrizeExchangeRecordService {

	@Resource
	private IPrizeExchangeRecordDao prizeExchangeRecordDao;
	
	@Override
	public int insert(PrizeExchangeRecord t) {
		// TODO Auto-generated method stub
		return prizeExchangeRecordDao.insert(t);
	}

	@Override
	public int deleteByID(int id) {
		// TODO Auto-generated method stub
		return prizeExchangeRecordDao.deleteByID(id);
	}

	@Override
	public int update(PrizeExchangeRecord t) {
		// TODO Auto-generated method stub
		return prizeExchangeRecordDao.update(t);
	}

	@Override
	public PrizeExchangeRecord selectByID(int id) {
		// TODO Auto-generated method stub
		return prizeExchangeRecordDao.selectByID(id);
	}

	@Override
	public List<PrizeExchangeRecord> getListByLinkSort(String link, String sort, Integer pageCount, Integer pageIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCountAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

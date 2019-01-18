package com.kanhaoyi.www.dao;

import java.util.List;
import java.util.Map;

import com.kanhaoyi.www.dao.father.IDaoFather;
import com.kanhaoyi.www.model.PrizeRecord;

public interface IPrizeRecordDao extends IDaoFather<PrizeRecord> {

	public int getLoggeryRecord(Integer userID);
	
	public List<PrizeRecord> getListByUserID(Map<String, Object> map);
}

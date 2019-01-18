package com.kanhaoyi.www.service;

import java.util.List;

import com.kanhaoyi.www.model.PrizeRecord;
import com.kanhaoyi.www.service.father.IServiceFather;

public interface IPrizeRecordService extends IServiceFather<PrizeRecord> {
	/**
	 * @description 查看中奖记录
	 * @author zhuziming
	 * @time 2018年12月27日 下午1:08:52
	 * @return
	 */
	public int getLoggeryRecord(Integer userID);
	
	public List<PrizeRecord> getListByUserID(Integer userID,String link, String sort,Integer pageCount,Integer pageIndex);
}

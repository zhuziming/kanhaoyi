package com.kanhaoyi.www.service;


import com.kanhaoyi.www.model.Push;
import com.kanhaoyi.www.service.father.IServiceFather;

public interface IPushService extends IServiceFather<Push> {
	
	public int getCountByIP(String ip);

	public int getLotteryCountByUserID(Integer userID);
	
	public Push getFirstOneByUserID(Integer userID);
}

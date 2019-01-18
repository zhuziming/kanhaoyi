package com.kanhaoyi.www.dao;

import java.util.List;

import com.kanhaoyi.www.dao.father.IDaoFather;
import com.kanhaoyi.www.model.Push;

public interface IPushDao extends IDaoFather<Push> {

	public int getCountByIP(String ip);
	
	public int getLotteryCountByUserID(Integer userID);
	
	public Push getFirstOneByUserID(Integer userID);
}

package com.kanhaoyi.www.dao;

import com.kanhaoyi.www.model.Essay;

public interface IEssayDao {
	/**
	 * @desctiption 
	 * @author zhuziming
	 * @time 2018年4月28日下午3:09:40
	 */
	public Essay getOneRandom();
	public int getCount();
}

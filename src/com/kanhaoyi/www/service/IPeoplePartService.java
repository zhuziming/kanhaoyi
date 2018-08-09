package com.kanhaoyi.www.service;

import java.util.List;

import com.kanhaoyi.www.model.PeoplePart;

public interface IPeoplePartService {
	/**
	 * @desctiption 得到全部
	 * @author zhuziming
	 * @time 2018年8月8日上午9:03:42
	 */
	public List<PeoplePart> getAll();
	
	/**
	 * @desctiption 查询一个跟据id
	 * @author zhuziming
	 * @time 2018年8月8日上午9:33:01
	 */
	public PeoplePart getOneByID(Integer peoplePartID);
	
}

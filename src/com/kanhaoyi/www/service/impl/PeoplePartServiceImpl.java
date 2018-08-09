package com.kanhaoyi.www.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.IPeoplePartDao;
import com.kanhaoyi.www.model.PeoplePart;
import com.kanhaoyi.www.service.IPeoplePartService;

@Service("peoplePartServiceImpl")
public class PeoplePartServiceImpl implements IPeoplePartService {

	@Resource
	private IPeoplePartDao peoplePartDao;
	
	@Override
	public List<PeoplePart> getAll() {
		return peoplePartDao.getAll();
	}
	
	@Override
	public PeoplePart getOneByID(Integer peoplePartID) {
		return peoplePartDao.getOneByID(peoplePartID);
	}


}

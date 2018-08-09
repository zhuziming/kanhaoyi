package com.kanhaoyi.www.dao;

import java.util.List;

import com.kanhaoyi.www.model.PeoplePart;

public interface IPeoplePartDao {

	public List<PeoplePart> getAll();
	
	public PeoplePart getOneByID(Integer peoplePartID);
	
}

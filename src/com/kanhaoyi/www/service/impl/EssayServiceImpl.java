package com.kanhaoyi.www.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.EssayDao;
import com.kanhaoyi.www.model.Essay;
import com.kanhaoyi.www.service.IEssayService;

@Service("essayServiceImpl")
public class EssayServiceImpl implements IEssayService {

	@Resource
	private EssayDao essayDao;
	
	@Override
	public Essay getRandom() {
		return essayDao.getOneRandom();
	}

}

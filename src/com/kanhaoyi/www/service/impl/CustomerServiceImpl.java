package com.kanhaoyi.www.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.ICustomerDao;
import com.kanhaoyi.www.model.Course;
import com.kanhaoyi.www.model.Customer;
import com.kanhaoyi.www.service.ICustomerService;

@Service("customerServiceImpl")
public class CustomerServiceImpl implements ICustomerService {

	@Resource
	private ICustomerDao customerDao;
	
	
	@Override
	public int insert(Customer t) {
		// TODO Auto-generated method stub
		return customerDao.insert(t);
	}

	@Override
	public int deleteByID(int id) {
		// TODO Auto-generated method stub
		return customerDao.deleteByID(id);
	}

	@Override
	public int update(Customer t) {
		// TODO Auto-generated method stub
		return customerDao.update(t);
	}

	public List<Customer> selectListByCourseIDAndCancel(Customer customer){
		return customerDao.selectListByCourseIDAndCancel(customer);
	}
	
	@Override
	public Customer selectByID(int id) {
		// TODO Auto-generated method stub
		return customerDao.selectByID(id);
	}

	@Override
	public List<Customer> getListByLinkSort(String link, String sort, Integer pageCount, Integer pageIndex) {
		Map<String ,Object> map = new HashMap<String, Object>();
		map.put("link", link);
		map.put("sort", sort);
		map.put("pageCount", pageCount);
		map.put("pageIndex", pageIndex);
		List<Customer> list =customerDao.getListByLinkSort(map);
		return list;
	}

	@Override
	public Integer getCountAll() {
		// TODO Auto-generated method stub
		return customerDao.getCountAll();
	}
	
	public List<Map> getMapListLeftCourseByUserID(Integer userID){
		return customerDao.getMapListLeftCourseByUserID(userID);
	}
}

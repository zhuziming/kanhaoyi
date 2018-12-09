package com.kanhaoyi.www.dao;

import java.util.List;
import java.util.Map;

import com.kanhaoyi.www.dao.father.IDaoFather;
import com.kanhaoyi.www.model.Customer;

public interface ICustomerDao extends IDaoFather<Customer> {

	public List<Customer> selectListByCourseIDAndCancel(Customer customer);
	public List<Map> getMapListLeftCourseByUserID(Integer userID);
}

package com.kanhaoyi.www.service;

import java.util.List;
import java.util.Map;

import com.kanhaoyi.www.model.Customer;
import com.kanhaoyi.www.service.father.IServiceFather;

public interface ICustomerService extends IServiceFather<Customer>{

	
	/**
	 * @description 查询列表，跟据课程id 和 删除记录
	 * @author zhuziming
	 * @time 2018年12月9日 下午2:37:44
	 * @param customer
	 * @return
	 */
	public List<Customer> selectListByCourseIDAndCancel(Customer customer);
	/**
	 * 
	 * @description 跟据userID查询客服数据 左连 课程，返回Map
	 * @author zhuziming
	 * @time 2018年12月9日 下午4:53:20
	 * @param userID
	 * @return
	 */
	public List<Map> getMapListLeftCourseByUserID(Integer userID);
	
}

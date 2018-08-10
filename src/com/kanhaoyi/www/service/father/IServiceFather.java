package com.kanhaoyi.www.service.father;

import java.util.List;

import com.kanhaoyi.www.model.Course;
import com.kanhaoyi.www.model.User;

/**
 * @discription 所有接口的父接口，用于定义每个接口都要有的方法
 * @author zhuziming
 * @time 2018年8月10日上午9:22:44
 */
public interface IServiceFather<T> {

	
	public int insert(T t);
	
	public int deleteByID(int id);
	
	public int update(T t);
	
	public T selectByID(int id);
	
	
	/**
	 * @desctiption 跟据列名排序取相应的条数
	 * @author zhuziming
	 * @param link:列名称
	 * @param sort:排序 DESC | ASC
	 * @param begin:开始的条数
	 * @param end:结束的条数
	 * @time 2018年8月8日上午11:14:19
	 */
	public List<T> getListByLinkSort(String link, String sort,Integer begin,Integer end);
	
}

package com.kanhaoyi.www.dao.father;

import java.util.List;
import java.util.Map;

/**
 * @discription 所有Dao层的父接口，用于定义公共方法
 * @author zhuziming
 * @time 2018年8月10日上午9:54:36
 */
public interface IDaoFather<T> {
	
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
	public List<T> getListByLinkSort(Map<String, Object> map);
	
	public Integer getCountAll();
	
}

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
	 * @param link 列名称
	 * @param sort 排序 DESC | ASC
	 * @param pageCount 一页几条
	 * @param pageIndex 第几页
	 * @time 2018年8月8日上午11:14:19
	 */
	public List<T> getListByLinkSort(String link, String sort,Integer pageCount,Integer pageIndex);
	
	/**
	 * @description 取全部数据的条数
	 * @author zhuziming
	 * @time 2018年8月14日 下午2:39:31
	 * @return
	 */
	public Integer getCountAll();
	
}

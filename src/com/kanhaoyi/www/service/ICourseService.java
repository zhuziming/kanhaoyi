package com.kanhaoyi.www.service;

import java.util.List;
import java.util.Map;

import com.kanhaoyi.www.model.Course;
import com.kanhaoyi.www.service.father.IServiceFather;

public interface ICourseService extends IServiceFather<Course> {
	public int insert(Course course);
	
	public int update(Course course);
	
	public Course getOneByID(Integer id);
	
	/**
	 * @description 取数据条数，跟据用户id
	 * @author zhuziming
	 * @time 2018年8月13日 下午12:13:38
	 * @param userID 用户id
	 * @return 数据条数
	 */
	public Integer getCountByUserID(Integer userID);
	
	
	public List<Course> getListByUserID(Integer userID);
	
	
	/**
	 * @description 跟据用户id查课程 左连 课程类型，并分页
	 * @author zhuziming
	 * @time 2018年8月13日 下午12:01:19
	 * @param userID 用户id
	 * @param link 排序列名
	 * @param sort 排序规则[DESC][ASC]
	 * @param pageCount 一页有几条数据 如果为空：10条
	 * @param pageIndex 页号 如果为空0页
	 * @return
	 */
	public List<Map> getListByUserIDLeftCourseType(Integer userID,String link, String sort,Integer pageCount,Integer pageIndex);
	
	/**
	 * @description 查全部课程 左连 课程类型，并分页
	 * @author zhuziming
	 * @time 2018年8月13日 下午12:01:19
	 * @param link 排序列名
	 * @param sort 排序规则[DESC][ASC]
	 * @param pageCount 一页有几条数据 如果为空：10条
	 * @param pageIndex 页号 如果为空0页
	 * @return
	 */
	public List<Map> getListLeftCourseType(String link, String sort,Integer pageCount,Integer pageIndex);
	
	/**
	 * @description 查看一个课程 左连 课程类型
	 * @author zhuziming
	 * @time 2018年12月9日 下午12:18:00
	 * @param id
	 * @return
	 */
	public Map getOneLeftCourseTypeByID(Integer id);
	
	/**
	 * @desctiption 跟据课程类型id查询列表，并排序
	 * @author zhuziming
	 * @param courseTypeID:课程类型id
	 * @param link:列名称
	 * @param sort:排序 DESC | ASC
	 * @param num :取几条
	 * @time 2018年8月7日上午10:14:39
	 */
	public List<Course> getListByCourseTypeID(Integer courseTypeID,String link, String sort);
	
	
	/**
	 * @description 取全部数据
	 * @author zhuziming
	 * @time 2018年8月13日 下午7:44:31
	 * @return
	 */
	public List<Course> getAll();
	/**
	 * @description 增加课程点击量 +1
	 * @author zhuziming
	 * @time 2018年10月25日 下午3:35:48
	 * @return
	 */
	public int addClickVolume(int courseID);
	/**
	 * @description 模糊查询通过intro字段
	 * @author zhuziming
	 * @time 2018年11月4日 下午12:11:53
	 * @param keyword
	 * @return
	 */
	public List<Course> vagueQueryByIntro(String keyword);
	
}

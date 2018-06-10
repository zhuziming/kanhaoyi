package com.kanhaoyi.www.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 
 * @author zhuziming
 * @time 2018年6月10日 下午7:02:33
 */
public class PagingUtil {

	/**
	 * 
	 * @description 分页方法
	 * @author zhuziming
	 * @time 2018年6月10日 下午7:02:49
	 * @param pageIndex 当前第几页
	 * @param pageCount 一页几条数据
	 * @param dataCount 总条数
	 * @return  allPageSize,dataCount,pageIndex,pageCount
	 */
	public static Map<String,Integer> beginPaging(Integer pageIndex, Integer pageCount, Integer dataCount){
		Integer allPageSize = 0;
		// 如果部条数取模页条数为0
		if(dataCount % pageCount==0){
			allPageSize = dataCount / pageCount;
		}else{
			allPageSize = dataCount / pageCount + 1;
		}
		Map<String ,Integer> map = new HashMap<String, Integer>();
		// 一共有几页
		map.put("allPageSize", allPageSize);
		// 数据总条数
		map.put("dataCount", dataCount);
		// 当前第几页
		map.put("pageIndex", pageIndex);
		// 一页几条数据
		map.put("pageCount", pageCount);
		return map;
	}
}

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
	
	/**
	 * @description 分页，生成html文件<br/>
	 * 求出起始页，然后向后加5页
	 * @author zhuziming
	 * @time 2018年6月24日 下午5:50:53
	 * @param allPageSize 一共有几页
	 * @param dataCount 数据总条数
	 * @param pageIndex 当前第几页
	 * @param pageCount 一页几条数据
	 * @return
	 */
	public static String padingHTML(Integer allPageSize, Integer dataCount, Integer pageIndex, Integer pageCount){
		// 求起始页
		Integer beginIndex = 0;
		if(pageIndex-3 < 0){
			beginIndex = 0;
		}else{
			beginIndex = pageIndex-3;
		}
		// 拼接html
		StringBuffer sb = new StringBuffer();
		sb.append("<nav aria-label='Page navigation example'>");
		sb.append("<ul class='pagination justify-content-end'>");
		sb.append("<li class='page-item'><a class='page-link' href='javascript:ajaxPaging(0)'>第一页</a></li>");
		
		for(int i=0;i<5;i++){
			// 如果当前页 小于 总页数，防止出现多余的页
			if(pageIndex < allPageSize){
				if(beginIndex+i==pageIndex){
					sb.append("<li class='page-item active'><a class='page-link' href='javascript:ajaxPaging("+(beginIndex+i)+")'>"+(beginIndex+i+1)+"</a></li>");
				}else{
					// 当前页必须小于总页数，否则会出现最后一页多出来的情况
					if((beginIndex+i) < allPageSize){
						sb.append("<li class='page-item'><a class='page-link' href='javascript:ajaxPaging("+(beginIndex+i)+")'>"+(beginIndex+i+1)+"</a></li>");
					}
				}
			}
		}
		
		sb.append("<li class='page-item'><a class='page-link' href='javascript:ajaxPaging("+(allPageSize-1)+")'>最后一页</a></li>");
		sb.append("<li class='page-item'><a class='page-link' href='#'>"+(pageIndex+1)+"/"+allPageSize+"页，"+dataCount+"条信息</a></li>");
		sb.append("</ul>");
		sb.append("</nav>");
		return sb.toString();
	}
	
	
}

package com.kanhaoyi.www.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kanhaoyi.www.dao.ICourseCommentDao;
import com.kanhaoyi.www.model.CourseComment;
import com.kanhaoyi.www.service.ICourseCommentService;
import com.kanhaoyi.www.util.PropertiesUtil;

@Service("courseCommentServiceImpl")
public class CourseCommentServiceImpl implements ICourseCommentService {

	@Resource
	private ICourseCommentDao courseCommentDao;
	
	@Override
	public int insert(CourseComment courseComment) {
		return courseCommentDao.insert(courseComment);
	}

	@Override
	public List<Map<String,Object>> getListByCourseID(Integer courseID,Integer commentID,Integer size) {
		Map<String,Integer> paramMap = new HashMap<String,Integer>();
		if(size==null || size==0){
			size=5;
		}
		paramMap.put("courseID", courseID);
		paramMap.put("size", size);
		// 第一次查是没有commentID的，取最新值
		if(commentID==null || commentID==0){
			return courseCommentDao.getListByCourseID(paramMap);
		}else{ // 有commentID表示之前查过，跟据id查之后的数据
			paramMap.put("commentID", commentID);
			return courseCommentDao.getListByCourseIDAndCommentID(paramMap);
		}
	}

	@Override
	public StringBuffer getHtml(List<Map<String, Object>> list) {
		StringBuffer sb = new StringBuffer();
		if(list!=null && list.size()>0){
			String imgpath = PropertiesUtil.getValue("system.properties", "imgpath");
			for(Map<String,Object> comment:list){
				sb.append("<div class=\"card mb-4\">");
				sb.append("<div class=\"card-header\">");
				sb.append("<img class=\"mr-3\" style=\"width: 40px;\" src=\""+imgpath+comment.get("picture")+"\">");  
				sb.append(comment.get("nickname")); // 用户名
				sb.append("<a onclick=\"praise("+ comment.get("id") +")\" class=\"float-right\">");
				sb.append("<span  class=\" badge badge-primary \"><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-thumbs-up\"><path d=\"M14 9V5a3 3 0 0 0-3-3l-4 9v11h11.28a2 2 0 0 0 2-1.7l1.38-9a2 2 0 0 0-2-2.3zM7 22H4a2 2 0 0 1-2-2v-7a2 2 0 0 1 2-2h3\"></path></svg>");    
				sb.append("<span name=\"praiseNum"+comment.get("id")+"\" id=\"praiseNum"+comment.get("id")+"\">"+comment.get("praise")+"</span>");     // 单击数
			   	sb.append("</span>"); 		
			   	sb.append("</a>");
			   	sb.append("</div>"); 	
				sb.append("<div class=\"card-body\">");  
				sb.append("<blockquote class=\"blockquote mb-0\">");  
				sb.append("<p>"+ comment.get("content") +"</p>");   // 评论 
				sb.append("<footer class=\"blockquote-footer\">"+ comment.get("nickname") +" <cite title=\"Source Title\">"+ comment.get("time") +"</cite></footer>");      
				sb.append("</blockquote>");      
				sb.append("</div>");    
				sb.append("</div>");  
			}
		}
		return sb;
	}

	@Override
	public List<Map<String, Object>> getListByCourseIDPraise(Integer courseID, Integer size) {
		Map<String,Integer> paramMap = new HashMap<String,Integer>();
		paramMap.put("courseID", courseID);
		paramMap.put("size", size);
		return courseCommentDao.getListByCourseIDPraise(paramMap);
	}

	@Override
	public Map<String, Object> getOneByCourseCommentId(Integer courseCommentId) {
		return courseCommentDao.getOneByCourseCommentId(courseCommentId);
	}

	@Override
	public int praiseAdd(Integer commentID) {
		return courseCommentDao.praiseAdd(commentID);
	}

	@Override
	public int praiseMinus(Integer commentID) {
		return courseCommentDao.praiseMinus(commentID);
	}
	
	

}

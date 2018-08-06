package com.kanhaoyi.www.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kanhaoyi.www.model.Course;
import com.kanhaoyi.www.model.CourseDetail;
import com.kanhaoyi.www.model.CourseType;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtil {
	
	/**
	 * @desctiption 得到Freemarker模块
	 * @param templateName 模版名称
	 * @author zhuziming
	 * @time 2018年3月30日下午5:03:15
	 */
	public static Template getTemplate(String ftlPath, String templateName){
		// 得到配置文件对象，并指定版本为VERSION_2_3_22
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		try {
			// 设置读取ftl模版的位置
			cfg.setDirectoryForTemplateLoading(new File(ftlPath));
			// 模版的名字
			Template tmp = cfg.getTemplate(templateName);
			// 返回这个模版
			return tmp;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @desctiption 得到生成html文件的写入对象
	 * @author zhuziming
	 * @param htmlName 生成页面的名称
	 * @time 2018年3月30日下午5:06:25
	 */
	public static Writer getWriter(String htmlName){
		Writer writer = null;
		try {
			writer = new FileWriter(new File(htmlName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer;
	}
	
	/**
	 * @description 生成网页
	 * @author zhuziming
	 * @time 2018年7月21日 下午5:01:53
	 */
	public static void createCourseHTML(CourseDetail courseDetail, List<CourseDetail> courseDetailList,
			List<CourseType> courseTypeList, CourseType courseType,Course course,
			List<Map<String,Object>> list,StringBuffer GoodPraise){
		// 准备数据
		Map<String ,Object> data = new HashMap<String, Object>();
		String indexpath = PropertiesUtil.getValue("system.properties", "indexpath");
		String imgpath = PropertiesUtil.getValue("system.properties", "imgpath");
		String csspath = PropertiesUtil.getValue("system.properties", "csspath");
		String jspath = PropertiesUtil.getValue("system.properties", "jspath");
		String projectPath = PropertiesUtil.getValue("system.properties", "projectPath");
		String freeMarkerFtlpath = PropertiesUtil.getValue("system.properties", "freeMarkerFtlpath");
		data.put("indexpath",indexpath);
		data.put("imgpath",imgpath);
		data.put("csspath",csspath);
		data.put("jspath",jspath);
		
		data.put("courseTypeList",courseTypeList); // 课程类型例表，导航用
		data.put("course",course); // 课程 子导航用
		data.put("courseType",courseType); // 课程
		data.put("courseDetailList",courseDetailList); //课程集数列表
		data.put("courseDetail",courseDetail);// 课程那一集
		data.put("GoodPraise",GoodPraise); // 赞最多的评论
		
		// 目录要一级一级的创建，否则会失败
		String deskpath = projectPath+"/"+courseType.getNameSpace()+"/"+course.getUserID()+"/";
		String[] desks = deskpath.split("/");
		String deskpath_1="";
		for(String desk:desks){
			deskpath_1 += desk+"/";
			File deskFile =new File(deskpath_1);    
			if(!deskFile .exists()  && !deskFile .isDirectory()){
				deskFile.mkdir();
			}
		}
		
		// 得到ftl模版
		Template tmp = FreeMarkerUtil.getTemplate(freeMarkerFtlpath,"front/template-play.ftl");
		// 得到生成对象
		Writer writer = FreeMarkerUtil.getWriter(projectPath+courseDetail.getCoursePath());	
		try {
			// 生成网页
			tmp.process(data, writer);
			// 清空缓存
			writer.flush();
			writer.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String freeMarkerFtlpath = PropertiesUtil.getValue("system.properties", "freeMarkerFtlpath");
		// 准备数据
		Map<String ,String> data = new HashMap<String, String>();
		data.put("username", "老潭?");
		// 得到ftl模版
		Template tmp = FreeMarkerUtil.getTemplate(freeMarkerFtlpath,"test.ftl");
		// 得到生成对象
		Writer writer = FreeMarkerUtil.getWriter("test.html");	
		try {
			// 生成网页
			tmp.process(data, writer);
			// 清空缓存
			writer.flush();
			writer.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package com.kanhaoyi.www.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


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
	public static Template getTemplate(String templateName){
		// 得到配置文件对象，并指定版本为VERSION_2_3_22
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		try {
			// 设置读取ftl模版的位置
			cfg.setDirectoryForTemplateLoading(new File("src/ftl"));
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

	public static void main(String[] args) {
		// 准备数据
		Map<String ,String> data = new HashMap<String, String>();
		data.put("username", "老潭?");
		// 得到ftl模版
		Template tmp = FreeMarkerUtil.getTemplate("test.ftl");
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

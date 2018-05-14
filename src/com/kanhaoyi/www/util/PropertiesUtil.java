package com.kanhaoyi.www.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/** 
 * @discription properties文件的操作类
 * @author zhuziming
 * @time 2018年4月17日上午9:39:31
 */
public class PropertiesUtil {
	
	// 缓存map
	private static Map<String,String> map = new HashMap<String,String>();

	/**
	 * @desctiption 获取属性值
	 * @author zhuziming
	 * @param fileName 文件名
	 * @param name 属性名
	 * @return 属性值
	 * @time 2018年4月17日上午9:39:52
	 */
	public static String getValue(String fileName,String name){
		// 1.先从静态变量中读
		String val = map.get(name);
		if(val!=null){
			return val;
		}
		// 2.如果变量中没有在读配置文件，读到了就存入变量
		String path = PropertiesUtil.class.getClassLoader().getResource(fileName).getPath();
		Properties p = new Properties();
    	try { 
    		FileReader in  = new FileReader(path);
    		   p.load(in);
    		   in.close();
    		  } catch (IOException e) {
    		   System.out.println("===================>配置文件:"+fileName+",未找到");
    		  }
    		  String value= p.getProperty(name);
    		  p.clear();
   		   if(value==null){
   			value="";
   			   System.out.println("===================>配置文件:"+fileName+"中--"+name+"属性,未找到");   
   		   }else{
   			   map.put(name, value);
   		   }
		return value;
	}

}

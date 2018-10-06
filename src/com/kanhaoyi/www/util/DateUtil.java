package com.kanhaoyi.www.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description 时间工具类
 * @author zhuziming
 * @time 2018年6月24日 下午5:29:53
 */
public class DateUtil {

	/**
	 * @description 格式化时间为字符串
	 * @author zhuziming
	 * @time 2018年6月24日 下午5:34:50
	 * @param timestamp
	 * @return
	 */
	public static String formatTimestampToStr(Timestamp timestamp){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(timestamp);
	}
	
	/**
	 * @description 格式化时间为字符串
	 * @author zhuziming
	 * @time 2018年6月24日 下午5:34:50
	 * @param timestamp
	 * @return
	 * @throws ParseException 
	 */
	public static String formatTimestampToStr(String time) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(time);
		return sdf.format(date);
	}
	
}

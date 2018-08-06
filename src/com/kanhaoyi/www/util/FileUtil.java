package com.kanhaoyi.www.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.kanhaoyi.www.model.CourseDetail;

/**
 * @description 文件工具类
 * @author zhuziming
 * @time 2018年7月15日 上午11:31:50
 */
public class FileUtil {

	/**
	 * @description 生成视频，图片
	 * @author zhuziming
	 * @time 2018年7月15日 上午11:35:23
	 * @param savePath 保存路径
	 * @param userID 用户id
	 * @param dataName 文件名称
	 * @param format 文件后缀名
	 * @param file 文件
	 * @throws IOException 
	 */
	public static void createFile(String savePath, String userID, String dataName, String format, CommonsMultipartFile file) throws IOException{
		// 如果目录不存在，创建
		String deskpath = savePath+"/"+userID+"/";
		File deskFile =new File(deskpath);    
		if(!deskFile .exists()  && !deskFile .isDirectory()){
			deskFile.mkdir();
		}
		
        //拿到输出流，同时重命名上传的文件  
        FileOutputStream os = new FileOutputStream( deskpath+dataName+format);  
        //拿到上传文件的输入流  
        FileInputStream in = (FileInputStream) file.getInputStream();  
        //以写字节的方式写文件  
        int b = 0;  
        while((b=in.read()) != -1){  
            os.write(b);  
        }  
        os.flush();  
        os.close();  
        in.close(); 

	}
	
}

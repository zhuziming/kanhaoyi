package com.kanhaoyi.www.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.kanhaoyi.www.model.CourseDetail;

/**
 * @description 文件工具类
 * @author zhuziming
 * @time 2018年7月15日 上午11:31:50
 */
public class FileUtil {

	/**
	 * @description 生成图片
	 * @author zhuziming
	 * @time 2018年7月15日 上午11:35:23
	 * @param savePath 保存路径
	 * @param userID 用户id
	 * @param dataName 文件名称
	 * @param format 文件后缀名
	 * @param file 文件
	 * @throws IOException 
	 */
	public static void createImageFile(String savePath, String userID, String dataName, String format, CommonsMultipartFile file) throws IOException{
		// 如果目录不存在，创建
		String deskpath = savePath+"/"+userID+"/";
		String[] desks = deskpath.split("/");
		String deskpath_1="";
		for(String desk:desks){
			deskpath_1 += desk+"/";
			File deskFile =new File(deskpath_1);    
			if(!deskFile .exists()  && !deskFile .isDirectory()){
				deskFile.mkdir();
			}
		}
		int width = 190; // 图片宽度
		int height= 100; // 图片长度
		
		//拿到输出流，同时重命名上传的文件  
        FileOutputStream outfile = new FileOutputStream( deskpath+dataName+format);  
		// 把文件读为图片
		BufferedImage src = javax.imageio.ImageIO.read(file.getInputStream());
		// 绘制图片大小
		BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 绘制 缩小  后的图片内容 
        tag.getGraphics().drawImage(src, 0, 0, width, height, null); 
		// 把文件写入磁盘
        ImageIO.write(tag, format.replace(".", ""), outfile);
	}
	
	
	/**
	 * @description 生成课程外链图片
	 * @author zhuziming
	 * @time 2018年7月15日 上午11:35:23
	 * @param savePath 保存路径
	 * @param userID 用户id
	 * @param dataName 文件名称
	 * @param format 文件后缀名
	 * @param file 文件
	 * @throws IOException 
	 */
	public static void createCourceLinkImage(String savePath, Integer userID, Integer courseID,Integer dataName, String format, CommonsMultipartFile file) throws IOException{
		// 如果目录不存在，创建
		String deskpath = savePath+"/"+userID+"/"+courseID+"/";
		String[] desks = deskpath.split("/");
		String deskpath_1="";
		for(String desk:desks){
			deskpath_1 += desk+"/";
			File deskFile =new File(deskpath_1);    
			if(!deskFile .exists()  && !deskFile .isDirectory()){
				deskFile.mkdir();
			}
		}
		int width = 200; // 图片宽度
		int height= 200; // 图片长度
		
		//拿到输出流，同时重命名上传的文件  
        FileOutputStream outfile = new FileOutputStream( deskpath+dataName+format);  
		// 把文件读为图片
		BufferedImage src = javax.imageio.ImageIO.read(file.getInputStream());
		// 绘制图片大小
		BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 绘制 缩小  后的图片内容 
        tag.getGraphics().drawImage(src, 0, 0, width, height, null); 
		// 把文件写入磁盘
        ImageIO.write(tag, format.replace(".", ""), outfile);
	}
	
	
	/**
	 * @description 创建首页新闻图片
	 * @author zhuziming
	 * @time 2018年7月15日 上午11:35:23
	 * @param savePath 保存路径
	 * @param userID 用户id
	 * @param dataName 文件名称
	 * @param format 文件后缀名
	 * @param file 文件
	 * @throws IOException 
	 */
	public static void createIndexNewsImage(String savePath, String dataName, String format, CommonsMultipartFile file) throws IOException{
		// 如果目录不存在，创建
		String deskpath = savePath+"/";
		String[] desks = deskpath.split("/");
		String deskpath_1="";
		for(String desk:desks){
			deskpath_1 += desk+"/";
			File deskFile =new File(deskpath_1);    
			if(!deskFile .exists()  && !deskFile .isDirectory()){
				deskFile.mkdir();
			}
		}
		int width = 570; // 图片宽度
		int height= 400; // 图片长度
		
		//拿到输出流，同时重命名上传的文件  
        FileOutputStream outfile = new FileOutputStream( deskpath+dataName+format);  
		// 把文件读为图片
		BufferedImage src = javax.imageio.ImageIO.read(file.getInputStream());
		// 绘制图片大小
		BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 绘制 缩小  后的图片内容 
        tag.getGraphics().drawImage(src, 0, 0, width, height, null); 
		// 把文件写入磁盘
        ImageIO.write(tag, format.replace(".", ""), outfile);
	}
	
	/**
	 * @description 生成视频
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
		String[] desks = deskpath.split("/");
		String deskpath_1="";
		for(String desk:desks){
			deskpath_1 += desk+"/";
			File deskFile =new File(deskpath_1);    
			if(!deskFile .exists()  && !deskFile .isDirectory()){
				deskFile.mkdir();
			}
		}
		
        //拿到输出流，同时重命名上传的文件  
        FileOutputStream os = new FileOutputStream( deskpath+dataName+format);  
        //拿到上传文件的输入流  
        InputStream in = (InputStream) file.getInputStream();  
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

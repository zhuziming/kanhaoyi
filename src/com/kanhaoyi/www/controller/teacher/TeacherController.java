package com.kanhaoyi.www.controller.teacher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.model.Video;
import com.kanhaoyi.www.model.VideoGroup;
import com.kanhaoyi.www.service.IUserService;
import com.kanhaoyi.www.service.IVideoGroupService;
import com.kanhaoyi.www.service.IVideoService;
import com.kanhaoyi.www.util.DateUtil;
import com.kanhaoyi.www.util.InitUtil;
import com.kanhaoyi.www.util.JSONUtil;
import com.kanhaoyi.www.util.PagingUtil;
import com.kanhaoyi.www.util.PropertiesUtil;

/**
 * @discription 老师操作类
 * @author zhuziming
 * @time 2018年5月7日上午11:09:54
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	Logger logger = Logger.getLogger(TeacherController.class);
	
	@Resource
	private IUserService userService;
	@Resource
	private IVideoGroupService videoGroupService;
	@Resource
	private IVideoService videoService;
	
	/**
	 * @description 上传视频页面
	 * @author zhuziming
	 * @time 2018年6月2日 下午6:13:50
	 */
	@RequestMapping("/uploadVideoPage.action")
	public String uploadVideo(Model model,HttpSession session){
		User user = userService.getSessionUser(session);
		List<VideoGroup> videoGroupList = videoGroupService.selectListByUserID(user.getId());
		model.addAttribute("videoGroupList",videoGroupList); // 视频组的名称
		model.addAttribute("user",user);  
		InitUtil.iniSystem(model);
		return "teacher/uploadVideoPage";
	}
	
	/**
	 * @description 发布课程页面
	 * @author zhuziming
	 * @time 2018年6月2日 下午6:14:13
	 */
	@RequestMapping("/publishCoursePage.action")
	public String publishCourse(Model model,HttpSession session){
		String account = SecurityUtils.getSubject().getPrincipal().toString();
		String picture =null;
		try {
			User user = userService.getUserByAccount(account);
			picture = user.getPicture(); // 照片
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(picture==null){
			picture="default.jpg";
		}
		model.addAttribute("picture",picture);
		model.addAttribute("account",account); // 账户
		model.addAttribute("nickname",userService.getSessionNickname(session));  // 昵称
		InitUtil.iniSystem(model);
		return "teacher/publishCoursePage";
	}
	
	/**
	 * @desctiption 我的课程页面
	 * @author zhuziming
	 * @time 2018年5月10日下午5:38:00
	 */
	@RequestMapping("/myCoursePage.action")
	public String myCoursePage(Model model,HttpSession session){
		User user = userService.getSessionUser(session);
		model.addAttribute("user",user);  
		InitUtil.iniSystem(model);
		return "teacher/myCoursePage";
	}
	
	/**
	 * @description 新建视频组
	 * @author zhuziming
	 * @time 2018年6月2日 下午6:15:05
	 */
	@RequestMapping("/createVideoGroup.action")
	@ResponseBody
	public String createGroup(HttpServletRequest request){
		String createGroupName = request.getParameter("createGroupName"); // 新建组名字
		String account = SecurityUtils.getSubject().getPrincipal().toString();
		User user = userService.getUserByAccount(account);
		VideoGroup vg = new VideoGroup();
		vg.setGroupName(createGroupName);
		vg.setUserID(user.getId());
		// 检测是否己存在该组
		List<VideoGroup> list = videoGroupService.selectList(vg);
		if(list!=null && list.size()>0){
			return JSONUtil.returnJson("2", "己存在的分组");
		}
		// 插入并返回新建的组
		videoGroupService.insert(vg);
		return JSONUtil.returnJson("1", JSONObject.toJSONString(vg));
	}
	
	/**
	 * 
	 * @description 上传视频文件，一次只能上传5个，并且为mp4格式
	 * @author zhuziming
	 * @time 2018年6月10日 上午10:13:06
	 * @param videoFile0 第0个上传文件
	 * @param videoName0 第0个上传文件名
	 * @param videoFile1 第1个上传文件
	 * @param videoName1 第1个上传文件名
	 * @param videoFile2 第2个上传文件
	 * @param videoName2 第2个上传文件名
	 * @param videoFile3 第3个上传文件
	 * @param videoName3 第3个上传文件名
	 * @param videoFile4 第4个上传文件
	 * @param videoName4 第4个上传文件名
	 * @param request
	 * @param videoGroupID 视频组id
	 * @return
	 */
	@RequestMapping("/videoFileUpload.action")
	@ResponseBody
	public String videoFileUpload(
			@RequestParam(value="videoFile0",required=false) CommonsMultipartFile videoFile0,
			@RequestParam(value="videoName0",required=false) String videoName0,
			@RequestParam(value="videoFile1",required=false) CommonsMultipartFile videoFile1,
			@RequestParam(value="videoName1",required=false) String videoName1,
			@RequestParam(value="videoFile2",required=false) CommonsMultipartFile videoFile2,
			@RequestParam(value="videoName2",required=false) String videoName2,
			@RequestParam(value="videoFile3",required=false) CommonsMultipartFile videoFile3,
			@RequestParam(value="videoName3",required=false) String videoName3,
			@RequestParam(value="videoFile4",required=false) CommonsMultipartFile videoFile4,
			@RequestParam(value="videoName4",required=false) String videoName4,
			HttpServletRequest request,Integer videoGroupID)
	{
		try{
			// 把得到的值放入list，方便循环
			List<Map<String, Object>> list  = new ArrayList<Map<String, Object>>();
			if(videoFile0!=null && !StringUtils.isEmpty(videoName0)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("videoName", videoName0);
				map.put("videoFile", videoFile0);
				list.add(map);
			}
			if(videoFile1!=null && !StringUtils.isEmpty(videoName1)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("videoName", videoName1);
				map.put("videoFile", videoFile1);
				list.add(map);
			}
			if(videoFile2!=null && !StringUtils.isEmpty(videoName2)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("videoName", videoName2);
				map.put("videoFile", videoFile2);
				list.add(map);
			}
			if(videoFile3!=null && !StringUtils.isEmpty(videoName3)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("videoName", videoName3);
				map.put("videoFile", videoFile3);
				list.add(map);
			}
			if(videoFile4!=null && !StringUtils.isEmpty(videoName4)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("videoName", videoName4);
				map.put("videoFile", videoFile4);
				list.add(map);
			}
			// 视频格式验证，只能上传mp4格式
			for(int i = 0;i<list.size();i++){
				CommonsMultipartFile file = (CommonsMultipartFile)list.get(i).get("videoFile"); 
				String format = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				if(!".mp4".equalsIgnoreCase(format)){
					String msg = URLEncoder.encode("请上传mp4格式的文件", "UTF-8");
					return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("2", msg)+"')</script>";
				}
			}

			if(list.size()>0){
				// 文件的保存路径
				String savePath = PropertiesUtil.getValue("system.properties", "courseMp4");
				for(int i = 0;i<list.size();i++){  
					// 视频的名称
					String videoName = list.get(i).get("videoName").toString(); 
					// 视频文件
					CommonsMultipartFile file = (CommonsMultipartFile)list.get(i).get("videoFile"); 
					// 文件后缀
					String format = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
					// 重新生成文件名
					String dataName = UUID.randomUUID().toString();
					User user = (User) request.getSession().getAttribute("user");
					// 如果目录不存在，创建
					String deskpath = savePath+"/"+user.getId()+"/";
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
	                // 上传成功后，插入视频
	                
	                Video video = new Video();
	                video.setAccountID(user.getId());
	                video.setCreateTime(new Timestamp(new Date().getTime()));
	                video.setGroupID(videoGroupID);
	                video.setLetterName(dataName+format);
	                video.setName(videoName);
	                video.setRemove("0");
	                videoService.insert(video);
		        }
				// 正常传输结束
				String msg = URLEncoder.encode("上传完毕", "UTF-8");
				return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("1", msg)+"')</script>";
			}else{ // 文件为空
				String msg = URLEncoder.encode("上传文件不能为空", "UTF-8");
				return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("2", msg)+"')</script>";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("3", null)+"')</script>";
		}
	}
	
	/**
	 * @description 我的视频页面
	 * @author zhuziming
	 * @time 2018年6月10日 下午5:58:37
	 * @return
	 */
	@RequestMapping("/myVideoPage.action")
	public String myVideoPage(Model model,HttpSession session,Integer pageIndex,Integer pageCount){
		// 如果没有传页数，默认第0页
		if(pageIndex==null){
			pageIndex=0;
		}
		// 如果没有传页条数，默认一页10条
		if(pageCount==null){
			pageCount=10;
		}
		User user = userService.getSessionUser(session);
		List<Video> videoList = videoService.getListByAccountId(user.getId(), pageCount, pageIndex);
		// 数据总条数
		Integer dataCount = videoService.getListCountByAccountId(user.getId());
		Map<String,Integer> map = PagingUtil.beginPaging(pageIndex, pageCount, dataCount);
		model.addAttribute("paging",map);
		model.addAttribute("padingHTML",PagingUtil.padingHTML(map.get("allPageSize"), map.get("dataCount"), map.get("pageIndex"), map.get("pageCount")));
		model.addAttribute("user",user);  
		model.addAttribute("videoList",videoList);
		InitUtil.iniSystem(model);
		return "teacher/myVideoPage";
	}
	
	/**
	 * @description ajax得到我的视频列表
	 * @author zhuziming
	 * @time 2018年6月24日 上午11:26:46
	 * @return
	 */
	@RequestMapping("/ajaxGetVideoList.action")
	@ResponseBody
	public String ajaxGetVideoList(HttpServletRequest request,HttpSession session){
		try{
			JSONObject jo = new JSONObject();
			String sPageIndex = request.getParameter("pageIndex");
			String sPageCount = request.getParameter("pageCount");
			Integer pageIndex =null; // 页数
			Integer pageCount =null; // 一页几条数据
			// 如果没有传页数，默认是第0页
			if(sPageIndex==null){
				pageIndex = 0;
			}else{
				pageIndex = Integer.valueOf(sPageIndex);
			}
			// 如果没有传页条数，默认一页10条
			if(sPageCount==null){
				pageCount=10;
			}else{
				pageCount = Integer.valueOf(sPageCount);
			}
			User user = userService.getSessionUser(session);
			List<Video> videoList = videoService.getListByAccountId(user.getId(), pageCount, pageIndex);
			StringBuffer dataList = new StringBuffer(); // 数据集合
			for (int i = 0; i < videoList.size(); i++) {
				Video  video= videoList.get(i);
				dataList.append("<tr>");
				dataList.append("<td>"+video.getName()+"</td>");
				dataList.append("<td>"+DateUtil.formatTimestampToStr(video.getCreateTime())+"</td>");
				dataList.append("</tr>");
			}
			
			StringBuffer padingHTML = new StringBuffer(); // 分页按钮集合
			Integer dataCount = videoService.getListCountByAccountId(user.getId());// 数据总条数
			Map<String,Integer> map = PagingUtil.beginPaging(pageIndex, pageCount, dataCount);
			padingHTML.append(PagingUtil.padingHTML(map.get("allPageSize"), map.get("dataCount"), map.get("pageIndex"), map.get("pageCount")));
			jo.put("dataList", dataList);
			jo.put("padingHTML", padingHTML);
			if(dataList.length() > 0){
				return JSONUtil.returnJson("1", jo.toString());
			}else{
				return JSONUtil.returnJson("2", "没有数据了");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("3", "服务器异常");
		}
	}
	
	
	
	
}

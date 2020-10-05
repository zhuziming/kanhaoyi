package com.kanhaoyi.www.controller.teacher;


import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kanhaoyi.www.model.Course;
import com.kanhaoyi.www.model.CourseComment;
import com.kanhaoyi.www.model.CourseDetail;
import com.kanhaoyi.www.model.CourseLink;
import com.kanhaoyi.www.model.CoursePeople;
import com.kanhaoyi.www.model.CourseType;
import com.kanhaoyi.www.model.PeoplePart;
import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.model.Video;
import com.kanhaoyi.www.model.VideoGroup;
import com.kanhaoyi.www.service.ICourseCommentService;
import com.kanhaoyi.www.service.ICourseDetailService;
import com.kanhaoyi.www.service.ICourseLinkService;
import com.kanhaoyi.www.service.ICoursePeopleService;
import com.kanhaoyi.www.service.ICourseService;
import com.kanhaoyi.www.service.ICourseTypeService;
import com.kanhaoyi.www.service.IPeoplePartService;
import com.kanhaoyi.www.service.IUserService;
import com.kanhaoyi.www.service.IVideoGroupService;
import com.kanhaoyi.www.service.IVideoService;
import com.kanhaoyi.www.util.DateUtil;
import com.kanhaoyi.www.util.FileUtil;
import com.kanhaoyi.www.util.FreeMarkerUtil;
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
	@Resource
	private ICourseTypeService courseTypeService;
	@Resource
	private ICourseService courseService;
	@Resource
	private ICourseDetailService courseDetailService;
	@Resource
	private ICourseCommentService courseCommentService;
	@Resource
	private IPeoplePartService peoplePartService;
	@Resource
	private ICoursePeopleService coursePeopleService;
	@Resource
	private ICourseLinkService courseLinkService;
	
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
	public String publishCoursePage(Model model,HttpSession session){
		User user = userService.getSessionUser(session);
		List<VideoGroup> videoGroupList = videoGroupService.selectListByUserID(user.getId()); // 用户自定义视频组名称
		List<CourseType> courseTypeList = courseTypeService.getAll();
		List<PeoplePart> peoplePartList = peoplePartService.getAll();
		
		model.addAttribute("user",user);
		model.addAttribute("videoGroupList",videoGroupList); // 视频组的名称
		model.addAttribute("courseTypeList",courseTypeList); // 科室列表
		model.addAttribute("peoplePartList",peoplePartList); // 课程部位
		InitUtil.iniSystem(model);
		return "teacher/publishCoursePage";
	}
	
	
	/**
	 * @description 发布课程，参数 videoName 视频可以为空，只展示文字和图片
	 * @author zhuziming
	 * @time 2018年7月8日 下午5:07:29
	 * @param request
	 * @return
	 */
	@RequestMapping("/publishCourse.action")
	@ResponseBody
	@Transactional
	public String publishCourse(HttpServletRequest request,
			@RequestParam(value="courseImg",required=false) CommonsMultipartFile courseImg){
		try{
			
			String[] peoplePartIDs = request.getParameterValues("peopelPartID"); // 人体部位id
			if(peoplePartIDs==null || peoplePartIDs.length==0){
				String msg = URLEncoder.encode("请选课程所属部位", "UTF-8");
				return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("2", msg)+"')</script>";
			}
			
			User user = userService.getSessionUser(request.getSession());
			String imgSavePath = PropertiesUtil.getValue("system.properties", "courseImg");
			// 文件后缀
			String imgFormat = courseImg.getOriginalFilename().substring(courseImg.getOriginalFilename().lastIndexOf("."));
			if(!".jpg".equalsIgnoreCase(imgFormat) && !".png".equalsIgnoreCase(imgFormat)){
				String msg = URLEncoder.encode("图片格式错误", "UTF-8");
				return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("2", msg)+"')</script>";
			}
			// 重新生成文件名
			String imgDataName = UUID.randomUUID().toString();
			// 创建
			FileUtil.createImageFile(imgSavePath, user.getId()+"", imgDataName, imgFormat, courseImg);

			// 1.生成课程
			String courseTitle = request.getParameter("courseTitle"); // 课程标题
			String courseTypeID= request.getParameter("courseTypeID"); // 课程类型id
			CourseType courseType = courseTypeService.getOneByID(Integer.valueOf(courseTypeID));
			
			Course course = new Course();
			course.setCourseName(courseTitle);
			course.setCourseTypeID(Integer.valueOf(courseTypeID));
			course.setUserID(user.getId());
			course.setPicturePath("/"+user.getId()+"/"+imgDataName+imgFormat);
			course.setClickVolume(0);
			course.setTime(new Timestamp(new Date().getTime()));
			courseService.insert(course);
			
			for (String peoplePartID : peoplePartIDs) { // 插入课程与部位关联表
				CoursePeople cp = new CoursePeople();
				cp.setCourseID(course.getId());
				cp.setPeoplePartID(Integer.valueOf(peoplePartID));
				coursePeopleService.insert(cp);
			}
			
			// 2.为课程生成集数，先排序在插入数据库
			Enumeration<String> enu =request.getParameterNames(); // 取得所有的参数名
			int sequence = 0;
			List<Integer> numList = new ArrayList<Integer>();
			while(enu.hasMoreElements()){
				String param = enu.nextElement();
				int ind = param.indexOf("courseName");
				if(ind != -1){// 取课程名
					String index = param.replace("courseName", ""); // 取参数后边的下标
					numList.add(Integer.valueOf(index));
				}
			}
			if(numList.size() > 0){
				int[] array = new int[numList.size()];
				for(int i=0;i<array.length;i++){
					array[i] = numList.get(i);
				}
				// 冒泡排序，从小到大
				int temp;
				for (int i = 0; i < array.length; i++) {
		            for (int j = i+1; j < array.length; j++) {
		                if (array[i] > array[j]) {
		                    temp = array[i];
		                    array[i] = array[j];
		                    array[j] = temp;  // 两个数交换位置
		                }
		            }
		        }
				// 程序排序后，跟据顺序插入数据库
				for(int i:array){
					sequence++;
					String courseDetailName = request.getParameter("courseName"+i); // 取本集课程名字
					String videoName = request.getParameter("videoName"+i); // 跟据下标取视频id
					CourseDetail courseDetail = new CourseDetail();
					courseDetail.setCourseID(course.getId());
					courseDetailService.insert(courseDetail);
					// 课程路径 项目目录/科室/用户id/页面id.html
					String coursePath = "/"+courseType.getNameSpace()+"/"+user.getId()+"/"+courseDetail.getId()+".html";
					courseDetail.setCoursePath(coursePath);
					courseDetail.setCourseDetailName(courseDetailName);
					courseDetail.setCreateTime(new Timestamp(new Date().getTime()));
					courseDetail.setSequence(sequence);
					if(videoName!=null && !videoName.isEmpty()){ // 课程详情页面中，视频可以为空，只展示文字
						courseDetail.setVideoID(Integer.valueOf(videoName));
					}
					courseDetail.setClickVolume(0);
					courseDetailService.update(courseDetail); 
				}
				if(sequence > 0){
					CourseDetail courseDetail_1 = courseDetailService.getOneOrderBy("id", "asc", course.getId()+"");
					course.setCoursePath(courseDetail_1.getCoursePath());
					course.setQuantity(array.length);
					courseService.update(course);
				}
				
				// 生成html页面
				// 准备数据 课程
				Course course_ = courseService.getOneByID(course.getId());
				// 课程商品链接列表
				List<CourseLink> courseLinkList_ = courseLinkService.getListByCourseID(course_.getId());
				// 课程类型列表，网页中导航部分用
				List<CourseType> courseTypeList_ = courseTypeService.getAll();
				// 当前课程类型，面包屑导航用
				CourseType courseType_ = courseTypeService.getOneByID(course_.getCourseTypeID());
				// 课程详情列表，右则课程列表显示用
				List<CourseDetail> courseDetailList_ = courseDetailService.getListByCourseIdAndSequence(course_.getId(), "ASC");
				// 得到最多的赞评论 5条
				List<Map<String,Object>> list_ = courseCommentService.getListByCourseIDPraise(Integer.valueOf(course_.getId()), 5);
				// 把赞评论转为html
				StringBuffer GoodPraise_ = courseCommentService.getHtml(list_); // 赞最多的评论
				
				for(CourseDetail courseDetail_2 : courseDetailList_){
					Video video_ = videoService.getOneByID(courseDetail_2.getVideoID());
					// 生成网页
					FreeMarkerUtil.createCourseHTML(courseDetail_2, courseDetailList_, 
							courseTypeList_, courseType_, course_, list_, GoodPraise_,video_,courseLinkList_);
				}
				String msg = URLEncoder.encode("上传完毕", "UTF-8");
				return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("1", msg)+"')</script>";
			}else{
				String msg = URLEncoder.encode("课程不能为空", "UTF-8");
				return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("2", msg)+"')</script>";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("3", "异常了")+"')</script>";
		}
	}
	
	
	/**
	 * @desctiption 我的课程页面
	 * @author zhuziming
	 * @time 2018年5月10日下午5:38:00
	 */
	@RequestMapping("/myCoursePage.action")
	public String myCoursePage(Model model,HttpSession session, Integer pageIndex,Integer pageCount){
		// 如果没有传页数，默认第0页
		if(pageIndex==null){
			pageIndex=0;
		}
		// 如果没有传页条数，默认一页10条
		if(pageCount==null){
			pageCount=10;
		}
		User user = userService.getSessionUser(session);
		Integer dataCount = courseService.getCountByUserID(user.getId());
		Map<String,Integer> map = PagingUtil.beginPaging(pageIndex, pageCount,dataCount);
		List<Map> courseListMap = courseService.getListByUserIDLeftCourseType(user.getId(),"id","DESC",pageCount,pageIndex);
		model.addAttribute("paging",map);
		model.addAttribute("padingHTML",PagingUtil.padingHTML(map.get("allPageSize"), map.get("dataCount"), map.get("pageIndex"), map.get("pageCount")));
		model.addAttribute("courseListMap",courseListMap);
		model.addAttribute("user",user);
		InitUtil.iniSystem(model);
		return "teacher/myCoursePage";
	}
	
	
	/**
	 * @description ajax得到我的课程列表
	 * @author zhuziming
	 * @time 2018年6月24日 上午11:26:46
	 * @return
	 */
	@RequestMapping("/ajaxGetCourseList.action")
	@ResponseBody
	public String ajaxGetCourseList(HttpServletRequest request,HttpSession session){
		String indexpath = PropertiesUtil.getValue("system.properties", "indexpath");
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
			List<Map> courseListMap = courseService.getListByUserIDLeftCourseType(user.getId(),"id","DESC",pageCount,pageIndex);
			StringBuffer dataList = new StringBuffer(); // 数据集合
			for (int i = 0; i < courseListMap.size(); i++) {
				Map  map= courseListMap.get(i);
				dataList.append("<tr>");
				dataList.append("<td scope=\"row\">"+map.get("course_name")+"</td>");
				dataList.append("<td>"+map.get("name")+"</td>");
				dataList.append("<td>"+DateUtil.formatTimestampToStr(map.get("time").toString())+"</td>");
				dataList.append("<td>"+map.get("click_volume")+"</td>");
				dataList.append("<td>"+map.get("quantity")+"</td>");
				dataList.append("<td><a href=\""+indexpath+"/teacher/compileCoursePage.action?courseID="+map.get("id")+"\" class=\"btn btn-info btn-sm\">编辑</a></td>");
				dataList.append("</tr>");
			}
			// DateUtil.formatTimestampToStr(map.get("time"))
			StringBuffer padingHTML = new StringBuffer(); // 分页按钮集合
			Integer dataCount = courseService.getCountByUserID(user.getId());
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
	
	/**
	 * @description 编辑课程页面
	 * @author zhuziming
	 * @time 2018年10月22日 上午11:06:45
	 * @return
	 */
	@RequestMapping("/compileCoursePage.action")
	public String compileCoursePage(HttpSession session,Model model,Integer courseID){
		User user = userService.getSessionUser(session);
		Course course = courseService.getOneByID(courseID); // 当前课程
		List<CourseDetail> courseDetailList = courseDetailService.getListByCourseIdAndSequence(courseID, "ASC"); // 当前课程的全部子集数
		List<CourseType> courseTypeList = courseTypeService.getAll(); // 全部科室
		List<CourseLink> courseLinkList = courseLinkService.getListByCourseID(courseID); // 商品链接
		List<VideoGroup> videoGroupList = videoGroupService.selectListByUserID(user.getId()); // 用户自定义视频组名称
		List<Map<String, Object>> courseCommentList = courseCommentService.getListByCourseID(courseID, null, 5);// 课程评论
		
		for(Map<String, Object> map:courseCommentList){ // 去除评论中的空格，因为要在页面中显示文字
			String content = map.get("content").toString().replace("&nbsp", "").replace("<br>", "");
			map.put("content", content);
		}
		
		for (CourseLink courseLink : courseLinkList) {
			model.addAttribute("courseLink"+courseLink.getPicture(),courseLink);
		}
		model.addAttribute("course",course); // 课程
		model.addAttribute("user",user); // 当前用户
		model.addAttribute("courseDetailList",courseDetailList); // 课程全部子集数
		model.addAttribute("courseTypeList",courseTypeList); // 科室列表
		model.addAttribute("videoGroupList",videoGroupList); // 视频组列表
		model.addAttribute("courseCommentList",courseCommentList); // 课程评论
		
		InitUtil.iniSystem(model);
		return "teacher/compileCoursePage";
	}
	/**
	 * @description 编辑课程
	 * @author zhuziming
	 * @time 2018年10月26日 下午2:33:18
	 * @param course
	 * @param session
	 * @return
	 */
	@RequestMapping("/compileCourse.action")
	@ResponseBody
	@Transactional
	public String compileCourse(Course course,HttpSession session,HttpServletRequest request){
		User user = userService.getSessionUser(session);
		Course course_check = courseService.getOneByID(course.getId()); // 取当前课程，查看是否属于该用户
		if(user.getId()!=course_check.getUserID()){
			return JSONUtil.returnJson("2", "该课程不属于当前用户，无法操作");
		}
		courseService.update(course);
		
		/* 更改每一集课程的网页目录 */
		CourseType courseType_1 = courseTypeService.getOneByID(course.getCourseTypeID());
		List<CourseDetail> courseDetailList = courseDetailService.getListByCourseIdAndSequence(course.getId(), "ASC");
		for (CourseDetail courseDetail_2 : courseDetailList) {
			// 课程路径 项目目录/科室/用户id/页面id.html
			String coursePath = "/"+courseType_1.getNameSpace()+"/"+user.getId()+"/"+courseDetail_2.getId()+".html";
			courseDetail_2.setCoursePath(coursePath);
			courseDetailService.update(courseDetail_2); 
		}
		/* 更改课程网页目录为课程详情中的第一页 */
		CourseDetail courseDetail_1 = courseDetailService.getOneOrderBy("id", "asc", course.getId()+"");
		course.setCoursePath(courseDetail_1.getCoursePath());
		courseService.update(course);
		
		
		
		
		/* 如果有新添加的集数，为其生成网页 */
		Enumeration<String> enu =request.getParameterNames(); // 取得所有的参数名
		
		List<Integer> numList = new ArrayList<Integer>();
		while(enu.hasMoreElements()){
			String param = enu.nextElement();
			int ind = param.indexOf("courseName");
			if(ind != -1){// 取课程名，有可能为空值，
				String CN = request.getParameter(param).replace(" ", "");
				if(CN != null && !CN.equals("")){ // 检查课程名是否为空
					String index = param.replace("courseName", ""); // 取参数后边的下标
					String VN = request.getParameter("videoName"+index);
					if(VN != null && !VN.equals("")){
						numList.add(Integer.valueOf(index));
					}
				}
			}
		}
		if(numList.size() > 0){
			int[] array = new int[numList.size()];
			for(int i=0;i<array.length;i++){
				array[i] = numList.get(i);
			}
			// 冒泡排序，从小到大
			int temp;
			for (int i = 0; i < array.length; i++) {
	            for (int j = i+1; j < array.length; j++) {
	                if (array[i] > array[j]) {
	                    temp = array[i];
	                    array[i] = array[j];
	                    array[j] = temp;  // 两个数交换位置
	                }
	            }
	        }
			// 程序排序后，跟据顺序插入数据库
			CourseDetail courseDetail_se = courseDetailService.getOneOrderBy("sequence", "DESC", course.getId()+"");
			int sequence = 0;
			try{
				sequence = courseDetail_se.getSequence(); // 取之前课程中排序的最大数，为新添加的课程叠加顺序
			}catch(Exception e){ }
			
			for(int i:array){
				sequence++;
				String courseDetailName = request.getParameter("courseName"+i); // 取本集课程名字
				String videoName = request.getParameter("videoName"+i); // 跟据下标取视频id
				CourseDetail courseDetail = new CourseDetail();
				courseDetail.setCourseID(course.getId());
				courseDetailService.insert(courseDetail);
				// 课程路径 项目目录/科室/用户id/页面id.html
				String coursePath = "/"+courseType_1.getNameSpace()+"/"+user.getId()+"/"+courseDetail.getId()+".html";
				courseDetail.setCoursePath(coursePath);
				courseDetail.setCourseDetailName(courseDetailName);
				courseDetail.setCreateTime(new Timestamp(new Date().getTime()));
				courseDetail.setSequence(sequence);
				courseDetail.setVideoID(Integer.valueOf(videoName));
				courseDetail.setClickVolume(0);
				courseDetailService.update(courseDetail); 
			}
			if(sequence > 0){
				CourseDetail courseDetail_11 = courseDetailService.getOneOrderBy("id", "asc", course.getId()+"");
				course.setCoursePath(courseDetail_11.getCoursePath());
				int quantity = courseDetailService.getListByCourseIdAndSequence(course.getId(), "ASC").size();
				course.setQuantity(quantity);// 课程集数
				courseService.update(course);
			}
		}	
		
		String intro2 = course.getIntro();
		if(intro2!=null && !intro2.equals("")){
			intro2 = intro2.replaceAll("\n", "<br/>"); // 把换行换成html <br/>
			intro2 = intro2.replaceAll(" ", "&nbsp"); // 把空格换成html &nbsp
			intro2 = intro2.replaceAll("\t", "&nbsp"); // 把制表符换成8个空格
		}
		// 生成html页面
		// 准备数据 课程
		Course course_ = courseService.getOneByID(course.getId());
		course_.setIntro(intro2); // 替换生成页面的介绍
		// 课程商品链接列表
		List<CourseLink> courseLinkList_ = courseLinkService.getListByCourseID(course_.getId());
		// 课程类型列表，网页中导航部分用
		List<CourseType> courseTypeList_ = courseTypeService.getAll();
		// 当前课程类型，面包屑导航用
		CourseType courseType_ = courseTypeService.getOneByID(course_.getCourseTypeID());
		// 课程详情列表，右则课程列表显示用
		List<CourseDetail> courseDetailList_ = courseDetailService.getListByCourseIdAndSequence(course_.getId(), "ASC");
		// 得到最多的赞评论 5条
		List<Map<String,Object>> list_ = courseCommentService.getListByCourseIDPraise(Integer.valueOf(course_.getId()), 5);
		// 把赞评论转为html
		StringBuffer GoodPraise_ = courseCommentService.getHtml(list_); // 赞最多的评论
		
		for(CourseDetail courseDetail_2 : courseDetailList_){
			Video video_ = videoService.getOneByID(courseDetail_2.getVideoID());
			// 生成网页
			FreeMarkerUtil.createCourseHTML(courseDetail_2, courseDetailList_, 
					courseTypeList_, courseType_, course_, list_, GoodPraise_,video_,courseLinkList_);
		}
		return JSONUtil.returnJson("1", "修改成功");
	}
	
	/***
	 * @description 编缉评论页面
	 * @author zhuziming
	 * @time 2020年10月5日 下午4:30:04
	 * @return
	 */
	@RequestMapping("/editCommentPage.action")
	public String editCommentPage(HttpSession session,Model model,Integer commentID){
		User user = userService.getSessionUser(session);
		Map<String, Object> commentMap = courseCommentService.getOneByCourseCommentId(commentID);
		Integer courseID = Integer.valueOf(commentMap.get("course_id").toString());
		
		Course course = courseService.getOneByID(courseID); // 当前课程
		
		model.addAttribute("user",user); // 当前用户
		model.addAttribute("course",course);
		model.addAttribute("commentMap",commentMap);
		InitUtil.iniSystem(model);

		return "teacher/editCommentPage";
	}
	
	/**
	 * @description 修改评论
	 * @author zhuziming
	 * @time 2020年10月5日 下午4:58:16
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyComment.action")
	public String modifyComment(HttpServletRequest request){
		try{
			String commentID = request.getParameter("commentID");
			if(commentID.isEmpty()){
				return JSONUtil.returnJson("2", "缺少id");
			}
			Integer id = Integer.valueOf(commentID);
			String content   = request.getParameter("content");
			CourseComment cc = new CourseComment();
			cc.setId(id);
			cc.setContent(content);
			courseCommentService.updateOne(cc);
			
			return JSONUtil.returnJson("1", "修改成功");
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("3", "异常了");
		}
		
	}
	
	/**
	 * @description 添加一个网页链接
	 * @author zhuziming
	 * @time 2018年10月28日 下午4:17:13
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addLink.action")
	public String addLink(@RequestParam(value="pictureFile",required=true) CommonsMultipartFile pictureFile,
			CourseLink courseLink,HttpServletRequest request){
		try{
			// 检查图片
			if(pictureFile.isEmpty()){
				String msg = URLEncoder.encode("图片不能为空", "UTF-8");
				return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("2", msg)+"')</script>";
			}
			User user = userService.getSessionUser(request.getSession());
			// 检查该课程是否属于该用户
			Course course = courseService.getOneByID(courseLink.getCourseID());
			if(course.getUserID()!=user.getId()){
				String msg = URLEncoder.encode("非法操作", "UTF-8");
				return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("2", msg)+"')</script>";
			}
			// 检查记录数是否超出
			List<CourseLink> courseLinkList = courseLinkService.getListByCourseID(courseLink.getCourseID());
			if(courseLinkList!=null && courseLinkList.size() >= 4){
				String msg = URLEncoder.encode("已超过4条，请先删除后在添加", "UTF-8");
				return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("2", msg)+"')</script>";
			}
			if(courseLink.getEndTime()==null ){
				String msg = URLEncoder.encode("请填写到期时间", "UTF-8");
				return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("2", msg)+"')</script>";
			}
			// 检查同一个位置是否多次插入
			for (CourseLink courseLink_f : courseLinkList) {
				if(courseLink_f.getPicture()==courseLink.getPicture()){
					String msg = URLEncoder.encode("已存在的信息，请先删除后在添加", "UTF-8");
					return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("2", msg)+"')</script>";
				}
			}
			
			
			String imgSavePath = PropertiesUtil.getValue("system.properties", "courseLinkImg");
			String imgFormat = pictureFile.getOriginalFilename().substring(pictureFile.getOriginalFilename().lastIndexOf("."));
			// 创建图片
			FileUtil.createCourceLinkImage(imgSavePath, user.getId(),courseLink.getCourseID() ,courseLink.getPicture(), imgFormat, pictureFile);
			courseLink.setFormat(imgFormat);
			courseLink.setCreateTime(new Timestamp(System.currentTimeMillis()));
			courseLinkService.insert(courseLink);
			String msg = URLEncoder.encode("保存成功", "UTF-8");
			return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("1", msg)+"')</script>";
		}catch(Exception e){
			return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("3", "异常了")+"')</script>";
		}
	}
	/**
	 * @description 删除课程商品链接
	 * @author zhuziming
	 * @time 2018年10月29日 下午2:35:04
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delLink.action")
	public String delCourseLink(HttpServletRequest request,CourseLink courseLink){
		try{
			User user = userService.getSessionUser(request.getSession());
			// 检查该课程是否属于该用户
			Course course = courseService.getOneByID(courseLink.getCourseID());
			if(course.getUserID()!=user.getId()){
				String msg = URLEncoder.encode("非法操作", "UTF-8");
				return JSONUtil.returnJson("2", msg);
			}
			courseLinkService.deleteByID(courseLink.getId());
			String msg = URLEncoder.encode("删除成功", "UTF-8");
			return JSONUtil.returnJson("1", msg);
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("3", "error");
		}
	}
	
	/**
	 * @description 课程  子集  页面
	 * @author zhuziming
	 * @time 2020年9月24日 下午15:48:05
	 * */
	@RequestMapping("/myCourseDetailPage.action")
	public String myCourseDetailPage(HttpSession session,Model model,Integer courseDetailID){
		User user = userService.getSessionUser(session);
		CourseDetail courseDetail = courseDetailService.getOneById(courseDetailID); // 当前子课程
		Course course = courseService.getOneByID(courseDetail.getCourseID()); // 当前课程
		
		model.addAttribute("user",user); // 当前用户
		model.addAttribute("course",course);
		model.addAttribute("courseDetail",courseDetail);
		InitUtil.iniSystem(model);
		return "teacher/myCourseDetailPage";
	}
	
	/**
	 * @description 修改课程详情，包括meta，title，文本内容
	 * @author zhuziming
	 * @time 2020年9月24日 下午17:48:05
	 */
	@RequestMapping("/modifyCourseDetail.action")
	@ResponseBody
	public String modifyCourseDetail(HttpServletRequest request){
		try{
			Integer detailID = Integer.valueOf(request.getParameter("detailID"));// 详情ID
			String meta = request.getParameter("detailMeta"); // 头标签meta
			String title= request.getParameter("detailTitle");// 头title
			String text = request.getParameter("detailText"); // 文本内容
			// 修改详情页数据，包括meta,intro,title
			CourseDetail courseDetail = courseDetailService.getOneById(detailID);
			courseDetail.setMeta(meta);
			courseDetail.setTitle(title);
			courseDetail.setIntro(text);
			courseDetailService.update(courseDetail);
			
			
			// 生成html页面
			// 准备数据 课程
			Course course_ = courseService.getOneByID(courseDetail.getCourseID());
			// 课程商品链接列表
			List<CourseLink> courseLinkList_ = courseLinkService.getListByCourseID(course_.getId());
			// 课程类型列表，网页中导航部分用
			List<CourseType> courseTypeList_ = courseTypeService.getAll();
			// 当前课程类型，面包屑导航用
			CourseType courseType_ = courseTypeService.getOneByID(course_.getCourseTypeID());
			// 课程详情列表，右则课程列表显示用
			List<CourseDetail> courseDetailList_ = courseDetailService.getListByCourseIdAndSequence(course_.getId(), "ASC");
			// 得到最多的赞评论 5条
			List<Map<String,Object>> list_ = courseCommentService.getListByCourseIDPraise(Integer.valueOf(course_.getId()), 5);
			// 把赞评论转为html
			StringBuffer GoodPraise_ = courseCommentService.getHtml(list_); // 赞最多的评论
			// 视频的名字
			Video video_ = videoService.getOneByID(courseDetail.getVideoID());
			// 生成页面
			FreeMarkerUtil.createCourseHTML(courseDetail, courseDetailList_, 
					courseTypeList_, courseType_, course_, list_, GoodPraise_,video_,courseLinkList_);
			
			return JSONUtil.returnJson("1", "修改完成");
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("3", "异常了");
		}
		
		
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
	 * @description 
	 * @author zhuziming
	 * @time 2018年7月8日 下午4:23:59
	 * @param request
	 * @return
	 */
	@RequestMapping("/getVideo.action")
	@ResponseBody
	public String getVideo(HttpServletRequest request){
		try{
			User user = userService.getSessionUser(request.getSession());
			String videoGroupID = request.getParameter("videoGroupID"); // 视频组id
			List<Video> videolist = videoService.getListByAccountIdAndGroupId(user.getId()+"", videoGroupID);
			if(videolist.size() > 0){
				return JSONUtil.returnJson("1", JSONArray.toJSONString(videolist));
			}else{
				return JSONUtil.returnJson("2", "数据为空");
			}
		}catch(Exception e){
			return JSONUtil.returnJson("3", "异常了");
		}
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
					// 创建
					FileUtil.createFile(savePath, user.getId()+"", dataName, format, file);
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
				dataList.append("<td>"+video.getGroupName()+"</td>");
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

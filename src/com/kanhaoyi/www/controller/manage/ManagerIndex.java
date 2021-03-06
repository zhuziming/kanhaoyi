package com.kanhaoyi.www.controller.manage;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.kanhaoyi.www.model.Course;
import com.kanhaoyi.www.model.CourseDetail;
import com.kanhaoyi.www.model.CourseLink;
import com.kanhaoyi.www.model.CourseType;
import com.kanhaoyi.www.model.Customer;
import com.kanhaoyi.www.model.IndexNews;
import com.kanhaoyi.www.model.PeoplePart;
import com.kanhaoyi.www.model.Role;
import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.model.UserRole;
import com.kanhaoyi.www.model.Video;
import com.kanhaoyi.www.service.ICourseCommentService;
import com.kanhaoyi.www.service.ICourseDetailService;
import com.kanhaoyi.www.service.ICourseLinkService;
import com.kanhaoyi.www.service.ICoursePeopleService;
import com.kanhaoyi.www.service.ICourseService;
import com.kanhaoyi.www.service.ICourseTypeService;
import com.kanhaoyi.www.service.ICustomerService;
import com.kanhaoyi.www.service.IIndexNewsService;
import com.kanhaoyi.www.service.IPeoplePartService;
import com.kanhaoyi.www.service.IRoleService;
import com.kanhaoyi.www.service.IUserRoleService;
import com.kanhaoyi.www.service.IUserService;
import com.kanhaoyi.www.service.IVideoService;
import com.kanhaoyi.www.util.DateUtil;
import com.kanhaoyi.www.util.FileUtil;
import com.kanhaoyi.www.util.FreeMarkerUtil;
import com.kanhaoyi.www.util.InitUtil;
import com.kanhaoyi.www.util.JSONUtil;
import com.kanhaoyi.www.util.PagingUtil;
import com.kanhaoyi.www.util.PropertiesUtil;


/**
 * @discription 后台管理
 * @author zhuziming
 * @time 2018年8月7日上午9:44:33
 */
@Controller
@RequestMapping("/manage")
public class ManagerIndex {
	
	@Resource
	private ICourseTypeService courseTypeService;
	@Resource
	private ICourseService courseService;
	@Resource
	private IPeoplePartService peoplePartService;
	@Resource
	private IUserService userService;
	@Resource
	private IRoleService roleService;
	@Resource
	private IUserRoleService userRoleService;
	@Resource
	private ICoursePeopleService coursePeopleService;
	@Resource
	private IIndexNewsService indexNewsService;
	@Resource
	private ICustomerService customerService;
	@Resource
	private ICourseDetailService courseDetailService;
	@Resource
	private ICourseCommentService courseCommentService;
	@Resource
	private IVideoService videoService;
	@Resource
	private ICourseLinkService courseLinkService;
	/**
	 * @desctiption 后台首页
	 * @author zhuziming
	 * @time 2018年8月7日上午9:44:47
	 */
	@RequestMapping("/indexPage.action")
	public String indexPage(Model model){
		List<IndexNews> indexNewslist = indexNewsService.getListByLinkSort("id", "DESC", 10, 0);
		InitUtil.iniSystem(model);
		model.addAttribute("indexNewslist", indexNewslist);
		model.addAttribute("methodName","indexPage"); // leftMenu页面中当前选中的参数
		return "manage/index";
	}
	
	/**
	 * @description 添加一个首页新闻
	 * @author zhuziming
	 * @time 2018年10月26日 下午1:52:23
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addIndexNews.action")
	public String addIndexNews(@RequestParam(value="indexNewsImg",required=true) CommonsMultipartFile indexNewsImg
			,int courseID,String title,String context){
		try{
			// 验证课程是否存在
			Course course = courseService.selectByID(courseID);
			if(course==null){
				String msg = URLEncoder.encode("不存在的课程", "UTF-8");
				return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("2", msg)+"')</script>";
			}
			if(indexNewsImg==null){
				String msg = URLEncoder.encode("图片不能为空", "UTF-8");
				return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("2", msg)+"')</script>";
			}
			
			String indexNewsPath = PropertiesUtil.getValue("system.properties", "indexNewsImg");
			// 验证文件后缀
			String imgFormat = indexNewsImg.getOriginalFilename().substring(indexNewsImg.getOriginalFilename().lastIndexOf("."));
			if(!".jpg".equalsIgnoreCase(imgFormat) && !".png".equalsIgnoreCase(imgFormat)){
				String msg = URLEncoder.encode("图片格式错误", "UTF-8");
				return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("2", msg)+"')</script>";
			}
			// 重新生成文件名
			String imgDataName = UUID.randomUUID().toString();
			// 创建
			FileUtil.createIndexNewsImage(indexNewsPath, imgDataName, imgFormat, indexNewsImg);

			IndexNews indexNews = new IndexNews();
			indexNews.setContext(context);
			indexNews.setTitle(title);
			indexNews.setCourseID(courseID);
			indexNews.setImg("/"+imgDataName+imgFormat);
			indexNewsService.insert(indexNews);
			
			String msg = URLEncoder.encode("添加成功，请重新生成首页", "UTF-8");
			return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("1", msg)+"')</script>";
		}catch(Exception e){
			e.printStackTrace();
			return "<script>window.parent.ajaxFileUpload('"+JSONUtil.returnJson("3", null)+"')</script>";
		}
	}
	
	/**
	 * @description 删除一个首页新闻
	 * @author zhuziming
	 * @time 2018年10月26日 下午1:53:05
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delIndexNews.action")
	public String delIndexNews(int indexNewsID){
		try{
			indexNewsService.deleteByID(indexNewsID);
			return JSONUtil.returnJson("1", "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("2", "异常，请稍后在试");
		}
	}
	
	/**
	 * @desctiption 系统页列表
	 * @author zhuziming
	 * @time 2018年8月7日上午9:44:58
	 */
	@RequestMapping("/systemListPage.action")
	public String systemListPage(Model model){
		
		List<CourseType> courseTypeList = courseTypeService.getAll();// 科室列表
		List<PeoplePart> peoplePartList = peoplePartService.getAll();// 部位列表
		model.addAttribute("courseTypeList",courseTypeList); 
		model.addAttribute("peoplePartList",peoplePartList);
		InitUtil.iniSystem(model);
		model.addAttribute("methodName","systemListPage");// leftMenu页面中当前选中的参数
		return "manage/systemList";
	}
	/**
	 * @desctiption 创建人体部位导航页，用于对二级导航页面的重新生成
	 * @author zhuziming
	 * @param peoplePartID : 人体部位id
	 * @time 2018年8月8日上午9:18:37
	 */
	@RequestMapping("/createPeoplePartNav.action")
	@ResponseBody
	public String createPeoplePartNav(Model model, String peoplePartID){
		try{
			// 当前人体部位
			PeoplePart peoplePart = peoplePartService.getOneByID(Integer.valueOf(peoplePartID));
			// 课程列表
			List<Course> courseList = coursePeopleService.getCourseListByPeoplePartID(peoplePart.getId());
			// 人体部位列表
			List<PeoplePart> peoplePartList = peoplePartService.getAll(); // 人体部位列表
			FreeMarkerUtil.createPeoplePartHTML(courseList, peoplePartList, peoplePart);
			return JSONUtil.returnJson("1", "生成完毕");
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("3", "异常了");
		}
	}
	
	
	/**
	 * @desctiption 创建课程类型导航页，用于对二级导航页面的重新生成
	 * @author zhuziming
	 * @param courseTypeID : 课程类型id
	 * @time 2018年8月7日上午9:59:13
	 */
	@RequestMapping("/createCourseTypeNav.action")
	@ResponseBody
	public String createCourseTypeNav(Model model, String courseTypeID){
		try{
			// 当前课程类型
			CourseType courseType = courseTypeService.getOneByID(Integer.valueOf(courseTypeID));
			// 课程列表
			List<Course> courseList = courseService.getListByCourseTypeID(courseType.getId(), "click_volume", "DESC");
			// 课程类型列表
			List<CourseType> courseTypeList = courseTypeService.getAll();
			FreeMarkerUtil.createCourseTypeHTML(courseList,courseTypeList,courseType);
			return JSONUtil.returnJson("1", "生成完毕");
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("3", "异常了");
		}
	}
	
	/**
	 * @desctiption 创建首页，每个导航栏目查询点击量最高的12个
	 * @author zhuziming
	 * @time 2018年8月7日下午2:45:22
	 */
	@RequestMapping("/createIndex.action")
	@ResponseBody
	public String createIndex(){
		try{
			List<IndexNews> indexNewslist = indexNewsService.getListByLinkSort("id", "DESC", 10, 0);
			List<Map<String,String>> indexNewsMapList = new ArrayList<Map<String,String>>();
			for (IndexNews indexNews : indexNewslist) {
				Course course = courseService.selectByID(indexNews.getCourseID());
				if(course==null){
					continue;
				}
				Map<String,String> indexNewsMap = new HashMap<String,String>();
				indexNewsMap.put("id", indexNews.getId()+"");
				indexNewsMap.put("picture", indexNews.getImg()); // 轮播图
				indexNewsMap.put("title", indexNews.getTitle());
				indexNewsMap.put("context", indexNews.getContext());
				indexNewsMap.put("webPath", course.getCoursePath()); //网页地址
				indexNewsMapList.add(indexNewsMap);
			}
			
			// 得到所有的科室，并在首页把每一个科室的课程列出
			List<CourseType> courseTypeList = courseTypeService.getAll();// 科室列表
			Map<String,List<Course>> map = new HashMap<String,List<Course>>();
			for(CourseType courseType:courseTypeList){
				map.put(courseType.getName(), courseService.getListByCourseTypeID(courseType.getId(), "id", "DESC"));
			}
			FreeMarkerUtil.createIndexHTML(courseTypeList,map,indexNewsMapList);
			return JSONUtil.returnJson("1", "生成完毕");
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("3", "异常了");
		}
	}
	
	/**
	 * @description 创建网站地图
	 * @author zhuziming
	 * @time 2018年8月13日 下午7:29:44
	 * @return
	 */
	@RequestMapping("/createWebMap.action")
	@ResponseBody
	public String createWebMap(){
		try{
			List<CourseType> courseTypeList = courseTypeService.getAll();// 科室列表
			List<PeoplePart> peoplePartList = peoplePartService.getAll();// 部位列表
			Map<String,List<Course>> map = new HashMap<String,List<Course>>();
			for(CourseType courseType:courseTypeList){
				map.put(courseType.getName(), courseService.getListByCourseTypeID(courseType.getId(), "id", "DESC"));
			}
			for(PeoplePart peoplePart:peoplePartList){
				map.put(peoplePart.getPartName(), coursePeopleService.getCourseListByPeoplePartID(peoplePart.getId()));
			}
			FreeMarkerUtil.createWebMapHTML(courseTypeList,peoplePartList,map);
			return JSONUtil.returnJson("1", "生成完毕");
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("3", "异常了");
		}
	}
	/**
	 * @description 创建百度推送链接xml文件
	 * @author zhuziming
	 * @time 2020年10月12日 下午3:36:01
	 * @return
	 */
	@RequestMapping("/createBaiduMapXml.action")
	@ResponseBody
	public String createBaiduMapXml(){
		try{
			List<CourseDetail> list = courseDetailService.getListByLimit("10000");
			FreeMarkerUtil.createWebMapXML(list);
			return JSONUtil.returnJson("1", "生成完毕");
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("3", "异常了");
		}
	}
	
	/**
	 * @desctiption 课程页列表
	 * @author zhuziming
	 * @param pageIndex:页数
	 * @param pageSize:一页有几条
	 * @time 2018年8月8日下午2:59:00
	 */
	@RequestMapping("/courseListPage.action")
	public String courseListPage(Model model,Integer pageIndex,Integer pageCount){
		// 如果没有传页数，默认第0页
		if(pageIndex==null){
			pageIndex=0;
		}
		// 如果没有传页条数，默认一页10条
		if(pageCount==null || pageCount==0){
			pageCount=10;
		}
		// 课程列表
		List<Map> courseListMap = courseService.getListLeftCourseType("click_volume","DESC",pageCount,pageIndex);
		Integer dataCount = courseService.getCountAll();
		Map<String,Integer> map = PagingUtil.beginPaging(pageIndex, pageCount,dataCount);
		model.addAttribute("padingHTML",PagingUtil.padingHTML(map.get("allPageSize"), map.get("dataCount"), map.get("pageIndex"), map.get("pageCount")));
		model.addAttribute("courseListMap", courseListMap);
		model.addAttribute("methodName","courseListPage"); // leftMenu页面中当前选中的参数
		InitUtil.iniSystem(model);
		return "manage/courseList";
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
			
			List<Map> courseListMap = courseService.getListLeftCourseType("click_volume","DESC",pageCount,pageIndex);
			String indexPath = PropertiesUtil.getValue("system.properties", "indexpath");
			StringBuffer dataList = new StringBuffer(); // 数据集合
			for (int i = 0; i < courseListMap.size(); i++) {
				Map  map= courseListMap.get(i);
				dataList.append("<tr>");
				dataList.append("<td scope=\"row\">"+map.get("id")+"</td>");
				dataList.append("<td>"+map.get("course_name")+"</td>");
				dataList.append("<td>"+map.get("account")+"</td>");
				dataList.append("<td>"+map.get("name")+"</td>");
				dataList.append("<td>"+map.get("click_volume")+"</td>");
				dataList.append("<td>");
				dataList.append("<a href=\""+indexPath+"/manage/setCustomerPage.action?courseID="+map.get("id")+"\" class=\"btn btn-info btn-sm\" role=\"button\">分配客服</a>");
				dataList.append("<a href=\"javascript:delCourse("+map.get("id")+")\" class=\"btn btn-danger btn-sm\" role=\"button\">删除</a>");
				dataList.append("</td>");
				dataList.append("</tr>");
			}
			// DateUtil.formatTimestampToStr(map.get("time"))
			StringBuffer padingHTML = new StringBuffer(); // 分页按钮集合
			Integer dataCount = courseService.getCountAll();
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
	 * @description 重新生成页面 全部
	 * @author zhuziming
	 * @time 2018年12月15日 上午11:58:20
	 * @return
	 */
	@RequestMapping("/updateHtmlAll.action")
	@ResponseBody
	public String updateHtmlAll(){
		try{
			List<Course> courseList = courseService.getAll();
			for (Course course2 : courseList) {
				String intro2 = course2.getIntro();
				if(intro2!=null && !intro2.equals("")){
					intro2 = intro2.replaceAll("\n", "<br/>"); // 把换行换成html <br/>
					intro2 = intro2.replaceAll(" ", "&nbsp"); // 把空格换成html &nbsp
					intro2 = intro2.replaceAll("\t", "&nbsp"); // 把制表符换成8个空格
					course2.setIntro(intro2); // 替换生成页面的介绍
				}
				
				// 生成html页面
				// 准备数据 课程
				Course course_ = course2;
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
			}
			return JSONUtil.returnJson("1", "更新完成");
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("3", "异常了");
		}
		
	}
	

	
	/**
	 * @desctiption 用户列表页面
	 * @author zhuziming
	 * @time 2018年8月10日上午9:06:19
	 */
	@RequestMapping("/userListPage.action")
	public String userListPage(Model model,Integer pageIndex,Integer pageCount){
		// 如果没有传页数，默认第0页
		if(pageIndex==null){
			pageIndex=0;
		}
		// 如果没有传页条数，默认一页10条
		if(pageCount==null || pageCount==0){
			pageCount=10;
		}
		List<User> userList = userService.getListByLinkSort("id", "DESC", pageCount, pageIndex);
		Integer dataCount = userService.getCountAll();
		Map<String,Integer> map = PagingUtil.beginPaging(pageIndex, pageCount,dataCount);
		model.addAttribute("padingHTML",PagingUtil.padingHTML(map.get("allPageSize"), map.get("dataCount"), map.get("pageIndex"), map.get("pageCount")));
		InitUtil.iniSystem(model);
		model.addAttribute("userList",userList);
		model.addAttribute("methodName","userListPage"); // leftMenu页面中当前选中的参数
		return "manage/userList";
	}
	
	/**
	 * @description ajax得到我的用户列表
	 * @author zhuziming
	 * @time 2018年6月24日 上午11:26:46
	 * @return
	 */
	@RequestMapping("/ajaxGetUserList.action")
	@ResponseBody
	public String ajaxGetUserList(HttpServletRequest request,HttpSession session){
		try{
			String indexpath = PropertiesUtil.getValue("system.properties", "indexpath");
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
			
			List<User> userList = userService.getListByLinkSort("id","DESC",pageCount,pageIndex);
			StringBuffer dataList = new StringBuffer(); // 数据集合
			for (int i = 0; i < userList.size(); i++) {
				User  user= userList.get(i);
				dataList.append("<tr>");
				dataList.append("<td scope=\"row\">"+user.getId()+"</td>");
				dataList.append("<td>"+user.getAccount()+"</td>");
				dataList.append("<td>"+user.getNickname()+"</td>");
				dataList.append("<td>"+user.getPhone()+"</td>");
				dataList.append("<td>"+user.getSex()+"</td>");
				dataList.append("<td><a href=\""+indexpath+"/manage/allocationRolePage.action?userid="+user.getId()+"\" class=\"btn btn-info btn-sm\" role=\"button\">分配角色</a></td>");
				dataList.append("</tr>");
			}
			// DateUtil.formatTimestampToStr(map.get("time"))
			StringBuffer padingHTML = new StringBuffer(); // 分页按钮集合
			Integer dataCount = userService.getCountAll();
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
	 * 
	 * @desctiption 分配角色页面
	 * @author zhuziming
	 * @throws Exception 
	 * @time 2018年8月10日上午11:03:55
	 */
	@RequestMapping("/allocationRolePage.action")
	public String allocationRolePage(Model model,Integer userid) throws Exception {
		User user = userService.selectByID(userid);
		List<Role> roleList = roleService.getAll();
		Map<String,Object> map = this.userService.listAuthByAccount(user.getAccount());
		Set<String> allRoles = (Set<String>)map.get("allRoles");
		for(Role role:roleList){
			for(String r:allRoles){
				if(role.getFlag().equals(r)){
					role.setFlag("1"); // 页面中，如果用户拥有此角色就勾选复选框，这里把值设为1
				}
			}
		}
		InitUtil.iniSystem(model);
		model.addAttribute("user",user);
		model.addAttribute("roleList",roleList);
		model.addAttribute("methodName","allocationRolePage"); // leftMenu页面中当前选中的参数
		return "manage/allocationRole";
	}
	
	/**
	 * @desctiption 更新角色
	 * @author zhuziming
	 * @time 2018年8月10日下午2:52:55
	 */
	@RequestMapping("/updateRole.action")
	@ResponseBody
	@Transactional
	public String updateRole(@RequestParam(value = "roles[]") String[] roles,String userID){
		try{
			userRoleService.deleteByUserID(Integer.valueOf(userID)); // 删除所有角色
			for(String roleID:roles){
				UserRole userRole = new UserRole();
				userRole.setUid(Integer.valueOf(userID));
				userRole.setRid(Integer.valueOf(roleID));
				userRoleService.insert(userRole); // 添加新角色
			}
			return JSONUtil.returnJson("1", "更新成功");
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("3", "异常了");
		}
	}
	
	/**
	 * @description 客服列表页
	 * @author zhuziming
	 * @time 2018年12月8日 下午6:39:59
	 * @return
	 */
	@RequestMapping("/setCustomerPage.action")
	public String customerListPage(Model model,Integer courseID){
		Customer customer = new Customer();
		customer.setCourseID(courseID);
		customer.setCancel(0);
		List<Customer> list = customerService.selectListByCourseIDAndCancel(customer);
		if(list!=null && list.size()>0){
			customer = list.get(0);
		}
		Map courseMap = courseService.getOneLeftCourseTypeByID(courseID);
		InitUtil.iniSystem(model);
		model.addAttribute("courseMap", courseMap);
		model.addAttribute("customer", customer);
		model.addAttribute("methodName","setCustomerPage"); // leftMenu页面中当前选中的参数
		return "manage/setCustomer";
	}
	
	/**
	 * @description 添加客服
	 * @author zhuziming
	 * @time 2018年12月9日 下午2:13:42
	 * @return
	 */
	@RequestMapping("/addCustomer.action")
	@ResponseBody
	public String addCustomer(Customer customer){
		customer.setCancel(0);
		List<Customer> list = customerService.selectListByCourseIDAndCancel(customer);
		if(list != null && list.size() > 0){
			return JSONUtil.returnJson("2", "记录已存在，请先删除");
		}
		if(customer.getUserID()==null || customer.getUserID()==0){
			return JSONUtil.returnJson("2", "请指明用户");
		}
		if(customer.getBeginTime()==null || "".equals(customer.getBeginTime())){
			return JSONUtil.returnJson("2", "开始时间不能为空");
		}
		if(customer.getEndTime()==null || "".equals(customer.getEndTime())){
			return JSONUtil.returnJson("2", "结束时间不能为空");
		}
		customer.setCreateTime(new Timestamp(System.currentTimeMillis()));
		customerService.insert(customer);
		return JSONUtil.returnJson("1", "添加成功");
	}
	
	/**
	 * @description 删除客服
	 * @author zhuziming
	 * @time 2018年12月9日 下午3:33:52
	 * @return
	 */
	@RequestMapping("/delCustomer.action")
	@ResponseBody
	public String delCustomer(Integer customerID){
		Customer customer = new Customer();
		customer.setId(customerID);
		customer.setCancel(1);
		customer.setRemoveTime(new Timestamp(System.currentTimeMillis()));
		customerService.update(customer);
		return JSONUtil.returnJson("1", "删除成功");
	}
	
	/**
	 * @description 删除课程
	 * @author zhuziming
	 * @time 2019年3月24日 下午5:52:13
	 * @return
	 */
	@RequestMapping("/delCourse.action")
	@ResponseBody
	public String delCourse(Integer courseID){
		try{
			courseService.deleteByID(courseID); // 删除课程表
			coursePeopleService.deleteByCourseID(courseID); // 删除人体部位表中的课程
			return JSONUtil.returnJson("1", "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("3", "异常了");
		}
		
	}
	
}

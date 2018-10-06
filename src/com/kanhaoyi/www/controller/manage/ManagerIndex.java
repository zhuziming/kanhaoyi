package com.kanhaoyi.www.controller.manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.kanhaoyi.www.model.Course;
import com.kanhaoyi.www.model.CourseType;
import com.kanhaoyi.www.model.PeoplePart;
import com.kanhaoyi.www.model.Role;
import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.model.UserRole;
import com.kanhaoyi.www.service.ICoursePeopleService;
import com.kanhaoyi.www.service.ICourseService;
import com.kanhaoyi.www.service.ICourseTypeService;
import com.kanhaoyi.www.service.IPeoplePartService;
import com.kanhaoyi.www.service.IRoleService;
import com.kanhaoyi.www.service.IUserRoleService;
import com.kanhaoyi.www.service.IUserService;
import com.kanhaoyi.www.util.DateUtil;
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
	
	/**
	 * @desctiption 后台首页
	 * @author zhuziming
	 * @time 2018年8月7日上午9:44:47
	 */
	@RequestMapping("/indexPage.action")
	public String indexPage(Model model){
		InitUtil.iniSystem(model);
		return "manage/index";
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
	 * @desctiption 创建首页，每个导航栏目查询点击量最高的6个
	 * @author zhuziming
	 * @time 2018年8月7日下午2:45:22
	 */
	@RequestMapping("/createIndex.action")
	@ResponseBody
	public String createIndex(){
		try{
			// 查点击量最高的6个
			List<Course> maxClickList = courseService.getListByLinkSort("click_volume", "DESC", 0,6);
			// 查时间最新的6个
			List<Course> newTimeList  = courseService.getListByLinkSort("time", "DESC", 0,6);

			FreeMarkerUtil.createIndexHTML(maxClickList,newTimeList);
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
			StringBuffer dataList = new StringBuffer(); // 数据集合
			for (int i = 0; i < courseListMap.size(); i++) {
				Map  map= courseListMap.get(i);
				dataList.append("<tr>");
				dataList.append("<td scope=\"row\">"+map.get("id")+"</td>");
				dataList.append("<td>"+map.get("course_name")+"</td>");
				dataList.append("<td>"+map.get("account")+"</td>");
				dataList.append("<td>"+map.get("name")+"</td>");
				dataList.append("<td>"+map.get("click_volume")+"</td>");
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
	
	
	
}

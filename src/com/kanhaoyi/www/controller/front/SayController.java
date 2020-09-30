package com.kanhaoyi.www.controller.front;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.kanhaoyi.www.model.CourseComment;
import com.kanhaoyi.www.model.CourseCommentPraise;
import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.service.ICourseCommentPraiseService;
import com.kanhaoyi.www.service.ICourseCommentService;
import com.kanhaoyi.www.service.IUserService;
import com.kanhaoyi.www.util.InitUtil;
import com.kanhaoyi.www.util.JSONUtil;
import com.kanhaoyi.www.util.PropertiesUtil;

/**
 * @description 用户留言类，包含所有说话的操作
 * @author zhuziming
 * @time 2018年7月22日 下午4:38:21
 */
@Controller
@RequestMapping("/front")
public class SayController {

	@Resource
	private IUserService userService;
	@Resource
	private ICourseCommentService courseCommentService;
	@Resource
	private ICourseCommentPraiseService courseCommentPraiseService;
	
	/**
	 * @description 对课程留言
	 * @author zhuziming
	 * @param courseID 课程id
	 * @param say 要说的话
	 * @time 2018年7月22日 下午4:39:43
	 * @return
	 */
	@RequestMapping("/courseSay.action")
	@ResponseBody
	public String courseSay(HttpServletRequest request,HttpSession session){
		try{
			User user = userService.getSessionUser(session);
			if(user==null){
				return JSONUtil.returnJson("2", "请登录后留言");
			}
			String courseID = request.getParameter("courseID"); // 课程id
			String say =  request.getParameter("say"); // 要说的话
			if(courseID==null){
				return JSONUtil.returnJson("2", "非法操作");
			}
			if(say==null || say.length()==0){
				return JSONUtil.returnJson("2", "请输入留言内容");
			}
			if(say.length()>2000){
				return JSONUtil.returnJson("2", "己超出最大字符数");
			}
			
			say = say.replaceAll("\n", "<br/>"); // 把换行换成html <br/>
			say = say.replaceAll(" ", "&nbsp"); // 把空格换成html &nbsp
			say = say.replaceAll("\t", "&nbsp"); // 把制表符换成8个空格
			
			
			CourseComment courseComment = new CourseComment();
			courseComment.setCourseID(Integer.valueOf(courseID));
			courseComment.setContent(say);
			courseComment.setUserID(user.getId());
			courseComment.setPraise(0);
			courseComment.setTime(new Timestamp(new Date().getTime()));
			courseCommentService.insert(courseComment);
			Map<String, Object> map = courseCommentService.getOneByCourseCommentId(courseComment.getId());
			List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
			list.add(map);
			StringBuffer sb = courseCommentService.getHtml(list);
			JSONObject joParam = new JSONObject();
			joParam.put("courseCommentHtml", sb);
			return JSONUtil.returnJson("1", "留言成功",joParam);
		}catch(Exception e){
			e.printStackTrace();
			return JSONUtil.returnJson("3", "程序异常");
		}
	}
	
	/**
	 * @description 加载最新留言
	 * @author zhuziming
	 * @time 2018年7月22日 下午6:14:01
	 * @return
	 */
	@RequestMapping("/loadNewComment.action")
	@ResponseBody
	public String loadNewComment(HttpServletRequest request){
		
		String courseID = request.getParameter("courseID");
		String commentID =request.getParameter("commentID");
		if(commentID==null ){commentID="0";}
		List<Map<String,Object>> list = courseCommentService.getListByCourseID(Integer.valueOf(courseID),Integer.valueOf(commentID),5);
		StringBuffer sb = courseCommentService.getHtml(list);
		if(sb.length()==0){
			return JSONUtil.returnJson("2", "没有数据了");
		}else{
			Map<String,Object> map = list.get(list.size()-1); // 取最后一个评论的id，为下一次加载做准备
			JSONObject joParam = new JSONObject();
			joParam.put("commentID", map.get("id"));
			return JSONUtil.returnJson("1", sb.toString(),joParam);
		}
	}
	
	/**
	 * @description 评论赞，只能增加赞，不能减少，点赞的人不用登陆
	 * @author zhuziming
	 * @time 2018年7月28日 下午5:38:09
	 * @return
	 */
	@RequestMapping("/praise.action")
	@ResponseBody
	public String praise(HttpServletRequest request,HttpSession session){
		try{
			String commentID = request.getParameter("commentID"); // 评论id

			courseCommentService.praiseAdd(Integer.valueOf(commentID));
			return JSONUtil.returnJson("1", "赞美成功");

		}catch(Exception e){
			return JSONUtil.returnJson("3", "异常了");
		}
		
	}
	/**
	 * @description 测试首页，用完删除
	 * @author zhuziming
	 * @time 2018年7月29日 下午3:56:59
	 * @return
	 */
	@RequestMapping("/index.action")
	public String frontIndex(Model model,HttpSession session){
		
		InitUtil.iniSystem(model);
		return "front/index";
	}
	/**
	 * @desctiption 得到昵称，消息数
	 * @author zhuziming
	 * @time 2018年4月28日上午10:18:22
	 */
	@RequestMapping("/getusername.action")
	@ResponseBody
	public String getUsername(HttpSession session){
		//ajax返回格式{success:'',msg:''}
		//success取值[1:成功][2:失败][3:异常]
		//		msg只有在success为2时，才有值
		
		Object account = SecurityUtils.getSubject().getPrincipal();
		if(account==null){
			return JSONUtil.returnJson("2", "");
		}else{
			String infoNum = userService.getSessionInfoNum(session); // 消息数
			String nickName= userService.getSessionNickname(session); // 昵称
			JSONObject jo = new JSONObject();
			jo.put("nickName", nickName);
			jo.put("infoNum", infoNum);
			return JSONUtil.returnJson("1", jo.toString());
		}
	}
	
}

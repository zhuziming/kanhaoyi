package com.kanhaoyi.www.controller.teacher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.model.VideoGroup;
import com.kanhaoyi.www.service.IUserService;
import com.kanhaoyi.www.service.IVideoGroupService;
import com.kanhaoyi.www.util.InitUtil;
import com.kanhaoyi.www.util.JSONUtil;

/**
 * @discription 老师操作类
 * @author zhuziming
 * @time 2018年5月7日上午11:09:54
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Resource
	private IUserService userService;
	@Resource
	private IVideoGroupService videoGroupService;
	
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
		model.addAttribute("user",user);  // 昵称
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
	 * @description 视频文件上传
	 * @author zhuziming
	 * @time 2018年6月3日 下午6:27:32
	 */
	@RequestMapping("/videoFileUpload.action")
	@ResponseBody
	public String videoFileUpload(@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest request){
		for(int i = 0;i<files.length;i++){  
            if(!files[i].isEmpty()){  
                int pre = (int) System.currentTimeMillis();  
                try {  
                    //拿到输出流，同时重命名上传的文件  
                    FileOutputStream os = new FileOutputStream("d:/" + new Date().getTime() + files[i].getOriginalFilename());  
                    //拿到上传文件的输入流  
                    FileInputStream in = (FileInputStream) files[i].getInputStream();  
                    //以写字节的方式写文件  
                    int b = 0;  
                    while((b=in.read()) != -1){  
                        os.write(b);  
                    }  
                    os.flush();  
                    os.close();  
                    in.close();  
                    int finaltime = (int) System.currentTimeMillis();  
                    System.out.println(finaltime - pre);  
                      
                } catch (Exception e) {  
                    e.printStackTrace();  
                    System.out.println("上传出错");  
                }  
            }  
        }  
		
		return "<script>window.parent.ajaxFileUpload(5)</script>";
	}
	
}

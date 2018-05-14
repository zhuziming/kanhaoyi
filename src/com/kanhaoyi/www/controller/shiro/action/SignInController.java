package com.kanhaoyi.www.controller.shiro.action;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.kanhaoyi.www.model.UserModel;
import com.kanhaoyi.www.service.UserService;
import com.kanhaoyi.www.util.CodeUtil;
import com.kanhaoyi.www.util.JSONUtil;

import org.springframework.util.StringUtils;

/**
 * @discription 登录注册页面
 * @author zhuziming
 * @time 2018年4月17日上午9:58:01
 */
@Controller
public class SignInController {
	
	@Resource
	UserService userService;
	
	/**
	 * @desctiption 登录
	 * @author zhuziming
	 * @time 2018年4月18日上午11:39:43
	 */
	@RequestMapping(value="/signIn.action")
	@ResponseBody
	public String signIn(HttpServletRequest request){
		String sessionCode = String.valueOf(request.getSession().getAttribute("code"));
		String securityCode= request.getParameter("securityCode"); 
		if(sessionCode.equalsIgnoreCase(securityCode)){
			
		}
		return "";
	}
	
	
	/**
	 * @desctiption 注册
	 * @author zhuziming
	 * @throws IOException 
	 * @time 2018年4月18日上午11:42:04
	 */
	@RequestMapping(value="/signUp.action")
	@ResponseBody
	public String register(UserModel userModel) throws IOException{
		String account = userModel.getAccount();
		if(StringUtils.isEmpty(account)){
			return "<script type='text/javascript'>window.top.submitTar('"+URLEncoder.encode(JSONUtil.returnJson("2", "账号不能为空"),"UTF-8")+"')</script>";
		}
		if(userService.selectUserIsExist(account)){
			return "<script type='text/javascript'>window.top.submitTar('"+URLEncoder.encode(JSONUtil.returnJson("2", "已存在的账号"),"UTF-8")+"')</script>";
		}
		userModel.setTime(new Timestamp(System.currentTimeMillis()));
		userService.insert(userModel);
		
		return "<script type='text/javascript'>window.top.submitTar('"+URLEncoder.encode(JSONUtil.returnJson("1", null),"UTF-8")+"')</script>";
	}
	
	/**
	 * @desctiption 生成验证码
	 * @author zhuziming
	 * @time 2018年4月18日上午11:18:03
	 */
	@RequestMapping(value="/getCode.action")
	public void createCode(HttpServletRequest request, HttpServletResponse response){
		// 调用工具类生成的验证码和验证码图片
        Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();
        // 将四位数字的验证码保存到Session中。
        HttpSession session = request.getSession();
        session.setAttribute("code", codeMap.get("code").toString());
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -1);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            sos = response.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
}

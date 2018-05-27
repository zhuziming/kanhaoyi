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

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.service.UserService;
import com.kanhaoyi.www.util.CodeUtil;
import com.kanhaoyi.www.util.InitUtil;
import com.kanhaoyi.www.util.JSONUtil;
import com.kanhaoyi.www.util.MyPasswordEncrypt;

import org.springframework.util.StringUtils;

/**
 * @discription 注册页面
 * @author zhuziming
 * @time 2018年4月17日上午9:58:01
 */
@Controller
public class SignUpController {
	
	@Resource
	UserService userService;
	
	/**
	 * @desctiption 注册页
	 * @author zhuziming
	 * @time 2018年4月18日上午11:39:43
	 */
	@RequestMapping(value="/signUpPage.action")
	public String signInPage(HttpServletRequest request,Model model){
		
		
		InitUtil.iniSystem(model);
		return "front/sign_up";
	}
	
	
	/**
	 * @desctiption 注册
	 * @author zhuziming
	 * @throws IOException 
	 * @time 2018年4月18日上午11:42:04
	 */
	@RequestMapping(value="/signUp.action")
	public String register(User user, Model model, HttpServletRequest request) throws IOException{
		InitUtil.iniSystem(model);
		model.addAttribute("userModel",user);
		String reqCode = request.getParameter("code");
		String sesCode = request.getSession().getAttribute("code").toString();
		if(!reqCode.equalsIgnoreCase(sesCode)){
			model.addAttribute("bugMsg","验证码错误");
			return "front/sign_up";
		}
		
		String account = user.getAccount();
		if(StringUtils.isEmpty(account)){
			model.addAttribute("bugMsg","账号不能为空");
			return "front/sign_up";
		}
		if(userService.selectUserIsExist(account)){
			model.addAttribute("bugMsg","已存在的账号");
			return "front/sign_up";
		}
		String password = user.getPassword();
		if(StringUtils.isEmpty(password)){
			model.addAttribute("bugMsg","密码不能为空");
			return "front/sign_up";
		}
		user.setPassword(MyPasswordEncrypt.encryptPassword(user.getPassword()));
		user.setTime(new Timestamp(System.currentTimeMillis()));
		userService.insert(user);
		// 注册完成后，直接登录
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(account,password);
		subject.login(token);
		return "front/sign_up_success";
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

package com.kanhaoyi.www.controller.shiro.action;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.model.UserRole;
import com.kanhaoyi.www.model.WeiXinToken;
import com.kanhaoyi.www.service.IUserRoleService;
import com.kanhaoyi.www.service.IUserService;
import com.kanhaoyi.www.util.HTTPUtil;
import com.kanhaoyi.www.util.InitUtil;
import com.kanhaoyi.www.util.WeiXinUtil;

@Controller
public class ShiroLoginAction {
	
	@Resource
	private IUserService userService;
	@Resource
	IUserRoleService userRoleService;
	
	/**
	 * @desctiption 登录页
	 * @author zhuziming
	 * @time 2018年4月28日上午9:12:21
	 */
	@RequestMapping("/loginUrl.action")
	public String loginUrl(Model model,HttpServletRequest request){
		System.out.println("登录页");
		// 取出抛出的异常
		Object exceptionObj = request.getAttribute("shiroLoginFailure");
		if(exceptionObj != null){
			if(IncorrectCredentialsException.class.getName().equals(exceptionObj.toString())){
				request.setAttribute("bugMsg", "密码错误");
			}else if(UnknownAccountException.class.getName().equals(exceptionObj.toString())){
				request.setAttribute("bugMsg", "该用户不存在");
			}
		}
		InitUtil.iniSystem(model);
		return "front/sign_in";
	}
	@RequestMapping("/loginPage.action")
	public String loginPage(Model model){
		InitUtil.iniSystem(model);
		return "front/sign_in";
	}
	
	/**
	 * @desctiption 登录出错页
	 * @author zhuziming
	 * @time 2018年4月28日上午9:12:33
	 */
	@RequestMapping("/unauthUrl")
	public ModelAndView unauthUrl(){
		System.out.println("登录出错页 /unauthUrl");
		return new ModelAndView("role");
	}
	/**
	 * @desctiption 登录成功
	 * @author zhuziming
	 * @time 2018年4月28日上午9:12:46
	 */
	@RequestMapping("/successUrl")
	public String successUrl(Model model, HttpSession session){
		System.out.println("登录成功跳转页");
		userService.getSessionUser(session);
		
		InitUtil.iniSystem(model);
		return "redirect:index.html";
	}
	
	/**
	 * @description 微信授权重定向的地址
	 * @author zhuziming
	 * @time 2019年2月5日 下午2:45:10
	 * @return
	 */
	@RequestMapping("/weixinRedirect.action")
	public String weixinRedirect(HttpServletRequest request){
		try{
			/* 第一步：得到code，若用户拒绝授权则不会有code
			code : 061WFXzm15Zr9p0i5Uym1BwmAm1WFXz1
			state : STATE
			*/
			String code = request.getParameter("code");
			String state = request.getParameter("state");
			if(code==null || code.equals("")){
				return "redirect:index.html";
			}
			
			/*
			 第二步：通过code获取access_token
			 {
			 	接口调用凭证
			 	"access_token":"18_DPAb9SnjSKKagLZ-5re-WrciBn4K_vby3R3r42fuCeqGVSeogpAlOYRJjLAdER5F5_CysNklsA7g_nEQr4P75A",
			 	接口调用凭证超时时间，单位（秒）
			 	"expires_in":7200,
			 	用户刷新access_token
			 	"refresh_token":"18_TlVmax0ZfUnrurPoPwyAmeVmRkABfBlUGn89BlOeDJZxGXfehvnBzhrfBN_z34jEQuMgtXyhaqrHG790zhfJmA",
			 	授权用户唯一标识
			 	"openid":"oLFgI53HSP8RwGUAdaUuvgb9x3FE",
			 	用户授权的作用域，使用逗号（,）分隔
			 	"scope":"snsapi_login",
			 	当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段。
			 	"unionid":"obuRDxF51lsYf-9QBbS7vLN4SAqs"
			 }
			 
			 */
			String getTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
			String getTokenParam = "appid="+WeiXinUtil.appid+"&secret="+WeiXinUtil.appSecret+"&code="+code+"&grant_type=authorization_code";
			String tokenResult = HTTPUtil.httpsRequest(getTokenUrl, "GET", getTokenParam);
			JSONObject tokenJsonResult = JSONObject.parseObject(tokenResult);
			
			/* 第三步：获取用户信息
			 {
			 	"openid":"oLFgI53HSP8RwGUAdaUuvgb9x3FE",
			 	"nickname":"dinner",
			 	"sex":1,
			 	"language":"zh_CN",
			 	"city":"Zhengzhou",
			 	"province":"Henan",
			 	"country":"CN",
			 	"headimgurl":"http:\/\/thirdwx.qlogo.cn\/mmopen\/vi_32\/Q0j4TwGTfTJBw3QsIfF3XHchicDic6zhSmP9m818vhc1X1M8hsRsB2N9wPndfhs9YpWcwG0bYr2SZtrNeSLHMgDQ\/132",
			 	"privilege":[],
			 	"unionid":"obuRDxF51lsYf-9QBbS7vLN4SAqs"}
			 * */
			
			String getInfoUrl = "https://api.weixin.qq.com/sns/userinfo";
			String getInfoParam = "access_token="+tokenJsonResult.getString("access_token")+"&openid="+tokenJsonResult.getString("openid");
			String getInfoResult = HTTPUtil.httpsRequest(getInfoUrl, "GET", getInfoParam);
			JSONObject infoJsonResult = JSONObject.parseObject(getInfoResult);
			String openid = infoJsonResult.getString("openid");
			String nickname = infoJsonResult.get("nickname").toString();
			String sex = infoJsonResult.get("sex").toString(); // 1为男性，2为女性
			String unionid = infoJsonResult.get("unionid").toString();
			
			User user = userService.getUserByOnlyID(unionid);
			if(user==null){
				User user_i = new User();
				user_i.setNickname(nickname);
				if("1".equals(sex)){
					user_i.setSex("男");
				}else if("2".equals(sex)){
					user_i.setSex("女");
				}else{
					user_i.setSex("男");
				}
				user_i.setOnlyID(unionid);
				user_i.setSource("wx");
				/*
				 * 因为shiro在查询权限时必须要有账号，所以这里给出微信的openid为账户
				 * unionid为密码，密码不做md5处理，防止用户知道自已的unionid后以密码方式登录网站
				 * 以后如果有绑定手机号的操作可以对account字段更换
				 * */
				user_i.setAccount(openid);
				user_i.setPassword(unionid);
				user_i.setInfoNum(0);
				user_i.setTime(new Timestamp(System.currentTimeMillis()));
				user_i.setPicture("/userDefault.jpg");
				user_i.setMoney(0.00);
				userService.insert(user_i);
				
				UserRole ur = new UserRole(); // 为用户加上访问个人中心的权限
				ur.setRid(1);
				ur.setUid(user_i.getId());
				userRoleService.insert(ur);
				
				HttpSession session = ((HttpServletRequest) request).getSession();
				session.setAttribute("user", user_i); // 把用户放入session
			}else{
				// 如果微信名称与数据库中不一致，则修改数据库。性别暂不判断
				if(!nickname.equals(user.getNickname())){ 
					user.setNickname(nickname);
					if("1".equals(sex)){
						user.setSex("男");
					}else if("2".equals(sex)){
						user.setSex("女");
					}
					userService.updateNicknameAndSex(user);
				}
				HttpSession session = ((HttpServletRequest) request).getSession();
				session.setAttribute("user", user); // 把用户放入session
			}
			// 登录shiro
			WeiXinToken token = new WeiXinToken();
			token.setUsername(openid);
			token.setPassword(unionid.toCharArray());
			token.setRememberMe(true);
			SecurityUtils.getSubject().login(token);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:index.html";
	}
	
}

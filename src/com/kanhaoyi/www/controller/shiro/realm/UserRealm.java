package com.kanhaoyi.www.controller.shiro.realm;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.kanhaoyi.www.model.UserModel;
import com.kanhaoyi.www.service.UserService;
import com.kanhaoyi.www.util.MyPasswordEncrypt;

// @Component
public class UserRealm extends AuthorizingRealm {
	
	@Resource
	UserService userService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		 System.out.println("用户角色与权限****");
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
		try{
			Map<String,Object> map = this.userService.listAuthByAccount(username);
			Set<String> allRoles = (Set<String>)map.get("allRoles");
			Set<String> allActions = (Set<String>)map.get("allActions");
			// 加载所有角色
			auth.setRoles(allRoles);
			// 加载所有权限
			auth.setStringPermissions(allActions);
		}catch(Exception e){
			e.printStackTrace();
		}
		// 进行业务层处理
		return auth;
	}
	
	
	/**
	 * 验证先执行
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("用户登录认证****");
		// 登录认证 得到用户名
		String account = (String) token.getPrincipal();  
		// 取得用户完整信息 自定义业务实现
		UserModel um = null;
		try {
			um = this.userService.getUserByAccount(account);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(um==null){
			throw new UnknownAccountException("该用户不存在");
		}else{
			// 得到密码
			String password = MyPasswordEncrypt.encryptPassword(new String((char[])token.getCredentials()));
			// 比较密码是否正确
			if(um.getPassword().equals(password)){
				// 返回用户对象
				AuthenticationInfo auth = new SimpleAuthenticationInfo(account,password,"member");
				return auth;
			}else{
				throw new IncorrectCredentialsException("密码错误");
			}
		}
	}

}

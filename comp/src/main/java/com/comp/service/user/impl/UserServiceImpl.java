package com.comp.service.user.impl;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.IncorrectCredentialsException;
//import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.comp.dao.user.UserDao;
import com.comp.entities.User;
import com.comp.service.user.UserService;

/**
 * 项目名： 创建时间：2017-3-18 创建人：Aobo 类名：UserRoleServiceImpl
 * 所属包名：com.comp.service.userrole.impl 项目名称：comp 类功能描述：
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	// @Qualifier("userDao")
	private UserDao userDao;

	@Override
	public User findByUserName(Map<String, Object> whereCases) {
		return userDao.findByUserName(whereCases);
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public String loginUser(User user) {
		if (isRelogin(user)){
			return "success"; // 如果已经登陆，无需重新登录
		}
		return shiroLogin(user); // 调用shiro的登陆验证
	}
	/**
	 * 使用shiro登录验证
	 * @param user
	 * @return
	 */
	@Override
	public String shiroLogin(User user) {
		// 组装token
		UsernamePasswordToken token = new UsernamePasswordToken(
				user.getUserName(), user.getPassword().toCharArray(), null);
		token.setRememberMe(true);
		//shiro登陆验证
		try {
			SecurityUtils.getSubject().login(token);
		} catch (UnknownAccountException ex) {
			return "用户不存在或者密码错误！";
		} catch (IncorrectCredentialsException ex) {
			return "用户不存在或者密码错误！";
		} catch (AuthenticationException ex) {
			return ex.getMessage(); // 自定义报错信息
		} catch (Exception ex) {
			ex.printStackTrace();
			return "内部错误，请重试！";
		}
		return "success";
	}

	/**
	 * 检查是否需要重新登录
	 * @param user
	 * @return
	 */
	@Override
	public boolean isRelogin(User user) {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			return true; // 参数未改变，无需重新登录，默认为已经登录成功
		}
		return false; // 需要重新登陆
	}

}

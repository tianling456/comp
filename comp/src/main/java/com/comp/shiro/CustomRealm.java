package com.comp.shiro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;

//import com.base.util.shiro.ShiroUtil;
import com.base.util.page.PageView;
import com.comp.entities.Menu;
import com.comp.entities.User;
import com.comp.service.menu.MenuService;
import com.comp.service.user.UserService;

/**
 * 项目名： 创建时间：2016-7-11 创建人：Aobo 类名：CustomRealm 所属包名：com.comp.shiro 项目名称：comp
 * 类功能描述：
 */
public class CustomRealm extends AuthorizingRealm {
	@Autowired
	//@Qualifier("userService")
	private UserService userService;
	@Autowired
	//@Qualifier("menuService")
	private MenuService menuService;
	// 设置realm的名称
	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}

	/**
	 * 用户授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// 从 principals获取主身份信息
		// 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
		User user = (User) principals.getPrimaryPrincipal();
		Map<String, Object> whereCases = new HashMap<String, Object>();
		whereCases.put("userName", user.getUserName());
		// 根据身份信息获取权限信息
		// 从数据库获取到权限数据
		List<Menu> menuList = null;
		List<String> permissionList = new ArrayList<String>();
		try {
			PageView<Menu> page = new PageView<Menu>();
			page = menuService.queryMenuForList(whereCases,page);
			for(Menu menu:menuList){
				permissionList.add(menu.getUrl());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 查到权限数据，返回授权信息(要包括 上边的permissions)
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(null);
		return simpleAuthorizationInfo;
	}

	/**
	 * 用户认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
//		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
//		char[] password = upToken.getPassword();
		Map<String, Object> whereCasess = new HashMap<String, Object>();
		whereCasess.put("userName", username);
		User user = userService.findByUserName(whereCasess);
		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}
//		Subject currentUser = SecurityUtils.getSubject();
		 //判断用户是否可用
		if (Boolean.TRUE.equals(user.getAvailable().equals("Y")?false:true)) {
			throw new LockedAccountException(); // 帐号锁定
		}
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user, // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getSalt()), this.getName());
		return authenticationInfo;
	}
}

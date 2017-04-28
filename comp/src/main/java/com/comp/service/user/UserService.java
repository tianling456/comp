package com.comp.service.user;

import java.util.Map;

import com.comp.entities.User;


/**
 *项目名：
 *创建时间：2017-3-18
 *创建人：Aobo
 *类名：UserRoleService
 *所属包名：com.comp.service.userrole
 *项目名称：comp
 *类功能描述：
 */
public interface UserService {
	public User findByUserName(Map<String,Object> whereCases);
	public String loginUser(User user);
	public String shiroLogin(User user);
	public boolean isRelogin(User user);
}


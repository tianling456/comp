package com.comp.dao.user;

//import java.util.List;
import java.util.Map;

import com.comp.entities.User;

/**
 *项目名：
 *创建时间：2017-3-18
 *创建人：Aobo
 *类名：UserRoleDao
 *所属包名：com.comp.dao.userrole
 *项目名称：comp
 *类功能描述：
 */
public interface UserDao {
	public User findByUserName(Map<String,Object> whereCases);
}


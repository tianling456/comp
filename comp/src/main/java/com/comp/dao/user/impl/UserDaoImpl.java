package com.comp.dao.user.impl;

//import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.base.mybatis.impl.MybatisDaoImpl;
import com.comp.dao.user.UserDao;
import com.comp.entities.User;

/**
 *项目名：
 *创建时间：2017-3-18
 *创建人：Aobo
 *类名：UserRoleDaoImpl
 *所属包名：com.comp.dao.userrole.impl
 *项目名称：comp
 *类功能描述：
 */
@Component("userDao")
public class UserDaoImpl extends MybatisDaoImpl<User,String> implements UserDao{
	@Override
	public User findByUserName(Map<String,Object> whereCases) {
		return super.findForObject("findByUserName", whereCases);
	}
}


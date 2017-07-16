package com.comp.dao.menu.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.base.mybatis.impl.MybatisDaoImpl;
import com.base.util.page.PageView;
import com.comp.dao.menu.MenuDao;
import com.comp.entities.Menu;

/**
 *项目名：
 *创建时间：2017-3-18
 *创建人：Aobo
 *类名：MenuDaoImpl
 *所属包名：com.comp.dao.menu.impl
 *项目名称：comp
 *类功能描述：
 */
@Component("menuDao")
public class MenuDaoImpl extends MybatisDaoImpl<Menu,String> implements MenuDao{
	@Override
	public List<Menu> queryMenuForList(Map<String, Object> whereCases){
		return super.queryForList("queryMenuByUserId", whereCases);
	}
}


package com.comp.service.menu.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.base.util.page.PageView;
import com.comp.dao.menu.MenuDao;
import com.comp.entities.Menu;
import com.comp.service.menu.MenuService;

/**
 *项目名：
 *创建时间：2017-3-18
 *创建人：Aobo
 *类名：MenuServiceImpl
 *所属包名：com.comp.service.menu.impl
 *项目名称：comp
 *类功能描述：
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService{
	@Autowired
	//@Qualifier("menuDao")
	private MenuDao menuDao;

	@Override
	public PageView<Menu> queryMenuForList(Map<String, Object> whereCases,PageView<Menu> page){
		whereCases.put("page", page);
		List<Menu> list= menuDao.queryMenuForList(whereCases);
		page.setRecordForList(list);
		return page;
	}
}


package com.comp.service.menu;

import java.util.List;
import java.util.Map;

import com.base.util.page.PageView;
import com.comp.entities.Menu;

/**
 *项目名：
 *创建时间：2017-3-18
 *创建人：Aobo
 *类名：MenuService
 *所属包名：com.comp.service.menu
 *项目名称：comp
 *类功能描述：
 */
public interface MenuService {
	public PageView<Menu> queryMenuForList(Map<String,Object> whereCases,PageView<Menu> page);
}


package com.comp.web.controller.menu;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.util.common.Common;
import com.base.util.page.PageView;
import com.comp.entities.Menu;
import com.comp.service.menu.MenuService;

/**
 *项目名：
 *创建时间：2017-3-18
 *创建人：Aobo
 *类名：MenuController
 *所属包名：com.comp.web.controller.menu
 *项目名称：comp
 *类功能描述：
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private MenuService menuService;
	public String queryMenuForList(HttpServletRequest request,HttpServletResponse response){
		PageView<Menu> page = null;
		String pageNow = request.getParameter("pageNow");
		if(Common.isEmpty(pageNow)){
			page = new PageView<Menu>(1);
		}else{
			page = new PageView<Menu>(Integer.parseInt(pageNow));
		}
		Map<String,Object> whereCases = new HashMap<String,Object>();
		whereCases.put("page", page);
		menuService.queryMenuForList(whereCases, page);
		request.setAttribute("page", page);
		return "menuList";
	}
}


package com.comp.web.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *项目名：
 *创建时间：2017-6-20
 *创建人：Aobo
 *类名：AdminController
 *所属包名：com.comp.web.controller.admin
 *项目名称：comp
 *类功能描述：
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "/admin/index";
	}
}


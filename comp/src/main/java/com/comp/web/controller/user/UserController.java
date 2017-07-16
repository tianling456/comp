package com.comp.web.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.jfree.data.UnknownKeyException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.exception.controller.ControllerException;
//import com.base.util.shiro.ShiroUtil;
import com.comp.entities.User;
import com.comp.service.user.UserService;

/**
 *项目名：
 *创建时间：2017-3-18
 *创建人：Aobo
 *类名：UserController
 *所属包名：com.comp.web.controller.user
 *项目名称：comp
 *类功能描述：
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	//@Qualifier("userService")
	private UserService userService;
	/**
	 * 登录校验
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/checkLogin",method=RequestMethod.POST)
	public String checkLogin(HttpServletRequest request,HttpServletResponse response){
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String kaptchaReceived = (String) request.getParameter("kaptchaReceived");
		Map<String,Object> whereCases = new HashMap<String,Object>();
		whereCases.put("userName", username);
		whereCases.put("password", password);
		User user = userService.findByUserName(whereCases);
		String message = userService.loginUser(user);
		//校验验证码是否对
		String kaptchaExpected = (String) request.getSession().getAttribute(  
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);  
		if (kaptchaReceived == null || !kaptchaReceived.equals(kaptchaExpected)) {  
            return "kaptcha_error";//返回验证码错误
        }  
		if("success".equals(message)){
			request.setAttribute("userName", username);
			request.setAttribute("password", password);
			request.setAttribute("user", user);
			return "/index/index";
		}else{
			request.setAttribute("msg", "failure");
			return "/user/login";
		}
	}
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "/user/login";
	}
	/**
	 * 注册用户
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/register")
	public String register(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "/user/register";
	}
	/**
	 * 权限不足
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/refuse")
	public String refuse(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "/user/refuse";
	}
}


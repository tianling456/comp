package com.comp.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *项目名：
 *创建时间：2017-6-13
 *创建人：Aobo
 *类名：LoginInterceptor
 *所属包名：com.comp.web.interceptor
 *项目名称：comp
 *类功能描述：
 *
 *此拦截器主要做需要登录验证的地址的拦截处理。
 *
 *拦截器、监听器、过滤器的区别：
 *过滤器：
 *Servlet中的过滤器Filter是实现了javax.servlet.Filter接口的服务器端程序，
 *主要的用途是过滤字符编码、做一些业务逻辑判断等。
 *其工作原理是，只要你在web.xml文件配置好要拦截的客户端请求，
 *它都会帮你拦截到请求，此时你就可以对请求或响应(Request、Response)统一设置编码，
 *简化操作；同时还可以进行逻辑判断，如用户是否已经登录、有没有权限访问该页面等等工作，
 *它是随你的web应用启动而启动的，只初始化一次，以后就可以拦截相关的请求，
 *只有当你的web应用停止或重新部署的时候才能销毁。
 *
 *监听器：
 *Servlet的监听器Listener，它是实现了javax.servlet.ServletContextListener接口的服务器端程序，
 *它也是随web应用的启动而启动，只初始化一次，随web应用的停止而销毁。
 *主要作用是：做一些初始化的内容添加工作、设置一些基本的内容、比如一些参数或者是一些固定的对象等等。
 *
 *拦截器：
 *拦截器是在面向切面编程中应用的，就是在你的service或者一个方法前调用一个方法，
 *或者在方法后调用一个方法比如动态代理就是拦截器的简单实现，
 *在你调用方法前打印出字符串（或者做其它业务逻辑的操作），
 *也可以在你调用方法后打印出字符串，甚至在你抛出异常的时候做业务逻辑的操作。
 *拦截器不是在web.xml配置的，比如struts在struts.xml配置，
 *在springMVC在spring与springMVC整合的配置文件中配置。
 *
 *执行顺序：过滤前 - 拦截前 - Action处理 - 拦截后 -过滤后。个人认为过滤是一个横向的过程，
 *首先把客户端提交的内容进行过滤(例如未登录用户不能访问内部页面的处理)；
 *过滤通过后，拦截器将检查用户提交数据的验证，做一些前期的数据处理，接着把处理后的数据发给对应的Action；
 *Action处理完成返回后，拦截器还可以做其他过程，再向上返回到过滤器的后续操作。
 *过滤器（Filter）：当你有一堆东西的时候，你只希望选择符合你要求的某一些东西。定义这些要求的工具，就是过滤器。
 *拦截器（Interceptor）：在一个流程正在进行的时候，你希望干预它的进展，甚至终止它进行，这是拦截器做的事情。
 *监听器（Listener）：当一个事件发生的时候，你希望获得这个事件发生的详细信息，而并不想干预这个事件本身的进程，
 *这就要用到监听器。
 *
 *
 *拦截器是在面向切面编程中应用的，就是在你的service或者一个方法前调用一个方法，
 *或者在方法后调用一个方法比如动态代理就是拦截器的简单实现，
 *在你调用方法前打印出字符串（或者做其它业务逻辑的操作），
 *也可以在你调用方法后打印出字符串，甚至在你抛出异常的时候做业务逻辑的操作。
 *拦截器不是在web.xml配置的，比如struts在struts.xml配置，
 *在springMVC在spring与springMVC整合的配置文件中配置。
 *在springmvc中，定义拦截器要实现HandlerInterceptor接口，
 *并实现该接口中提供的三个方法
 *
 */
public class LoginInterceptor implements HandlerInterceptor{

	/**
	 * 进入Handler方法之前执行 
	 * 可以用于身份认证、身份授权。如果认证没有通过表示用户没有登陆，需要此方法拦截不再往下执行，否则就放行
	 */
    
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		 System.out.println("InterceptorUtil...........preHandle");  
	     String user= (String) request.getSession().getAttribute("user");  
	     if(user != null){  
	          return true;
	     }  
	    request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);  
		//true表示放行，false表示不放行  
		return false;
	}

	/**
	 * 进入Handler方法之后，返回ModelAndView之前执行。
	 * 可以看到该方法中有个modelAndView的形参。
	 * 应用场景：从modelAndView出发：将公用的模型数据（比如菜单导航之类的）在这里传到视图，
	 * 也可以在这里同一指定视图。
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("InterceptorUtil...........postHandle");  
	}

	/**
	 * 执行Handler完成之后执行。应用场景：统一异常处理，统一日志处理等。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("InterceptorUtil...........afterCompletion");  
	}

}


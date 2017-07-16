package com.comp.web.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 *项目名：
 *创建时间：2017-6-13
 *创建人：Aobo
 *类名：RequestAttribute
 *所属包名：com.comp.web.filter
 *项目名称：comp
 *类功能描述：
 *
 **拦截器、监听器、过滤器的区别：
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
 *Servlet的监听器Listener，它是实现了javax.servlet.ServletContextListener接口的服务器端程序，
 *它也是随web应用的启动而启动，只初始化一次，随web应用的停止而销毁。
 *主要作用是：做一些初始化的内容添加工作、设置一些基本的内容、比如一些参数或者是一些固定的对象等等。
 *在javax.servlet.ServletContextListener接口中定义了2种方法：
 */
public class ServletRequestAttribute implements ServletRequestAttributeListener {

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
	}
}


package com.base.struts;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

//import org.apache.struts2.ServletActionContext;
//import org.apache.struts2.interceptor.ApplicationAware;
//import org.apache.struts2.interceptor.RequestAware;
//import org.apache.struts2.interceptor.ServletRequestAware;
//import org.apache.struts2.interceptor.SessionAware;
//
//import com.opensymphony.xwork2.ActionContext;
//import com.opensymphony.xwork2.ActionSupport;
//import com.opensymphony.xwork2.ModelDriven;
//import com.opensymphony.xwork2.Preparable;

/**
 * 项目名： 创建时间：2017-3-23 创建人：Aobo 类名：BaseAction 所属包名：com.base.struts 项目名称：comp
 * 类功能描述：
 */
public class BaseAction 
//extends ActionSupport implements RequestAware,
//		SessionAware, ApplicationAware, ServletRequestAware, Preparable 
		{
	private static final long serialVersionUID = 1L;
	// map类型web元素
	private Map<String, Object> requestMap;
	private Map<String, Object> sessionMap;
	private Map<String, Object> applicationMap;
	// 真实web元素
	private HttpServletRequest httpServletRequest;
	private HttpSession httpSession;
	private ServletContext applicationContext;

	public void setRequest(Map<String, Object> request) {
		this.requestMap = request;
	}

	public void setSession(Map<String, Object> session) {
		this.sessionMap = session;
	}

	public void setApplication(Map<String, Object> application) {
		this.applicationMap = application;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.httpServletRequest = request;
		this.httpSession = this.httpServletRequest.getSession();
		this.applicationContext = this.httpSession.getServletContext();
	}

	public Map<String, Object> getRequestMap() {
		return requestMap;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public Map<String, Object> getApplicationMap() {
		return applicationMap;
	}

	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	public HttpSession getHttpSession() {
		return httpSession;
	}

	public ServletContext getApplicationContext() {
		return applicationContext;
	}

//	@Override
//	public void prepare() throws Exception {
//	}
}

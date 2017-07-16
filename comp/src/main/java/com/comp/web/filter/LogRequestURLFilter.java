package com.comp.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *项目名：
 *创建时间：2017-6-13
 *创建人：Aobo
 *类名：LogRequestURLFilter
 *所属包名：com.comp.web.filter
 *项目名称：comp
 *类功能描述：
 */
public class LogRequestURLFilter implements Filter{

	public FilterConfig config;    
	@Override
    public void destroy() {    
        this.config = null;    
        System.out.println("end do the logging filter!");  
    }    
	@Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {    
        System.out.println("before the log filter!");    
        // 将请求转换成HttpServletRequest 请求    
        HttpServletRequest hreq = (HttpServletRequest) req;    
        // 记录日志    
        System.out.println("Log Filter已经截获到用户的请求的地址:"+hreq.getServletPath() );    
        try {    
            // Filter 只是链式处理，请求依然转发到目的地址。    
            chain.doFilter(req, res);    
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
        System.out.println("after the log filter!");    
    }    
	@Override
    public void init(FilterConfig config) throws ServletException {    
        System.out.println("begin do the log filter!");    
        this.config = config;    
    } 

}


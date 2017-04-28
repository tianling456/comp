package com.base.util.spring;

import junit.framework.Assert;


import org.apache.log4j.Logger;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;
import com.base.util.spring.SpringUtil;

/**
 *项目名：
 *创建时间：2016-6-12
 *创建人：Aobo
 *类名：SpringUtil
 *所属包名：com.util.spring
 *项目名称：comp
 *类功能描述：
 */
public class SpringUtilTest {
	private final Logger logger=Logger.getLogger(SpringUtilTest.class);
	@Test
	public void testGetBean(){
		DruidDataSource dataSource = SpringUtil.getBean("dataSource", DruidDataSource.class);
		long count = dataSource.getMaxWait();
		System.out.println(count);
		Assert.assertNotNull(count);
	}
//	@Test
//	public void testGetshiro(){
//		ShiroFilterFactoryBean shiro = SpringUtil.getBean("shiroFilter", ShiroFilterFactoryBean.class);
//		System.out.println(shiro.getSecurityManager());
//		Assert.assertNotNull(shiro.getSecurityManager());
//	}
}


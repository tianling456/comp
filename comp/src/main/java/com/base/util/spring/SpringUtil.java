package com.base.util.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 项目名： 创建时间：2016-6-12 创建人：Aobo 类名：SpringUtil 所属包名：com.base.util.spring
 * 项目名称：comp 类功能描述：
 */
public abstract class SpringUtil {
	private static ApplicationContext ac;
	private static String config = "classpath*:config/applicationContext.xml";

	public static ApplicationContext getContext() {
		ac = new ClassPathXmlApplicationContext(config);
		return ac;
	}

	public static <T> T getBean(String beanName, Class<T> clazz) {
		ApplicationContext ac = getContext();
		T bean = ac.getBean(beanName, clazz);
		return bean;
	}
}

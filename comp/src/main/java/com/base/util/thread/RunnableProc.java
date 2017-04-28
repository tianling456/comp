package com.base.util.thread;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *项目名：
 *创建时间：2016-7-11
 *创建人：Aobo
 *类名：RunnableProc
 *所属包名：test
 *项目名称：test
 *类功能描述：
 */
public class RunnableProc implements Runnable{
	private final Method method;
	private final Class<?> clazz;
	private final Object[] parameter;
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"任务启动");
		Object o = null;
		try {
			o = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			method.invoke(o,parameter);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public RunnableProc(Method method, Class<?> clazz,Object... parameter) {
		this.clazz = clazz;
		this.method = method;
		this.parameter = parameter;
	}
}


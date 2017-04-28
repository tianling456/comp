package com.base.util.thread;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.Callable;

/**
 * 项目名： 创建时间：2016-7-8 
 * 创建人：Aobo 
 * 类名：Callable 
 * 所属包名：com.base.util.thread 
 * 项目名称：comp
 * 类功能描述：
 */
public class CallablePro<T> implements Callable<T> {
	private final Method method;
	private final Class<?> clazz;
	private final Object[] parameter;
	
	@SuppressWarnings("unchecked")
	@Override
	public T call() throws Exception {
		Object o = clazz.newInstance();
		System.out.println(Thread.currentThread().getName()+"任务启动");
		return (T) method.invoke(o,parameter);
	}

	public CallablePro(Method method, Class<?> clazz,Object... parameter) {
		this.clazz = clazz;
		this.method = method;
		this.parameter = parameter;
	}
}

package com.base.util.thread;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 项目名： 创建时间：2016-7-8 创建人：Aobo 类名：ThreadProc 所属包名：com.base.util.thread 项目名称：comp
 * 类功能描述：
 */
public class ThreadProc<T> {
	private ExecutorService executor;
	private final static int POOL_SIZE = 5;
	private List<Future<T>> futureList = new ArrayList<Future<T>>();

	/**
	 * 返回线程池管理对象
	 * 
	 * @param poolSize
	 *            指定线程数,如果没有指定，默认为5
	 * @return
	 */
	public ExecutorService getExecutorService(int poolSize) {
		if (poolSize <= 0) {
			executor = getExecutorService();
		} else {
			executor = Executors.newFixedThreadPool(poolSize);
		}
		return executor;
	}

	/**
	 * 创建默认的5个线程的线程池
	 * @return
	 */
	public ExecutorService getExecutorService() {
		executor = Executors.newFixedThreadPool(POOL_SIZE);
		return executor;
	}

	/**
	 * 无返回值
	 * @param poolSize 线程数
	 * @param method
	 * @param className
	 * @param parameter
	 */
	public void execute(int poolSize, Method method, Class<?> className,Object... parameter) {
		getExecutorService(poolSize);
		for(int i=0;i<poolSize;i++){
			RunnableProc runnable = new RunnableProc(method, className, parameter);
			executor.execute(runnable);
		}
		executor.shutdown();
	}
	/**
	 * 无返回值
	 * @param method
	 * @param className
	 * @param parameter
	 */
	public void execute(Method method, Class<?> className,Object... parameter) {
		for(int i=0;i<POOL_SIZE;i++){
			RunnableProc runnable = new RunnableProc(method, className, parameter);
			executor.execute(runnable);
		}
		executor.shutdown();
	}

	/**
	 * 有返回值
	 * @param poolSize
	 * @param method
	 * @param className
	 * @param parameter
	 * @return
	 */
	public List<Future<T>> getFuture(int poolSize, Method method,Class<?> className, Object... parameter) {
		getExecutorService(poolSize);
		for (int i = 0; i < poolSize; i++) {
			CallablePro<T> callable = new CallablePro<T>(method, className, parameter);
			Future<T> future = executor.submit(callable);
			futureList.add(future);
		}
		executor.shutdown();
		return futureList;
	}

	/**
	 * 有返回值
	 * @param method
	 * @param className
	 * @param parameter
	 * @return
	 */
	public List<Future<T>> getFuture(Method method, Class<?> className,Object... parameter) {
		getExecutorService();
		for (int i = 0; i < POOL_SIZE; i++) {
			CallablePro<T> callable = new CallablePro<T>(method, className, parameter);
			Future<T> future = executor.submit(callable);
			futureList.add(future);
		}
		executor.shutdown();
		return futureList;
	}
}
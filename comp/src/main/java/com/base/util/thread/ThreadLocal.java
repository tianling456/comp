package com.base.util.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *项目名：
 *创建时间：2016-7-2
 *创建人：Aobo
 *类名：ThreadLocal
 *所属包名：com.base.util.thread
 *项目名称：comp
 *类功能描述：
 */
public class ThreadLocal<T>{
	private Map<Object,Object> values = Collections.synchronizedMap(new HashMap<Object,Object>());
	@SuppressWarnings("unchecked")
	public T get() {
      Thread currentThread = Thread.currentThread();
      T result = (T) values.get(currentThread);
      if(result == null&&!values.containsKey(currentThread)) {
    	  result = initialValue();
    	  values.put(currentThread, result);
      }
      return result;
    } 
	public void set(T newValue) {
      values.put(Thread.currentThread(), newValue);
    } 
	public T initialValue() {
      return null;
   }
}


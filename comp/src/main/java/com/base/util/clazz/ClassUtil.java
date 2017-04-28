package com.base.util.clazz;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 *项目名：
 *创建时间：2016-6-14
 *创建人：Aobo
 *类名：ClassUtil
 *所属包名：com.base.util.clazz
 *项目名称：comp
 *类功能描述：
 */
public abstract class ClassUtil{
	/**
	 * 根据参数索引获得参数化类型的泛型类型，（通过索引取得）
	 * @param clazz 参数化类型
	 * @param index 参数索引
	 * @return 泛型类型
	 */
	@SuppressWarnings("rawtypes")
	public static Class getClassGenricType(final Class clazz) {
		return getClassGenricType(clazz, 0);
	}
	
	/**
	 * 根据参数索引获得参数化类型的泛型类型，（通过索引取得）
	 * @param clazz 参数化类型
	 * @param index 参数索引
	 * @return 泛型类型
	 */
	@SuppressWarnings("rawtypes")
	public static Class getClassGenricType(
			final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
}


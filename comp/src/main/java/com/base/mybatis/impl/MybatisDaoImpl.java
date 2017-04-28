package com.base.mybatis.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import com.base.mybatis.MybatisDao;
import com.base.util.mybatis.MybatisUtil;

/**
 *项目名：
 *创建时间：2016-6-10
 *创建人：Aobo
 *类名：MybatisDaoImpl
 *所属包名：com.base.mybatis.impl
 *项目名称：comp
 *类功能描述：
 */
public class MybatisDaoImpl<T,ID extends Serializable> implements MybatisDao<T,ID>{
	@SuppressWarnings("unchecked")
	public Class<T> getClazz(){
		//在父类中得到子类声明的父类的泛型信息  
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		@SuppressWarnings("rawtypes")
		Class<T> clazz = (Class) pt.getActualTypeArguments()[0];
		return clazz;
	}
	
	public String getNameSpace(String methodName){
		return getClazz().getName().toString()+SQLNAME_SEPARATOR+methodName;
	}
	
	public void addMapper(String methodName){
		MybatisUtil.getSession().getConfiguration().addMappers(this.getNameSpace(methodName));
	}
	
	@Override
	public Integer save(T t,String methodName) {
		return MybatisUtil.getSession().insert(getNameSpace(methodName), t);
	}

	@Override
	public T findById(ID id,String methodName) {
		return MybatisUtil.getSession().selectOne(getNameSpace(methodName), id);
	}

	
	@Override
	public int deleteById(ID id,String methodName) {
		return MybatisUtil.getSession().delete(getNameSpace(methodName), id);
	}

	@Override
	public Integer modify(T t,String methodName) {
		return MybatisUtil.getSession().update(getNameSpace(methodName), t);
	}

	@Override
	public Integer getCount(String methodName) {
		return MybatisUtil.getSession().selectOne(getNameSpace(methodName));
	}

	@Override
	public Integer getCount(ID id,String methodName) {
		return MybatisUtil.getSession().selectOne(getNameSpace(methodName), id);
	}

	@Override
	public <V,K> Integer getCount(String methodName,Map<V,K> whereCases) {
		return MybatisUtil.getSession().selectOne(getNameSpace(methodName), whereCases);
	}

	@Override
	public List<T> queryForList(String methodName) {
		return MybatisUtil.getSession().selectList(getNameSpace(methodName));
	}

	@Override
	public List<T> queryForList(ID id,String methodName) {
		return MybatisUtil.getSession().selectList(getNameSpace(methodName), id);
	}

	@Override
	public <V,K> List<T> queryForList(String methodName,Map<V,K> whereCases) {
		return MybatisUtil.getSession().selectList(getNameSpace(methodName), whereCases);
	}

	@Override
	public <V, K> Map<V, K> queryForMap(String methodName, Map<V, K> whereCases, String mapKey) {
		return MybatisUtil.getSession().selectMap(getNameSpace(methodName), whereCases, mapKey);
	}

	@Override
	public <V, K> Map<V, K> queryForMap(ID id, String methodName, String mapKey) {
		return MybatisUtil.getSession().selectMap(getNameSpace(methodName), id, mapKey);
	}

	@Override
	public <V, K> Map<V, K> queryForMap(String methodName, String mapKey) {
		return MybatisUtil.getSession().selectMap(getNameSpace(methodName), mapKey);
	}
	
	@Override
	public void callProcedure(String methodName) {
		MybatisUtil.getSession().selectOne(getNameSpace(methodName));
	}

	@Override
	public <V,K> List<T> callProcedureForList(String methodName,Map<V,K> input) {
		return MybatisUtil.getSession().selectList(getNameSpace(methodName), input);
	}

	@Override
	public <V,K> Map<V,K> callProcedureForMap(String methodName,Map<V,K> input,String mapKey) {
		return MybatisUtil.getSession().selectMap(getNameSpace(methodName), input, mapKey);
	}

	@Override
	public <V,K> T findForObject(String methodName, Map<V, K> whereCases) {
		return MybatisUtil.getSession().selectOne(getNameSpace(methodName),whereCases);
	}
}
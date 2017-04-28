package com.base.hibernate.impl;

//import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.procedure.ProcedureCall;
import com.base.hibernate.HibernateDao;
import com.base.util.hibernate.HibernateUtil;

/**
 *项目名：
 *创建时间：2016-6-10
 *创建人：Aobo
 *类名：hibernateDaoImpl
 *所属包名：com.base.hibernate.impl
 *项目名称：comp
 *类功能描述：
 */
public class HibernateDaoImpl<T,ID extends java.io.Serializable> implements HibernateDao<T,ID> {
//	/**
//	 * @Filed entityClass : 泛型反射类
//	 */
//	private Class<T> entityClass;
//	public HibernateDaoImpl(){
//		Type genericType = this.getClass().getGenericSuperclass();
//		this.entityClass = (Class<T>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
//	}
	@SuppressWarnings("unchecked")
	public Class<T> getClazz(){
		//在父类中得到子类声明的父类的泛型信息  
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		@SuppressWarnings("rawtypes")
		Class<T> clazz = (Class) pt.getActualTypeArguments()[0];
		return clazz;
	}
	
	@Override
	public void save(T t) {
//		Session session = HibernateUtil.getSession();
		try{
			HibernateUtil.getSession().getTransaction().begin();
			HibernateUtil.getSession().save(t);
		}catch(Exception e){
			HibernateUtil.getSession().getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.getSession().getTransaction().commit();
			HibernateUtil.sessionClose();
		}
	}

	@Override
	public int save(String sql) {
//		Session session = HibernateUtil.getSession();
		int count=0;
		try{
			HibernateUtil.getSession().getTransaction().begin();
			count = HibernateUtil.execute(sql);
		}catch(Exception e){
			HibernateUtil.getSession().getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.getSession().getTransaction().commit();
			HibernateUtil.getSession().close();
		}
		return count;
	}

	@Override
	public int save(String sql, Map<String, Object> whereCasese) {
//		Session session = HibernateUtil.getSession();
		int count=0;
		try{
			HibernateUtil.getSession().getTransaction().begin();
			count = HibernateUtil.execute(sql,whereCasese);
		}catch(Exception e){
			HibernateUtil.getSession().getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.getSession().getTransaction().commit();
			HibernateUtil.getSession().close();
		}
		return count;
	}
	
	@Override
	public T findById(ID id) {
		return HibernateUtil.getSession().get(getClazz(), id);
	}

	@Override
	public void deleteById(ID id) {
//		Session session = HibernateUtil.getSession();
		try{
			HibernateUtil.getSession().getTransaction().begin();
			HibernateUtil.getSession().delete(findById(id));
		}catch(Exception e){
			HibernateUtil.getSession().getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.getSession().getTransaction().commit();
			HibernateUtil.getSession().close();
		}
	}
	
	@Override
	public int delete(String sql) {
//		Session session = HibernateUtil.getSession();
		int count=0;
		try{
			HibernateUtil.getSession().getTransaction().begin();
			count = HibernateUtil.execute(sql);
		}catch(Exception e){
			HibernateUtil.getSession().getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.getSession().getTransaction().commit();
			HibernateUtil.getSession().close();
		}
		return count;
	}

	@Override
	public int delete(String sql, Map<String, Object> whereCasese) {
		Session session = HibernateUtil.getSession();
		int count=0;
		try{
			session.getTransaction().begin();
			count = HibernateUtil.execute(sql,whereCasese);
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.getTransaction().commit();
			session.close();
		}
		return count;
	}

	@Override
	public void modify(T t) {
//		Session session = HibernateUtil.getSession();
		try{
			HibernateUtil.getSession().getTransaction().begin();
			HibernateUtil.getSession().update(t);
		}catch(Exception e){
			HibernateUtil.getSession().getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.getSession().getTransaction().commit();
			HibernateUtil.getSession().close();
		}
	}
	
	@Override
	public int modify(String sql) {
//		Session session = HibernateUtil.getSession();
		int count=0;
		try{
			HibernateUtil.getSession().getTransaction().begin();
			count = HibernateUtil.execute(sql);
		}catch(Exception e){
			HibernateUtil.getSession().getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.getSession().getTransaction().commit();
			HibernateUtil.getSession().close();
		}
		return count;
	}

	@Override
	public int modify(String sql, Map<String, Object> whereCasese) {
//		Session session = HibernateUtil.getSession();
		int count=0;
		try{
			HibernateUtil.getSession().getTransaction().begin();
			count = HibernateUtil.execute(sql,whereCasese);
		}catch(Exception e){
			HibernateUtil.getSession().getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.getSession().getTransaction().commit();
			HibernateUtil.getSession().close();
		}
		return count;
	}

	@Override
	public Integer getCount(String sql) {
		return (Integer)HibernateUtil.getCreateSQLQuery(getClazz(),sql).list().get(0);
	}

	@Override
	public Integer getCount(String sql,Map<String,Object> whereCases) {
		return (Integer) HibernateUtil.getCreateSQLQuery(getClazz(),sql, whereCases).list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryForList(String sql,String sqlOrHql) {
		if(CONSTANT_SQL.equals(sqlOrHql)){
			return (List<T>)HibernateUtil.getCreateSQLQuery(getClazz(),sql).list();
		}
		if(CONSTANT_HQL.equals(sqlOrHql)){
			return (List<T>)HibernateUtil.getCreateQuery(sql).list();
		}
		return (List<T>)HibernateUtil.getCreateSQLQuery(getClazz(),sql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryForList(String sql) {
		return (List<T>)HibernateUtil.getCreateSQLQuery(getClazz(),sql).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryForList(String sql,Map<String,Object> whereCases) {
		return (List<T>)HibernateUtil.getCreateSQLQuery(getClazz(),sql,whereCases).list();
	}

	@Override
	public void callProcedure(String sql,Map<String,Object> whereCases) {
		HibernateUtil.getCreateSQLQuery(getClazz(),sql,whereCases);
	}

	@Override
	public List<T> callProcedureForList(String sql,Map<String,Object> whereCases) {
		
		return null;
	}

	@Override
	public Map<String,Object> callProcedureForMap(String sql,Map<String,Object> whereCases) {
		ProcedureCall call = HibernateUtil.getSession().createStoredProcedureCall(sql);
//		call.
		return null;
	}
}


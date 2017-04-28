package com.base.util.hibernate;

import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.base.common.exception.app.AppException;
import com.base.util.spring.SpringUtil;

//import com.base.util.thread.ThreadLocal;

/**
 *项目名：
 *创建时间：2016-6-11
 *创建人：Aobo
 *类名：HibernateUtil
 *所属包名：com.base.util.hibernate
 *项目名称：comp
 *类功能描述：主要用于处理hibernate底层的一些操作，
 *		        比如获取session和关闭session等操作
 *		       也可以extends HibernateDaoSupport
 */
public abstract class HibernateUtil{
	private static final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();
	/**
	 * 
	 * @return 返回Hibernate的SessionFactory，以便获取Session，
	 *         用于操作数据库，进行增删改查操作
	 */
	public static SessionFactory getSessionFactory(){
		SessionFactory sessionFactory = SpringUtil.getBean("sessionFactory", SessionFactory.class);
		return sessionFactory;
	}
	
	/**
	 * 通过SessionFactory获取用于操作数据库的session
	 * @return Session对象
	 */
	public static Session getSession(){  
	    Session session = threadSession.get();
	    try {  
	        if (session == null) {  
	            session = getSessionFactory().openSession();
//	            session = getSessionFactory().getCurrentSession();
	            threadSession.set(session);
	        }  
	    } catch (HibernateException ex) {  
	        throw new HibernateException(ex);  
	    }  
	    return session;  
	}  
	
	/**
	 * TODO 要执行分页查询
	 * 使用hql语句查询
	 * @param sql  查询的sql语句
	 * @return 
	 */
	public static Query getCreateQuery(String hql){
		Query query = getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(10);
		return query;
	}
	
	
	/**
	 * TODO 要执行分页查询
	 * 使用原始的sql语句查询
	 * @param sql  查询的sql语句
	 * @return 
	 */
	public static SQLQuery getCreateSQLQuery(@SuppressWarnings("rawtypes") Class c,String sql){
		SQLQuery sqlQuery =  getSession().createSQLQuery(sql);
		sqlQuery.addEntity(c);
		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(10);
		return sqlQuery;
	}
	
	/**
	 * TODO 要执行分页查询
	 * 使用hql查询
	 * @param sql  查询的sql语句
	 * @param whereCasese 执行查询的条件
	 * @return 
	 */
	public static Query getCreateQuery(String hql,Map<String,Object> whereCasese){
		Query query = getCreateQuery(hql);
		setParameters(query,whereCasese);
		return query;
	}
	
	/**
	 * TODO 要执行分页查询
	 * 使用原始的sql语句查询
	 * @param sql  查询的sql语句
	 * @param whereCasese 执行查询的条件
	 * @return 
	 */
	public static SQLQuery getCreateSQLQuery(@SuppressWarnings("rawtypes") Class c,String sql,Map<String,Object> whereCasese){
		SQLQuery query =  getCreateSQLQuery(c,sql);
		setParameters(query,whereCasese);
		return query;
	}
	
	/**
	 * TODO 设置query的查询参数,还要判断参数类型和是否为空的相应操作
	 * @param query 要执行的查询
	 * @param whereCasese 不能为空，为空抛出异常
	 */
	public static void setParameters(Query query,Map<String,Object> whereCasese) {
		if(whereCasese==null||whereCasese.isEmpty()||whereCasese.size()<0){
			throw new AppException("","");
		}
		for (Map.Entry<String,Object> entry : whereCasese.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
	
	/**
	 * TODO 设置query的查询参数,还要判断参数类型和是否为空的相应操作
	 * @param query 要执行的查询
	 * @param whereCasese 不能为空，为空抛出异常
	 */
	public static void setParameters(SQLQuery query,Map<String,Object> whereCasese){
		for (Map.Entry<String,Object> entry : whereCasese.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
	
	/**
	 * 使用原始的sql语句对数据库进行更新和删除
	 * @param sql 原始sql语句
	 * @return 受影响的行数
	 */
	public static int execute(String sql){
		SQLQuery query = getSession().createSQLQuery(sql);
		int count = query.executeUpdate();
		return count;
	}
	
	/**
	 * 使用原始的sql语句对数据库进行更新和删除和添加，whereCasese条件
	 * @param sql 原始sql语句
	 * @param whereCasese 不能为空，为空抛出异常
	 * @return 受影响的行数
	 */
	public static int execute(String sql,Map<String,Object>whereCasese){
		SQLQuery query = getSession().createSQLQuery(sql);
		setParameters(query,whereCasese);
		int count = query.executeUpdate();
		return count;
	}
	
	/**
	 * 关闭session连接，释放资源
	 */
	public static void sessionClose(){
		Session session = threadSession.get();
		if(session!=null){
			session.close();
		}
		threadSession.set(null);
	}
	
	/**
	 * 可以强制清除Session缓存
	 */
	public static void sessionClear(){
		getSession().clear();
	}
	
	/**
	 * 可以强制进行从内存到数据库的同步
	 */
	public static void sessionFlush(){
		getSession().flush();
	}
	
	/**
	 * 终止当前查询
	 */
	public static void cancelQuery(){
		getSession().cancelQuery();
	}
	
	/**
	 * 判断对象t是否包含在session的缓存中，存在返回true，不存在返回false
	 * @param t  要判断的对象
	 * @return  对象t存在session缓存中，返回true，不存在返回false
	 */
	public static <T> boolean contains(T t){
		return getSession().contains(t);
	} 
}


package com.base.util.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.mybatis.spring.SqlSessionTemplate;

import com.base.util.spring.SpringUtil;

/**
 *项目名：
 *创建时间：2016-6-12
 *创建人：Aobo
 *类名：MybatisUtil
 *所属包名：com.base.util.mybatis
 *项目名称：comp
 *类功能描述：主要用于处理mybatis底层的一些操作，
 *		        比如获取session和关闭session等操作
 *		        也可以 extends SqlSessionDaoSupport
 */
public abstract class MybatisUtil{
	/**
	 * 通过spring的容器获取SqlSession
	 * @return
	 */
	public static SqlSession getSession(){
//		SqlSession session = SpringUtil.getBean("sqlSession", SqlSessionTemplate.class);
		SqlSessionFactory sqlSessionFactory = SpringUtil.getBean("sqlSessionFactoryBean", SqlSessionFactory.class);
		SqlSession session = SqlSessionManager.newInstance(sqlSessionFactory);
//		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
	public static void sessionClose() {
		try {
			getSession().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sessionCommit(){
		try {
			getSession().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sessionCacheClear(){
		getSession().clearCache();
	}
}


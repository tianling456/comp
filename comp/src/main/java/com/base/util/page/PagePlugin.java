package com.base.util.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.bind.PropertyException;

import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import com.base.util.page.PageView;
import com.base.util.common.Common;
import com.base.util.jdbc.dialet.Dialect;

/**
 * Mybatis的分页查询插件，通过拦截StatementHandler的prepare方法来实现。 
 * 只有在参数列表中包括Page类型的参数时才进行分页查询。 
 * 在多参数的情况下，只对第一个Page类型的参数生效。 
 * 另外，在参数列表中，Page类型的参数无需用@Param来标注 
 * 2013-11-19
 * @version 1.0v
 */
@SuppressWarnings("unchecked")
@Intercepts( { @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PagePlugin implements Interceptor {

	private static Dialect dialectObject = null; // 数据库方言
	private static String pageSqlId = ""; // mybaits的数据库xml映射文件中需要拦截的ID(正则匹配)

	public Object intercept(Invocation ivk) throws Throwable {
		if (ivk.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk
					.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper
					.getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectHelper
					.getValueByFieldName(delegate, "mappedStatement");
			/**
			 * 方法1：通过ＩＤ来区分是否需要分页．.*query.*
			 * 方法2：传入的参数是否有page参数，如果有，则分页，
			 */
			if (mappedStatement.getId().matches(pageSqlId)) { // 拦截需要分页的SQL
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject();// 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
				if (parameterObject == null) {
					//throw new NullPointerException("boundSql.getParameterObject() is null!");
					return ivk.proceed();
				} else {

					PageView pageView = null;
					if (parameterObject instanceof PageView) { // 参数就是Pages实体
						pageView = (PageView) parameterObject;
					} else if (parameterObject instanceof Map) {
						for (Entry entry : (Set<Entry>) ((Map) parameterObject)
								.entrySet()) {
							if (entry.getValue() instanceof PageView) {
								pageView = (PageView) entry.getValue();
								break;
							}
						}
					} else { // 参数为某个实体，该实体拥有Pages属性
						pageView = ReflectHelper.getValueByFieldType(
								parameterObject, PageView.class);
						if (pageView == null) {
							return ivk.proceed();
						}
					}

					String sql = boundSql.getSql();
					PreparedStatement countStmt = null;
					ResultSet rs = null;
					try {
						Connection connection = (Connection) ivk.getArgs()[0];
						String countSql = "select count(1) from (" + sql
								+ ")"; // 记录统计
						countStmt = connection.prepareStatement(countSql);
						ReflectHelper.setValueByFieldName(boundSql, "sql",
								countSql);
						DefaultParameterHandler parameterHandler = new DefaultParameterHandler(
								mappedStatement, parameterObject, boundSql);
						parameterHandler.setParameters(countStmt);
						rs = countStmt.executeQuery();
						int count = 0;
						if (rs.next()) {
							count = ((Number) rs.getObject(1)).intValue();
						}
						pageView.setRowCount(count);
					} finally {
						try {
							rs.close();
						} catch (Exception e) {
						}
						try {
							countStmt.close();
						} catch (Exception e) {
						}
					}
//					setPageParameter(sql, connection, mappedStatement, boundSql, parameterObject, pageView);
					String pageSql = generatePagesSql(sql, pageView);
					ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql); // 将分页sql语句反射回BoundSql.
				}
			}
		}
		return ivk.proceed();
	}

	/**
     * 从数据库里查询总的记录数并计算总页数，回写进分页参数<code>PageParameter</code>,这样调用者就可用通过 分页参数
     * <code>PageParameter</code>获得相关信息。
     * 
     * @param sql
     * @param connection
     * @param mappedStatement
     * @param boundSql
     * @param page
	 * @throws SQLException 
     */
    private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement,
            BoundSql boundSql,Object parameterObject, PageView pageView) throws SQLException {
        // 记录总记录数
    	PreparedStatement countStmt = null;
		ResultSet rs = null;
		try {
			String countSql = "";
			try {
				 countSql = "select count(1) from " + suffixStr(removeOrderBys(sql));
				countStmt = connection.prepareStatement(countSql);
				rs = countStmt.executeQuery();
			} catch (Exception e) {
				countSql = "select count(1) from (" + sql+ ") tmp_count"; 
				countStmt = connection.prepareStatement(countSql);
				rs = countStmt.executeQuery();
			}
			int count = 0;
			if (rs.next()) {
				count = ((Number) rs.getObject(1)).intValue();
			}
			pageView.setRowCount(count);
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				countStmt.close();
			} catch (Exception e) {
			}
		}
    }
    
    /** 
     * 去除Sql的orderBy。 
     * @param toSql 
     * @return String
     *
     */  
    private static String removeOrderBys(String toSql) {  
  	  	toSql=toSql.toUpperCase();
  	  	int sun = toSql.indexOf("order");
  	  	if(sun>-1){
  	  	  	String f1 = toSql.substring(sun-1,sun);
  	  		String f2 = toSql.substring(sun+5,sun+5);
  	  		if(f1.trim().isEmpty()&&f2.trim().isEmpty()){//判断第一个from的前后是否为空
  	  		  	String zb = toSql.substring(sun);
  	  		  	int s0 =zb.indexOf(")");
  	  		  	if(s0>-1){//from之前是否有括号
  	  		  		String s1=toSql.substring(0,sun);
  	  		  		String s2 =zb.substring(s0);
  	  		  		return removeOrderBys(s1+s2);
  	  		  	}else{
  	  		  		toSql=toSql.substring(0,sun);
  	  		  	}
  	  		}
  	  	}
  		return toSql;
    }
    
    /**
	   *   select
	     *  id,
		 * 	articleNo,
		 * sum(ddd) ss,
		 * 	articleName,
	     *  (SELECT loginName from ly_userinfo u where u.id=userId) loginName,
		 * 	(SELECT userName from ly_userinfo u where u.id=userId) userName,
		 * sum(ddd) ss
		 * from article	
		 * 兼容以上子查询
		 * //去除sql ..from 前面的字符。考虑 aafrom fromdd 等等情况
	   */
	public static String suffixStr(String toSql){
		toSql=toSql.toUpperCase();
		int sun = toSql.indexOf("from");
		String f1 = toSql.substring(sun-1,sun);
		String f2 = toSql.substring(sun+4,sun+5);
		if(f1.trim().isEmpty()&&f2.trim().isEmpty()){//判断第一个from的前后是否为空
			String s1 = toSql.substring(0,sun);
			int s0 =s1.indexOf("(");
			if(s0>-1){
				int se1 =s1.indexOf("select");
				if(s0<se1){
					if(se1>-1){
						String ss1 = s1.substring(se1-1,se1);
						String ss2 = s1.substring(se1+6,se1+7);
						if(ss1.trim().isEmpty()&&ss2.trim().isEmpty()){//判断第一个from的前后是否为空
							return suffixStr(toSql.substring(sun+5));
						}
					}
				}	
				int se2 =s1.indexOf("(select");
					if(se2>-1){
						String ss2 = s1.substring(se2+7,se2+8);
						if(ss2.trim().isEmpty()){//判断第一个from的前后是否为空
							return suffixStr(toSql.substring(sun+5));
						}
					}
					if(se1==-1&&se2==-1){
						return toSql.substring(sun+5);
					}else{
						toSql=toSql.substring(sun+5);
					}
			}else{
				toSql=toSql.substring(sun+5);
			}
		}
		return toSql;
	}
	
	/**
	 * 根据数据库方言，生成特定的分页sql
	 * 
	 * @param sql
	 * @param page
	 * @return
	 */
	private String generatePagesSql(String sql, PageView page) {
		if (page != null && dialectObject != null) {
			//pageNow默认是从1，而已数据库是从0开始计算的．所以(page.getPageNow()-1)
			int pageNow = page.getPageNow();
			return dialectObject.getLimitString(sql, (pageNow<=0?0:pageNow-1)
					* page.getPageSize(), page.getPageSize());
		}
		return sql;
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties p) {
		String dialect = ""; // 数据库方言
		dialect = p.getProperty("dialect");
		if (Common.isEmpty(dialect)) {
			try {
				throw new PropertyException("dialect property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		} else {
			try {
				dialectObject = (Dialect) Class.forName(dialect)
						.getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				throw new RuntimeException(dialect + ", init fail!\n" + e);
			}
		}
		pageSqlId = p.getProperty("pageSqlId");//根据id来区分是否需要分页
		if (Common.isEmpty(pageSqlId)) {
			try {
				throw new PropertyException("pageSqlId property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
	}
}

package com.base.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

/**
 *项目名：
 *创建时间：2016-6-10
 *创建人：Aobo
 *类名：hibernateDao
 *所属包名：com.base.hibernate
 *项目名称：comp
 *类功能描述：
 */
public interface HibernateDao <T,ID extends Serializable>{
	public final static String CONSTANT_HQL="HQL";
	public final static String CONSTANT_SQL="SQL";
	
	/**
	 * 查找指定记录：
	 * 根据传入的指定id查找记录，
	 * id不能为空，为空会报id为空异常，
	 * 如果找不到会提示没有记录，并抛出记录为空的异常
	 * @param id 传入的id值，可以是String类型，也可以是Number类型
	 * @return 返回查找到满足此id的所有记录
	 */
	public T findById(ID id);
	
	/**
	 * 保存指定记录：
	 * 根据传入的指定对象保存记录，
	 * t不能为空，为空会报t为空异常
	 * @param t 传入的t值
	 * @return 返回保存的记录条数
	 */
	public void save(T t);
	
	/**
	 * 保存指定记录：
	 * 根据传入的指定sql保存记录，
	 * sql不能为空，为空会报sql为空异常
	 * @param sql 传入的sql值
	 * @return 返回保存的记录条数
	 */
	public int save(String sql);
	
	/**
	 * 保存指定记录：
	 * 根据传入的指定sql和whereCasese保存记录，
	 * sql不能为空，为空会报sql为空异常
	 * @param sql 传入的sql值
	 * @param whereCasese 传入的whereCasese条件的值
	 * @return 返回保存的记录条数
	 */
	public int save(String sql,Map<String,Object>whereCasese);
	
	/**
	 * 删除指定记录：
	 * 根据传入的指定id删除指定记录，
	 * id不能为空，为空会报id为空异常，
	 * 如果找不到会提示没有记录，并抛出记录为空的异常
	 * @param id 传入的id值，可以是String类型，也可以是Number类型
	 * @return 返回删除满足id记录的所有条数
	 */
	public void deleteById(ID id);
	
	/**
	 * 删除指定记录：
	 * 根据传入的指定sql来删除指定记录，
	 * sql不能为空，为空会报sql为空异常，
	 * 如果找不到会提示没有记录，并抛出记录为空的异常
	 * @param sql 传入的sql语句
	 * @return 返回删除受影响的条数
	 */
	public int delete (String sql);
	
	/**
	 * 删除指定记录：
	 * 根据传入的指定whereCasese条件和sql语句删除指定记录，
	 * sql和whereCasese不能为空，为空会报sql和whereCasese为空异常，
	 * 如果找不到会提示没有记录，并抛出记录为空的异常
	 * @param sql 传入的sql语句
	 * @param whereCasese 传入的whereCasese的条件值
	 * @return 返回删除满足id记录的所有条数
	 */
	public int delete (String sql,Map<String,Object>whereCasese);
	
	/**
	 * 修改指定记录：
	 * 根据传入的指定id更新指定记录；
	 * id不能为空，为空会报id为空异常；
	 * 如果找不到会提示没有记录，并抛出记录为空的异常；
	 * @param id 传入的id值，可以是String类型，
	 * 			  也可以是Number类型，也可以是自定义的javabean，
	 * 			 但是该javabean必须实现java.io.Serializable序列号接口
	 * @return 返回修改满足id记录的所有条数
	 */
	public void modify(T t);
	
	/**
	 * 修改指定记录：
	 * 根据传入的指定sql更新指定记录；
	 * sql不能为空，为空会报sql为空异常；
	 * 如果找不到会提示没有记录，并抛出记录为空的异常；
	 * @param sql 传入的sql值
	 * @return 返回修改满足sql记录的所有条数
	 */
	public int modify(String sql);
	
	/**
	  * 修改指定记录：
	 * 根据传入的指定sql和whereCasese更新指定记录；
	 * sql和whereCasese不能为空，为空会报sql和whereCasese为空异常；
	 * 如果找不到会提示没有记录，并抛出记录为空的异常；
	 * @param sql 传入的sql值
	 * @param whereCasese 传入的whereCasese条件的值
	 * @return 返回修改满足sql和whereCasese记录的所有条数
	 */
	public int modify(String sql,Map<String,Object> whereCasese);
	
	/**
	 * 查找所有记录的记录数：
	 * 没有条件的查询查询记录条数
	 * @return 返回查找的所有的记录的记录数
	 */
	public Integer getCount(String sql);
	
	/**
	 * 查找所有记录的记录数：
	 * 根据传入的指定whereCases查找指定的所有记录；
	 * whereCase不能为空，为空会报whereCases为空异常；
	 * 如果找不到会提示没有记录，并抛出记录为空的异常；
	 * @param map 传入的whereCases值，
	 * @return  返回指定id的所有记录的记录数
	 */
	public Integer getCount(String sql , Map<String,Object> whereCases);
	
	/**
	 * 查找所有记录：默认是原生的sql
	 * 没有条件的查询
	 * @param sqlOrHql 通过指定是原生的sql(CONSTANT_SQL)还是hql(CONSTANT_HQL)来查询记录
	 * @return 返回查找的所有的记录
	 */
	public List<T> queryForList(String sql,String sqlOrHql);
	
	/**
	 * 查找所有记录：使用的是原生的sql语句
	 * 没有条件的查询
	 * @return 返回查找的所有的记录
	 */
	public List<T> queryForList(String sql);
	
	/**
	 * 查找所有记录：
	 * 根据传入的指定whereCases查找指定的所有记录；
	 * whereCase不能为空，为空会报whereCases为空异常；
	 * 如果找不到会提示没有记录，并抛出记录为空的异常；
	 * @param map 传入的whereCases值，
	 * @return  返回指定id的所有记录
	 */
	public List<T> queryForList(String sql,Map<String,Object> whereCases);
	
	/**
	 * 调用存储过程：
	 * 执行失败抛出异常；
	 * 调用没有入参和出参的存储过程
	 */
	public void callProcedure(String sql,Map<String,Object> whereCases);
	
	/**
	 * 调用存储过程：
	 * 执行失败抛出异常；
	 * @param input 传入参数，不能为空，为空包异常
	 * @return 主要针对返回一个cursor的存储过程
	 */
	public List<T> callProcedureForList(String sql,Map<String,Object> whereCases);
	
	/**
	 * 调用存储过程：
	 * 执行失败抛出异常；
	 * @param input 传入参数，不能为空，为空包异常
	 * @return 主要针对返回多个cursor的存储过程
	 */
	public Map<String,Object> callProcedureForMap(String sql,Map<String,Object> whereCases);
}


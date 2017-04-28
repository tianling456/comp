package com.base.mybatis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 *项目名：
 *创建时间：2016-6-10
 *创建人：Aobo(田令)
 *类名：mybatisDao
 *所属包名：com.base.mybatis
 *项目名称：comp
 *类功能描述：实现mybatis的通用dao，T为javaBean类型，ID为根据指定的主键查找的条件
 */
public interface MybatisDao <T,ID extends Serializable>{
	public static String SQLNAME_SEPARATOR=".";
	/**
	 * 保存指定记录：
	 * 根据传入的指定对象保存记录，
	 * t不能为空，为空会报t为空异常
	 * @param t 传入的t值
	 * @return 返回保存的记录条数
	 */
	public Integer save(T t,String methodName);
	
	/**
	 * 查找指定记录：
	 * 根据传入的指定id查找记录，
	 * id不能为空，为空会报id为空异常，
	 * 如果找不到会提示没有记录，并抛出记录为空的异常
	 * @param id 传入的id值，可以是String类型，也可以是Number类型
	 * @return 返回查找到满足此id的所有记录
	 */
	public T findById(ID id,String methodName);
	
	/**
	 * 删除指定记录：
	 * 根据传入的指定id删除指定记录，
	 * id不能为空，为空会报id为空异常，
	 * 如果找不到会提示没有记录，并抛出记录为空的异常
	 * @param id 传入的id值，可以是String类型，也可以是Number类型
	 * @return 返回删除满足id记录的所有条数
	 */
	public int deleteById(ID id,String methodName);
	
	/**
	 * 修改指定记录：
	 * 根据传入的指定id更新指定记录；
	 * id不能为空，为空会报id为空异常；
	 * 如果找不到会提示没有记录，并抛出记录为空的异常；
	 * @param id 传入的id值，可以是String类型，
	 * 			  也可以是Number类型，也可以是自定义的javabean，
	 * 			 但是该javabean必须实现java.io.Serializable序列号接口
	 * @param  t 要更新的对象，不能为空，为空抛异常
	 * @return 返回修改满足id记录的所有条数
	 */
	public Integer modify(T t,String methodName);
	
	/**
	 * 查找所有记录的记录数：
	 * 没有条件的查询查询记录条数
	 * @return 返回查找的所有的记录的记录数
	 */
	public Integer getCount(String methodName);
	
	/**
	 * 查找满足指定id所有记录的记录数：
	 * 根据传入的指定id查找指定的所有记录；
	 * id不能为空，为空会报id为空异常；
	 * 如果找不到会提示没有记录，并抛出记录为空的异常；
	 * @param id 传入的id值，可以是String类型，
	 * 			  也可以是Number类型，也可以是自定义的javabean，
	 * 			 但是该javabean必须实现java.io.Serializable序列号接口
	 * @return  返回指定id的所有记录的记录数
	 */
	public Integer getCount(ID id,String methodName);
	
	/**
	 * 查找满足指定条件的所有记录的记录数：
	 * 根据传入的指定whereCases查找指定的所有记录；
	 * whereCase不能为空，为空会报whereCases为空异常；
	 * 如果找不到会提示没有记录，并抛出记录为空的异常；
	 * @param map 传入的whereCases值，
	 * @return  返回指定id的所有记录的记录数
	 */
	public <V,K> Integer getCount(String methodName,Map<V,K> whereCases);
	
	
	public <V,K> T findForObject(String methodName,Map<V,K> whereCases);
	
	/**
	 * 查找所有记录：
	 * 没有条件的查询
	 * @return 返回查找的所有的记录
	 */
	public List<T> queryForList(String methodName);
	
	/**
	 * 查找所有记录：
	 * 根据传入的指定id查找指定的所有记录；
	 * id不能为空，为空会报id为空异常；
	 * 如果找不到会提示没有记录，并抛出记录为空的异常；
	 * @param id 传入的id值，可以是String类型，
	 * 			  也可以是Number类型，也可以是自定义的javabean，
	 * 			 但是该javabean必须实现java.io.Serializable序列号接口
	 * @return  返回指定id的所有记录
	 */
	public List<T> queryForList(ID id,String methodName);
	
	/**
	 * 查找所有记录：
	 * 根据传入的指定whereCases查找指定的所有记录；
	 * whereCase不能为空，为空会报whereCases为空异常；
	 * 如果找不到会提示没有记录，并抛出记录为空的异常；
	 * @param map 传入的whereCases值，
	 * @return  返回指定whereCases的所有记录
	 */
	public <V,K> List<T> queryForList(String methodName,Map<V,K> whereCases);
	
	/**
	 * 查找所有记录：
	 * 根据传入的指定whereCases查找指定的所有记录；
	 * whereCase不能为空，为空会报whereCases为空异常；
	 * 如果找不到会提示没有记录，并抛出记录为空的异常；
	 * @param map 传入的whereCases值，
	 * @return  返回指定whereCases的记录
	 */
	public <V,K> Map<V, K> queryForMap(String methodName,Map<V,K> whereCases,String mapKey);
	
	/**
	 * 查找所有记录：
	 * 根据传入的指定id查找指定的所有记录；
	 * id不能为空，为空会报id为空异常；
	 * 如果找不到会提示没有记录，并抛出记录为空的异常；
	 * @param id 传入的id值，可以是String类型，
	 * 			  也可以是Number类型，也可以是自定义的javabean，
	 * 			 但是该javabean必须实现java.io.Serializable序列号接口
	 * @param mapKey 要返回的key
	 * @return  返回指定id的所有记录
	 */
	public <V,K> Map<V, K> queryForMap(ID id,String methodName,String mapKey);
	
	/**
	 * 查找所有记录：
	 * 没有条件的查询
	 * @return 返回查找的所有的记录
	 */
	public <V,K> Map<V, K> queryForMap(String methodName,String mapKey);
	
	/**
	 * 调用存储过程：
	 * 执行失败抛出异常；
	 * 调用没有入参和出参的存储过程
	 */
	public void callProcedure(String methodName);
	
	/**
	 * 调用存储过程：
	 * 执行失败抛出异常；
	 * @param input 传入参数，不能为空，为空包异常
	 * @return 主要针对返回一个cursor的存储过程
	 */
	public <V,K> List<T> callProcedureForList(String methodName,Map<V,K> input);
	
	/**
	 * 调用存储过程：
	 * 执行失败抛出异常；
	 * @param input 传入参数，不能为空，为空包异常
	 * @return 主要针对返回多个cursor的存储过程
	 */
	public <V,K> Map<V,K> callProcedureForMap(String methodName,Map<V,K> input,String mapKey);
	
}

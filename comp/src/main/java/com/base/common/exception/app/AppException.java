package com.base.common.exception.app;
/**
 *项目名：
 *创建时间：2016-6-18
 *创建人：Aobo
 *类名：AppException
 *所属包名：com.comp.common.exception.app
 *项目名称：comp
 *类功能描述：所有的公共类的异常处理类
 *errorMessage：异常信息，所有的异常都要写一个异常信息，来说明异常的原因。
 *fieldName:异常的文件，即类文件
 *errorCode:错误代码，每个错误都应该有个错误代码，以便于好维护及快速查找错误，并定位开发者。
 *@since 1.7
 */
public class AppException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private String fieldName;
	private String errorCode;
	
	public AppException(){
		super();
	}
	
	/**
	 * 异常构造方法，msg为错误信息，
	 * 建议使用AppException(String msg,String errorCode)或
	 * AppException(String msg,String errorCode,Throwable cause)
	 * @param msg 错误信息
	 */
	public AppException(String msg) {
		super(msg);
		this.errorMessage = msg;
	}
	
	/**
	 * 异常构造方法，msg为错误信息,cause异常处理的超类，可以为任意类型的异常处理类
	 * 建议使用AppException(String msg,String errorCode)或
	 * AppException(String msg,String errorCode,Throwable cause)
	 * @param msg 错误信息
	 * @param cause 异常处理的超类，可以为任意类型的异常处理类
	 */
	public AppException(String msg,Throwable cause) {
		super(msg,cause);
		this.errorMessage = msg;
	}
	
	/**
	 * 异常构造方法，msg为错误信息,errorCode错误代码
	 * 建议使用本异常处理构造方法或
	 * AppException(String msg,String errorCode,Throwable cause)
	 * @param msg 错误信息
	 * @param errorCode 错误代码
	 */
	public AppException(String msg,String errorCode) {
		super(msg);
		this.errorMessage = msg;
		this.errorCode = errorCode;
	}
	
	/**
	 * 异常构造方法，msg为错误信息,errorCode错误代码
	 * 建议使用本异常处理构造方法或
	 * AppException(String msg,String errorCode)
	 * @param msg 错误信息
	 * @param errorCode 错误代码
	 * @param cause 异常处理的超类，可以为任意类型的异常处理类
	 */
	public AppException(String msg,String errorCode,Throwable cause) {
		super(msg,cause);
		this.errorMessage = msg;
		this.errorCode = errorCode;
	}
	
	/**
	 * 异常构造方法，msg为错误信息,errorCode错误代码
	 * 建议使用AppException(String msg,String errorCode)或
	 * AppException(String msg,String errorCode,Throwable cause)
	 * @param msg 错误信息
	 * @param errorCode 错误代码
	 * @param fieldName 文件名称
	 */
	public AppException(String msg,String errorCode,String fieldName){
		super(msg);
		this.errorMessage = msg;
		this.fieldName = fieldName;
		this.errorCode = errorCode;
	}
	
	/**
	 * 异常构造方法，msg为错误信息,errorCode错误代码
	 * 建议使用AppException(String msg,String errorCode)或
	 * AppException(String msg,String errorCode,Throwable cause)
	 * @param msg 错误信息
	 * @param errorCode 错误代码
	 * @param fieldName 文件名称
	 * @param cause 异常处理的超类，可以为任意类型的异常处理类
	 */
	public AppException(String msg,String errorCode,String fieldName,Throwable cause){
		super(msg,cause);
		this.errorMessage = msg;
		this.fieldName = fieldName;
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public String getFieldName(){
		return this.fieldName;
	}

	public String getMessage() {
		return errorMessage;
	}
}


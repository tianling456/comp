package com.base.entity;

import java.util.List;
import java.util.Map;

/**
 *项目名：
 *创建时间：2017-7-9
 *创建人：Aobo
 *类名：ResultEntity
 *所属包名：com.base.entity
 *项目名称：comp
 *类功能描述：
 */
public class Entity{
	private static final long serialVersionUID = 1L;
	private Map<String,Object> map;
	private List<Object> list;
	private String message;
	private String status;
	private Object object;
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
}


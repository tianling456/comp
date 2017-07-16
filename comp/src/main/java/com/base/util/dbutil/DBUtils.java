package com.base.util.dbutil;

import com.alibaba.druid.pool.DruidDataSource;
import com.base.util.common.Encoder;

/**
 *项目名：
 *创建时间：2017-6-24
 *创建人：Aobo
 *类名：DBUtils
 *所属包名：com.base.util.dbutil
 *项目名称：comp
 *类功能描述：
 */
public class DBUtils extends DruidDataSource{
	private static final long serialVersionUID = 1L;

	@Override
	public void setUsername(String username) {
		Encoder encoder = new Encoder();
		super.setUsername(encoder.decryptToAES(username));
	}

	@Override
	public void setPassword(String password) {
		Encoder encoder = new Encoder();
		super.setPassword(encoder.decryptToAES(password));
	}
	
}


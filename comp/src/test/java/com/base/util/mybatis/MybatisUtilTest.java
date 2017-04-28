package com.base.util.mybatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.base.util.page.PageView;
import com.comp.entities.User;

/**
 *项目名：
 *创建时间：2016-6-13
 *创建人：Aobo
 *类名：MybatisUtilTest
 *所属包名：com.util.mybatis
 *项目名称：comp
 *类功能描述：
 */
public class MybatisUtilTest {
	private final Logger logger=Logger.getLogger(MybatisUtilTest.class);
	@Test
	public void testGetSession(){
		SqlSession session = MybatisUtil.getSession();
		try {
			System.out.println(session.getConnection().getAutoCommit());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(session.getConnection());
	}
	@Test
	public void test(){
		PageView<User> page = new PageView<User>();
		page.setStartPage(1);
		page.setPageNow(1);
		page.setPageSize(5);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page", page);
		map.put("loginType1", "1");
		List list = MybatisUtil.getSession().selectList("com.comp.entities.User.queryForList", map);
		System.out.println(list);
	}
}


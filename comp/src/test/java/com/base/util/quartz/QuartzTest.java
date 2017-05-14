package com.base.util.quartz;


import org.apache.log4j.Logger;
import org.junit.Test;


/**
 *项目名：
 *创建时间：2017-5-14
 *创建人：Aobo
 *类名：QuartzTest
 *所属包名：com.base.util.quartz
 *项目名称：comp
 *类功能描述：
 */
public class QuartzTest {
	private final Logger logger=Logger.getLogger(QuartzTest.class);
	@Test
	public void testQuartz(){
		try {
			QuartzUtil.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("");
			e.printStackTrace();
		}
	}
}


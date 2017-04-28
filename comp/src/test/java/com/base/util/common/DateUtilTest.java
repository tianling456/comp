package com.base.util.common;

import java.util.Date;

/**
 *项目名：
 *创建时间：2016-8-7
 *创建人：Aobo
 *类名：DateUtil
 *所属包名：com.base.util.common
 *项目名称：comp
 *类功能描述：
 */
public class DateUtilTest {
	public static void main(String[] args) {
		Date date = new Date();
//		date = DateUtil.getCurrentDateLastDay(date);
		System.out.println(date);
		String lastDay = DateUtil.getCurrentDateLastDay2Str(date);
		System.out.println(lastDay);
		Integer day = DateUtil.getSumDaysByCurrentMonth(date);
		System.out.println(day);
		System.out.println(DateUtil.getFirstDay2Date(date));
		System.out.println(DateUtil.getFirstDayOfMonth(date));
		System.out.println(DateUtil.getFirstDayOfMonth2Str(date));
	}
}


package com.base.util.common;
/**
 * <p>Title: 时间和日期的工具类</p>
 * <p>Description: DateUtil类包含了标准的时间和日期格式，以及这些格式在字符串及日期之间转换的方法</p>
 * <p>Copyright: Copyright  advance,Inc. All Rights Reserved</p>
 * <p>Company: advance,Inc.</p>
 * @author advance
 * @version 1.0
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateUtil {
	//~ Static fields/initializers =============================================

//	private static String datePattern = "MM/dd/yyyy";
	private static String datePattern = "yyyy-MM-dd";
	private static String timePattern = datePattern + " HH:MM a";

	//~ Methods ================================================================

	/**
	 * Return default datePattern (MM/dd/yyyy)
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		return datePattern;
	}

	/**
	 * This method attempts to convert an Oracle-formatted date
	 * in the form dd-MMM-yyyy to mm/dd/yyyy.
	 *
	 * @param aDate date from database as a string
	 * @return formatted string for the ui
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(datePattern);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	public static final String date2Str(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(datePattern);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	public static final String date2Str(String pattern, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(pattern);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date/time
	 * in the format you specify on input
	 *
	 * @param aMask the date pattern the string is in
	 * @param strDate a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			return null;
		}

		return (date);
	}

	public static final Date str2Date(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			return null;
		}

		return (date);
	}

	/**
	 * This method returns the current date time in the format:
	 * MM/dd/yyyy HH:MM a
	 *
	 * @param theTime the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 *
	 * @return the current date
	 * @throws ParseException
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(datePattern);

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time
	 * in the format you specify on input
	 *
	 * @param aMask the date pattern the string is in
	 * @param aDate a date object
	 * @return a formatted string representation of the date
	 *
	 * @see java.text.SimpleDateFormat
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			System.out.print("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date based
	 * on the System Property 'dateFormat'
	 * in the format you specify on input
	 *
	 * @param aDate A date to convert
	 * @return a string representation of the date
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(datePattern, aDate);
	}

	/**
	 * This method converts a String to a date using the datePattern
	 *
	 * @param strDate the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 *
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String strDate)
			throws ParseException {
		Date aDate = null;

		try {

			aDate = convertStringToDate(datePattern, strDate);
		} catch (ParseException pe) {
			//log.error("Could not convert '" + strDate
			//          + "' to a date, throwing exception");
			pe.printStackTrace();
			return null;

		}
		return aDate;
	}

	//日期格式转换成时间戳
	public static long getTimeStamp(String pattern, String strDate) {
		long returnTimeStamp = 0;
		Date aDate = null;
		try {
			aDate = convertStringToDate(pattern, strDate);
		} catch (ParseException pe) {
			aDate = null;
		}
		if (aDate == null) {
			returnTimeStamp = 0;
		} else {
			returnTimeStamp = aDate.getTime();
		}
		return returnTimeStamp;
	}

	//获取当前日期的邮戳
	public static long getNowTimeStamp() {
		long returnTimeStamp = 0;
		Date aDate = null;
		try {
			aDate = convertStringToDate("yyyy-MM-dd HH:mm:ss", getNowDateTime());
		} catch (ParseException pe) {
			aDate = null;
		}
		if (aDate == null) {
			returnTimeStamp = 0;
		} else {
			returnTimeStamp = aDate.getTime();
		}
		return returnTimeStamp;
	}

	/**
	 *得到格式化后的系统当前日期
	 *@param strScheme 格式模式字符串
	 *@return 格式化后的系统当前时间，如果有异常产生，返回空串""
	 *@see java.util.SimpleDateFormat
	 */
	public static final String getNowDateTime(String strScheme) {
		String strReturn = null;
		Date now = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(strScheme);
			strReturn = sdf.format(now);
		} catch (Exception e) {
			strReturn = "";
		}
		return strReturn;
	}

	public static final String getNowDateTime() {
		String strReturn = null;
		Date now = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strReturn = sdf.format(now);
		} catch (Exception e) {
			strReturn = "";
		}
		return strReturn;
	}

	/**
	 *转化日期格式"MM/dd/YY、MM.dd.YY、MM-dd-YY、MM/dd/YY"，并输出为正常的格式yyyy-MM-dd
	 *@param strDate 待转换的日期格式
	 *@return 格式化后的日期，如果有异常产生，返回空串""
	 *@see java.util.SimpleDateFormat
	 */
	public static final String convertNormalDate(String strDate) {
		String strReturn = null;
		//先把传入参数分格符转换成java认识的分格符
		String[] date_arr = strDate.split("\\.|\\/|\\-");
		try {
			if (date_arr.length == 3) {
				if (date_arr[2].length() != 4) {
					String nowYear = getNowDateTime("yyyy");
					date_arr[2] = nowYear.substring(0, 2) + date_arr[2];
				}
				strReturn = DateUtil.getDateTime("yyyy-MM-dd",
						convertStringToDate(combineStringArray(date_arr, "/")));
			}

		} catch (Exception e) {
			return strReturn;
		}
		return strReturn;
	}

	/**
	 * 将字符串数组使用指定的分隔符合并成一个字符串。
	 * @param array 字符串数组
	 * @param delim 分隔符，为null的时候使用""作为分隔符（即没有分隔符）
	 * @return 合并后的字符串
	 * @since  0.4
	 */
	public static final String combineStringArray(String[] array, String delim) {
		int length = array.length - 1;
		if (delim == null) {
			delim = "";
		}
		StringBuffer result = new StringBuffer(length * 8);
		for (int i = 0; i < length; i++) {
			result.append(array[i]);
			result.append(delim);
		}
		result.append(array[length]);
		return result.toString();
	}

	public static final int getWeekNum(String strWeek) {
		int returnValue = 0;
		if (strWeek.equals("Mon")) {
			returnValue = 1;
		} else if (strWeek.equals("Tue")) {
			returnValue = 2;
		} else if (strWeek.equals("Wed")) {
			returnValue = 3;
		} else if (strWeek.equals("Thu")) {
			returnValue = 4;
		} else if (strWeek.equals("Fri")) {
			returnValue = 5;
		} else if (strWeek.equals("Sat")) {
			returnValue = 6;
		} else if (strWeek.equals("Sun")) {
			returnValue = 0;
		} else if (strWeek == null) {
			returnValue = 0;
		}

		return returnValue;
	}
	/**
	 * 获取日期字符串中的中文时间表示字符串
	 * @param strDate
	 * @return
	 */
	public static final String getSabreTime(String strDate) {
		String strReturn = "";
		try {

			Date d = DateUtil.str2Date("yyyy-MM-dd HH:mm:ss", CTool.replace(
					strDate, "T", " "));
			strReturn = DateUtil.date2Str("hh:mm aaa", d);

		} catch (Exception e) {
			return strReturn;
		}
		return strReturn;
	}
	/**
	 * 获取日期字符串中的中文日期表示字符串
	 * @param strDate
	 * @return
	 */
	public static final String getSabreDate(String strDate) {
		String strReturn = "";
		try {
			String p = null;
			if (strDate.length() > 10)
				p = "yyyy-MM-dd HH:mm:ss";
			else
				p = "yyyy-MM-dd";
			Date d = DateUtil.str2Date(p, CTool.replace(strDate, "T", " "));
			strReturn = DateUtil.date2Str("EEE d-MMM", d);

		} catch (Exception e) {
			return strReturn;
		}
		return strReturn;
	}
	/**
	 * 获取日期字符串的中文日期时间表示
	 * @param strDate
	 * @return
	 */
	public static final String getSabreDateTime(String strDate) {
		String strReturn = "";
		try {
			String p = null;
			if (strDate.length() > 10)
				p = "yyyy-MM-dd HH:mm:ss";
			else
				p = "yyyy-MM-dd";
			Date d = DateUtil.str2Date(p, CTool.replace(strDate, "T", " "));
			strReturn = DateUtil.date2Str("EEE d-MMM hh:mm aaa", d);

		} catch (Exception e) {
			return strReturn;
		}
		return strReturn;
	}

	/**
	 *得到格式化后的指定日期
	 *@param strScheme 格式模式字符串
	 *@param date 指定的日期值
	 *@return 格式化后的指定日期，如果有异常产生，返回空串""
	 *@see java.util.SimpleDateFormat
	 */
	public static final String getDateTime(Date date, String strScheme) {
		String strReturn = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(strScheme);
			strReturn = sdf.format(date);
		} catch (Exception e) {
			strReturn = "";
		}

		return strReturn;
	}
	/**
	 * 获取日期
	 * @param timeType 时间类型，譬如：Calendar.DAY_OF_YEAR
	 * @param timenum  时间数字，譬如：-1 昨天，0 今天，1 明天
	 * @return 日期
	 */
	public static final Date getDateFromNow(int timeType, int timenum){
		Calendar cld = Calendar.getInstance();
		cld.set(timeType, cld.get(timeType) + timenum);
		return cld.getTime();
	}
	/**
	 * 获取日期
	 * @param timeType 时间类型，譬如：Calendar.DAY_OF_YEAR
	 * @param timenum  时间数字，譬如：-1 昨天，0 今天，1 明天
	 * @param format_string 时间格式，譬如："yyyy-MM-dd HH:mm:ss"
	 * @return 字符串
	 */
	public static final String getDateFromNow(int timeType, int timenum, String format_string){
		if ((format_string == null)||(format_string.equals("")))
			format_string = "yyyy-MM-dd HH:mm:ss";
		Calendar cld = Calendar.getInstance();
		Date date = null;
	    DateFormat df = new SimpleDateFormat(format_string);
		cld.set(timeType, cld.get(timeType) + timenum);
	    date = cld.getTime();
	    return df.format(date);
	}
	/**
	 * 获取当前日期的字符串
	 * @param format_string 时间格式，譬如："yyyy-MM-dd HH:mm:ss"
	 * @return 字符串
	 */
	public static final String getDateNow(String format_string){
		if ((format_string == null)||(format_string.equals("")))
			format_string = "yyyy-MM-dd HH:mm:ss";
		Calendar cld = Calendar.getInstance();
	    DateFormat df = new SimpleDateFormat(format_string);
	    return df.format(cld.getTime());
	}
	
	/**
	 * 获取指定日期的月份
	 * @param date
	 * @return
	 */
	public static final Integer getFirstDay2Date(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Integer currentMonth = c.get(Calendar.MONTH)+1;      //得到指定日期的月份
		return currentMonth;
	}
	
	/**
	 * 获取指定日期的最后一天的时间格式
	 * @param date
	 * @return
	 */
	public static final Date getCurrentDateLastDay(Date date){
		Calendar c = Calendar.getInstance();
		Date lastDay = new Date();
		c.setTime(date);
		int day = c.getActualMaximum(Calendar.DATE);   //获取该月的最大天数,即该月的总天数
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		c.clear();
		c.set(year, month, day);
		lastDay = c.getTime();
		return lastDay;
	}
	
	/**
	 * 获取指定日期的最后一天的时间格式
	 * @param date
	 * @return
	 */
	public static final String getCurrentDateLastDay2Str(Date date){
		Calendar c = Calendar.getInstance();
		Date lastDay = new Date();
		c.setTime(date);
		int day = c.getActualMaximum(Calendar.DATE);   //获取该月的最大天数,即该月的总天数
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		c.clear();
		c.set(year, month, day);
		lastDay = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		return sdf.format(lastDay);
	}
	
	/**
	 * 获取指定日期的该月份的总天数
	 * @param date
	 * @return
	 */
	public static final Integer getSumDaysByCurrentMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Integer days = c.getActualMaximum(Calendar.DATE);   //获取该月的最大天数,即该月的总天数
		return days;
	}
	
	/**
	 * 获取指定日期的该月的第一天
	 * @param date
	 * @return
	 */
	public static final Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1);
        return calendar.getTime();
    }
	
	/**
	 * 获取指定日期的该月的第一天
	 * @param date
	 * @return
	 */
	public static final String getFirstDayOfMonth2Str(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1);
        Date firstDay = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        return sdf.format(firstDay);
    }
	
	/**
     * 返回指定年月的月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static final Date getLastDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }
    /**
     * 返回指定年月的月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static final Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        return calendar.getTime();
    }
	
	 /**
     * 返回指定日期的上个月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                     calendar.get(Calendar.MONTH) - 1, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    
    /**
     * 返回指定日期的季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfQuarter(calendar.get(Calendar.YEAR),
                                    getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 1 - 1;
        } else if (quarter == 2) {
            month = 4 - 1;
        } else if (quarter == 3) {
            month = 7 - 1;
        } else if (quarter == 4) {
            month = 10 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getFirstDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfQuarter(calendar.get(Calendar.YEAR),
                                   getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 3 - 1;
        } else if (quarter == 2) {
            month = 6 - 1;
        } else if (quarter == 3) {
            month = 9 - 1;
        } else if (quarter == 4) {
            month = 12 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的上一季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfLastQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的上一季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 12 - 1;
        } else if (quarter == 2) {
            month = 3 - 1;
        } else if (quarter == 3) {
            month = 6 - 1;
        } else if (quarter == 4) {
            month = 9 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季度
     *
     * @param date
     * @return
     */
    public static int getQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }
    
    /**  
     * 计算两个日期之间相差的天数  
     * @param startDate 较小的时间 
     * @param endDate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static Integer daysBetween(Date startDate, Date endDate) throws ParseException {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        startDate=sdf.parse(sdf.format(startDate));
        endDate=sdf.parse(sdf.format(endDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long startTime = cal.getTimeInMillis();
        cal.clear();
        cal.setTime(endDate);
        long endTime = cal.getTimeInMillis();
        long between_days=(endTime-startTime)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }    
	      
	/** 
	*字符串的日期格式的计算 
	*/  
    public static Integer daysBetween(String startDate, String endDate) throws ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(startDate));
        long startTime = cal.getTimeInMillis();
        cal.clear();
        cal.setTime(sdf.parse(endDate));
        long endTime = cal.getTimeInMillis();
        long between_days=(endTime-startTime)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days)); 
    }  
}

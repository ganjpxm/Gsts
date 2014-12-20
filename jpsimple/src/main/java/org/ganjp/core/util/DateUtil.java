/*
 * $Id: DateUtil.java,v 1.1 2011/05/12 09:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>文件操作工具类</p>
 *
 * @author ganjp
 * @since 1.0
 */
public class DateUtil {
	private static Logger log = LoggerFactory.getLogger(DateUtil.class);
	
	/**
	 * get the number of day by month and year
	 * @param month
	 * @param year
	 * @return
	 */
	public static int getDays(int month, int year) {
		switch (month) {
        	case 1: case 3: case 5: case 7: case 8: case 10: case 12:
            	return 31;
            case 4: case 6: case 9: case 11:
            	return 30;
            case 2:
            	if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0))
                	return 29;
            	else
            		return 28;
            default:
             System.out.println("Invalid month.");
		}
		return 0;
	}
	
	/**
	 * 获取当前月的第一天
	 *
	 * @return java.lang.String
	 */
	public static String getFirstDateOfMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-");
		String s = df.format(new Date());
		s += "01";
		return s;
	}
	/**
	 * 
	 * @return
	 */
	public static String getNowDateTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}
	
	/**
	 * <p>
	 * 把指定时间按指定格式解析为Date，如果解决失败，将返回null
	 * </p>
	 * <p>
	 * 例如，时间“20091103”按“yyMMdd”解析之后得到2009年11月3日
	 * </p>
	 *
	 * @param dateString A date string in the format
	 * @return The corresponding Timestamp or null
	 */
	public static Date parseDate(String dateString, String timeFormat) {
		if (dateString == null || dateString.trim().length() == 0) {
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat(timeFormat);
		try {
			java.util.Date date = format.parse(dateString.trim());
			return date;
		} catch (ParseException e) {
			return null;
		}
	}
	public static void main(String[] args) {
		try {
			System.out.println(getDays(2,2012));
			System.out.println(getNowDateTime());
			System.out.println(getFirstDateOfMonth());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
	}
}

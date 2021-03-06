/**
 * $Id: DateUtil.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.gcore.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Date Utility for internal use.</p>
 *
 * @author GanJianping
 * @since 1.0
 */
public class DateUtil {
	private static Logger log = LoggerFactory.getLogger(DateUtil.class);
	
	/**
	 * <p>get the total number of day by month and year</p>
	 * 
	 * @param month eg:4
	 * @param year eg:2012
	 * @return
	 */
	public static int getTotalDay(int month, int year) {
		int totalDay = 0; 
		switch (month) {
        	case 1: case 3: case 5: case 7: case 8: case 10: case 12:
            	totalDay = 31; break;
            case 4: case 6: case 9: case 11:
            	totalDay = 30; break;
            case 2:
            	if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)) {
            		totalDay = 29;
            	} else {
            		totalDay = 28;
            	}
            	break;
            default:
            	log.error("Invalid month.");
		}
		log.debug("month : " + month + "year : " + year + " get days : " + totalDay);
		return totalDay;
	}
	
	/**
	 * <p>get now date formatted locale</p>
	 * 
	 * @param locale eg:new Locale("zh_CN")
	 * @return
	 */
	public static String getNowDateTime(Locale locale) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String localDateTime = dateFormat.format(date);
		log.debug("get now date time (locale:" + locale.toString() + ") : " + localDateTime);
		return localDateTime;
	}
	
	public static Timestamp getNowTimstamp() {
		return convertDateToTimestamp(new Date());
	}
	
    /**
     * date to timestamp.
     * @param date
     * @return
     */
    public static Timestamp convertDateToTimestamp(Date date) {
    	return convertStringToTimestamp(DateUtil.getDate(date,"yyyy-MM-dd HH:mm:ss"));
    }
    
    /**
     * string to timestamp
     * @param string
     * @return
     */
    public static Timestamp convertStringToTimestamp(String string) {
    	return (Timestamp) ConvertUtils.convert(string, Timestamp.class);
    }

	
    /**
     * This method attempts to convert an Oracle-formatted date
     * in the form dd-MMM-yyyy to mm/dd/yyyy.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static final String getDate(Date aDate,String formate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(formate);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    public static Time parseTime(String dateString, String Format) {
		if (dateString == null || dateString.trim().length() == 0) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(Format);
		Time time = null;
		try {
			long ms = df.parse(dateString).getTime();
			time = new Time(ms);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		
		return time;
	}
    
    public static Time parseTime(String dateString) {
    	return parseTime(dateString, "HH:mm");
    }
    
	public static Date parseDate(String dateString, String Format) {
		if (dateString == null || dateString.trim().length() == 0) {
			return null;
		}
		DateFormat df = new SimpleDateFormat(Format);
		try {
			Date date = (Date)df.parse(dateString);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date parseDate(String dateString) {
		return DateUtil.parseDate(dateString, "dd/MM/yyyy");
	}
	
	public static String formatDate(String dateString, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(new Date(dateString));
	}

	/**
	 * <p>get day month year</p>
	 * 
	 * @param aDate
	 * @return
	 */
    public static final String[] getDayMonthYear(Date aDate, boolean isAddSpace) {
        String[] dmys = new String[3];
        if (aDate != null) {
           Calendar cal=Calendar.getInstance();
           cal.setTime(aDate);
           int day = cal.get(Calendar.DAY_OF_MONTH);
           if (day<10) {
        	   if (isAddSpace) {
        		  dmys[0] = "0 " + day;
        	   } else {
        		  dmys[0] = "0" + day;
        	   }
           } else {
        	   String tmp = day + "";
        	   if (isAddSpace) {
        		   dmys[0] =  tmp.substring(0, 1) + " " + tmp.substring(1, 2);
        	   } else {
        		   dmys[0] = tmp;
        	   }
           }
           int month = cal.get(Calendar.MONTH) + 1;
           if (month<10) {
        	   if (isAddSpace) {
        		   dmys[1] = "0 " + month;
        	   } else {
        		   dmys[1] = "0" + month;
        	   }
           } else {
        	   String tmp = month + "";
        	   if (isAddSpace) {
        		   dmys[1] = tmp.substring(0, 1) + " " + tmp.substring(1, 2);
        	   } else {
        		   dmys[1] = tmp;
        	   }
           }
           String year = cal.get(Calendar.YEAR) + "";
           if (isAddSpace) {
        	   dmys[2] = year.substring(0, 1) + " " + year.substring(1, 2) + " " + year.substring(2, 3) + " " + year.substring(3, 4);
           } else {
        	   dmys[2] = year;
           }
        }
        return dmys;
    }
    
    public static final String[] getDayMonthYearWithSpace(Date aDate) {
    	return getDayMonthYear(aDate, true);
    }
    
    public static final String getDateString(Date aDate) {
    	if (aDate==null) {
    		return "";
    	} else {
	    	String[] date = getDayMonthYear(aDate, false);
	    	return date[0]+"/"+date[1]+"/"+date[2];
    	}
    }
    
    public static final String getDayString(Date aDate) {
    	if (aDate==null) {
    		return "";
    	} else {
	    	String[] date = getDayMonthYear(aDate, false);
	    	return date[0];
    	}
    }
    
    public static final String getDayWeekString(Date aDate) {
    	if (aDate==null) {
    		return "";
    	} else {
	    	String[] date = getDayMonthYear(aDate, false);
	    	return date[0] + " " + getWeekDdEnmmYyyy(aDate);
    	}
    }

    public static final String getNowMmYyyy() {
    	String[] date = getDayMonthYear(new Date(), false);
    	return date[1]+"/"+date[2];
    }
    
    public static final String getNowHhMm() {
    	Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
    	return getFixedTwoStr(cal.get(Calendar.HOUR_OF_DAY)) + ":" + getFixedTwoStr(cal.get(Calendar.MINUTE));
    }
    
    public static final String getNowHhMmAmPm() {
    	Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String am_pm = "";
        if(cal.get(Calendar.AM_PM) == 0)   am_pm = "AM";   else   am_pm = "PM";
    	return getFixedTwoStr(cal.get(Calendar.HOUR_OF_DAY)) + ":" + getFixedTwoStr(cal.get(Calendar.MINUTE)) + " " + am_pm;
    }
    
    public static final String getNowHh() {
    	Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
    	return getFixedTwoStr(cal.get(Calendar.HOUR_OF_DAY));
    }
    
    public static String getFixedTwoStr(int i) {
    	String fixedTwoStr = i + "";
    	if (i<10) {
    		fixedTwoStr = "0" + i;
    	}
    	return fixedTwoStr;
    }
    
    public static final String getNowDateStr() {
    	String[] date = getDayMonthYear(new Date(), false);
    	return date[0] + "/" + date[1] + "/" + date[2];
    }
    
    public static final String getTomorrowDateStr() {
    	Calendar cal = Calendar.getInstance(); 
    	cal.setTime(new Date()); 
    	cal.add(Calendar.DATE, 1);
    	String yearStr = cal.get(Calendar.YEAR) + "";
    	int month = cal.get(Calendar.MONTH) + 1;
    	String monthStr = month<10?"0"+month:month+"";
    	int day = cal.get(Calendar.DAY_OF_MONTH);
    	String dayStr = day<10 ? "0"+day : day+"";
    	return dayStr + "/" + monthStr + "/" + yearStr;
    }
    
    public static final String getBeforeTenDayDateStr() {
    	Calendar cal = Calendar.getInstance(); 
    	cal.setTime(new Date()); 
    	cal.add(Calendar.DATE, -10);
    	String yearStr = cal.get(Calendar.YEAR) + "";
    	int month = cal.get(Calendar.MONTH) + 1;
    	String monthStr = month<10?"0"+month:month+"";
    	int day = cal.get(Calendar.DAY_OF_MONTH);
    	String dayStr = day<10 ? "0"+day : day+"";
    	return dayStr + "/" + monthStr + "/" + yearStr;
    }
    
    public static final String getBeforeDateStr(int days) {
    	Calendar cal = Calendar.getInstance(); 
    	cal.setTime(new Date()); 
    	cal.add(Calendar.DATE, -days);
    	String yearStr = cal.get(Calendar.YEAR) + "";
    	int month = cal.get(Calendar.MONTH) + 1;
    	String monthStr = month<10 ? "0"+month : month+"";
    	int day = cal.get(Calendar.DAY_OF_MONTH);
    	String dayStr = day<10 ? "0"+day : day+"";
    	return dayStr + "/" + monthStr + "/" + yearStr;
    }
    
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
    
    public static final String getNowYyyyMmDmHhMmSs() {
    	Calendar cal = Calendar.getInstance();
    	String yearStr = cal.get(Calendar.YEAR) + "";
    	int month = cal.get(Calendar.MONTH) + 1;
    	String monthStr = month<10?"0"+month:month+"";
    	int day = cal.get(Calendar.DAY_OF_MONTH);
    	String dayStr = day<10 ? "0"+day : day+"";
    	int hour = cal.get(Calendar.HOUR_OF_DAY);
    	String hourStr = hour<10 ? "0"+hour : hour+"";
    	int minute = cal.get(Calendar.MINUTE);
    	String minuteStr = minute<10 ? "0"+minute : minute+"";
    	int second = cal.get(Calendar.SECOND);
    	String secondStr = second<10 ? "0"+second : second+"";
    	return yearStr+monthStr+dayStr+hourStr+minuteStr+secondStr;
    }
    
    /**
     * <p>01/2012 to January 2012</p>
     * 
     * @param monthSlashYear
     * @return
     */
    public static final String convertMonth(String monthSlashYear) {
    	String[] monthYear = monthSlashYear.split("/");
    	String month = monthYear[0];
    	String year = monthYear[1];
    	return getEnMonth(month) + " " + year;
    }
    
    public static final String getDdEnmmYyyy(Date date) {
    	if (date==null) {
    		return "";
    	}
    	String ddEnmmYyyy = "";
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        ddEnmmYyyy += cal.get(Calendar.DAY_OF_MONTH) + " ";
        ddEnmmYyyy += getEnMonth(cal.get(Calendar.MONTH) + 1 +"") + " ";
        ddEnmmYyyy += cal.get(Calendar.YEAR);
        return ddEnmmYyyy;
    }
    
    public static final String getShortDateTime(Date date, Time time) {
    	if (date==null) {
    		return "";
    	}
    	Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int thisDay = cal.get(Calendar.DAY_OF_MONTH);
        int thisMonth = cal.get(Calendar.MONTH) + 1;
        int thisYear = cal.get(Calendar.YEAR);
        
        cal.setTime(addDays(new Date(), 1));
        int nextDay = cal.get(Calendar.DAY_OF_MONTH);
        int nextMonth = cal.get(Calendar.MONTH) +1 ;
        int nextYear = cal.get(Calendar.YEAR);
        
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        
        String shortDateTime = "";
        if (thisDay==day && thisMonth==month && thisYear==year) {
        	shortDateTime = getTimeAmPmStr(time);
        } else if (nextDay==day && nextMonth==month && nextYear==year) {
        	shortDateTime = "Tmrw " + getTimeStr(time);
        } else if (thisYear==year) {
        	shortDateTime = getFixedTwoStr(day) + " " + getEnMonth(month + "") + " " + getTimeStr(time);
        } else {
        	shortDateTime = getFixedTwoStr(day) + " " + getEnMonth(month + "") + " " + year + " " + getTimeStr(time);
        }
        return shortDateTime;
    }
    
    public static final String getWeekDdEnmmYyyy(Date date) {
    	if (date==null) {
    		return "";
    	}
    	String ddEnmmYyyy = "";
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        ddEnmmYyyy += getEnDayOfWeek(cal.get(Calendar.DAY_OF_WEEK)) + ", ";
        ddEnmmYyyy += cal.get(Calendar.DAY_OF_MONTH) + " ";
        ddEnmmYyyy += getEnMonth(cal.get(Calendar.MONTH) + 1 +"") + " ";
        ddEnmmYyyy += cal.get(Calendar.YEAR);
        return ddEnmmYyyy;
    }
    
    public static final String getDdEnmmYyyyHHmm(Date date) {
    	if (date==null) {
    		return "";
    	}
    	String ddEnmmYyyyHHmm = "";
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        ddEnmmYyyyHHmm += cal.get(Calendar.DAY_OF_MONTH) + " ";
        ddEnmmYyyyHHmm += getEnMonth(cal.get(Calendar.MONTH) + 1 +"") + " ";
        ddEnmmYyyyHHmm += cal.get(Calendar.YEAR) + " ";
        ddEnmmYyyyHHmm += getFixedTwoStr(cal.get(Calendar.HOUR_OF_DAY)) + ":" + getFixedTwoStr(cal.get(Calendar.MINUTE));
        return ddEnmmYyyyHHmm;
    }
    
    public static final String getDdEnmmYyyyHHmmAmPm(Date date) {
    	if (date==null) {
    		return "";
    	}
    	String ddEnmmYyyyHHmm = "";
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        ddEnmmYyyyHHmm += cal.get(Calendar.DAY_OF_MONTH) + " ";
        ddEnmmYyyyHHmm += getEnMonth(cal.get(Calendar.MONTH) + 1 +"") + " ";
        ddEnmmYyyyHHmm += cal.get(Calendar.YEAR) + " ";
        String am_pm = "";
        if(cal.get(Calendar.AM_PM) == 0)   am_pm = "AM";   else   am_pm = "PM";
        ddEnmmYyyyHHmm += getFixedTwoStr(cal.get(Calendar.HOUR_OF_DAY)) + ":" + getFixedTwoStr(cal.get(Calendar.MINUTE)) + " " + am_pm;
        return ddEnmmYyyyHHmm;
    }
    
    public static final String getWeekDdEnmmYyyyHHmm(Date date) {
    	if (date==null) {
    		return "";
    	}
    	String ddEnmmYyyyHHmm = "";
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        ddEnmmYyyyHHmm += getEnDayOfWeek(cal.get(Calendar.DAY_OF_WEEK)) + ", ";
        ddEnmmYyyyHHmm += cal.get(Calendar.DAY_OF_MONTH) + " ";
        ddEnmmYyyyHHmm += getEnMonth(cal.get(Calendar.MONTH) + 1 +"") + " ";
        ddEnmmYyyyHHmm += cal.get(Calendar.YEAR) + " ";
        ddEnmmYyyyHHmm += getFixedTwoStr(cal.get(Calendar.HOUR_OF_DAY)) + ":" + getFixedTwoStr(cal.get(Calendar.MINUTE));
        return ddEnmmYyyyHHmm;
    }
    
    public static final String getDdMmYyyy(Date date) {
    	if (date==null) {
    		return "";
    	}
    	String ddMmYyyy = "";
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        ddMmYyyy += cal.get(Calendar.DAY_OF_MONTH);
        ddMmYyyy += cal.get(Calendar.MONTH) + 1;
        ddMmYyyy += cal.get(Calendar.YEAR);
        return ddMmYyyy;
    }
    
    public static final String getEnDayOfWeek(int day) {
    	if (day==1) {
    		return "Sun";
    	} else if (day==2) {
    		return "Mon";
    	} else if (day==3) {
    		return "Tue";
    	} else if (day==4) {
    		return "Wed";
    	} else if (day==5) {
    		return "Thu";
    	} else if (day==6) {
    		return "Fri";
    	} else if (day==7) {
    		return "Sat";
    	}
    	return "";
    }
    
    public static final String getEnMonth(String month) {
    	if (month.equals("01") || month.equals("1")) {
    		month = "Jan";
    	} else if (month.equals("02") || month.equals("2")) {
    		month = "Feb";
    	} else if (month.equals("03") || month.equals("3")) {
    		month = "Mar";
    	} else if (month.equals("04") || month.equals("4")) {
    		month = "Apr";
    	} else if (month.equals("05") || month.equals("5")) {
    		month = "May";
    	} else if (month.equals("06") || month.equals("6")) {
    		month = "June";
    	} else if (month.equals("07") || month.equals("7")) {
    		month = "July";
    	} else if (month.equals("08") || month.equals("8")) {
    		month = "Aug";
    	} else if (month.equals("09") || month.equals("9")) {
    		month = "Sept";
    	} else if (month.equals("10")) {
    		month = "Oct";
    	} else if (month.equals("11")) {
    		month = "Nov";
    	} else if (month.equals("12")) {
    		month = "Dec";
    	}
    	return month;
    }
	public static void main(String[] args) {
		System.out.println(convertTime(1368821585000l));
		System.out.println(pareDdMmYyyyHhMmSs("25/5/2009 10:10:10"));
		System.out.println(parseDate("25/5/2009 10:10:10","dd/MM/yyyy HH:mm:ss"));
		System.out.println(getDayMonthYearWithSpace(new Date())[0]);
		//getTotalDay(4, 2012);
		//getNowDateTime(new Locale("zh_CN"));
		 log.debug(getNowYyyyMmDmHhMmSs());
	}
	
	
	public static Date pareRealDatabaseDate(String dateString) throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return (Date)df.parse(dateString);
	}
	
	public static long pareDdMmYyyyHhMmSs(String dateString) {
		return parseDateOrDateTime(dateString).getTime();
	}
	
	public static Date parseDateOrDateTime(String dateString) {
		if (StringUtil.isNotEmpty(dateString) && dateString.indexOf("/")!=-1 ) {
			try {
				if (dateString.indexOf(":")!=-1) {
					if (dateString.length()==16) {
						return parseDate(dateString,"dd/MM/yyyy HH:mm");
					} else {
						return parseDate(dateString,"dd/MM/yyyy HH:mm:ss");
					}
				} else {
					return parseDate(dateString,"dd/MM/yyyy");
				}
			} catch(Exception ex) {
				log.error(ex.getMessage());
			}
		}
		return DateUtil.parseDate(dateString, "dd/MM/yyyy");
	}
	
	public static String parseDatabaseDate(String dateString) throws ParseException {
		    String oldScheduledDate = dateString.replaceAll("/", "-");
	        DateFormat oldFormatter = new SimpleDateFormat("dd-MM-yyyy");
	        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        Date oldDate = (Date)oldFormatter.parse(oldScheduledDate);
	           return  formatter.format(oldDate);		 
	}	
	
	public static final String getDateTimeStr(Timestamp timestamp) {
    	if (timestamp==null) {
    		return null;
    	}
    	Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);
    	return getFixedTwoStr(cal.get(Calendar.DAY_OF_MONTH)) + "/" + getFixedTwoStr((cal.get(Calendar.MONTH) + 1)) + "/" + cal.get(Calendar.YEAR) + " " + 
    			getFixedTwoStr(cal.get(Calendar.HOUR_OF_DAY)) + ":" + getFixedTwoStr(cal.get(Calendar.MINUTE));
    }
	
	public static final String getTimeStr(Time time) {
    	if (time==null) {
    		return "";
    	}
    	Calendar cal = Calendar.getInstance();
        cal.setTime(time);
    	return getFixedTwoStr(cal.get(Calendar.HOUR_OF_DAY)) + ":" + getFixedTwoStr(cal.get(Calendar.MINUTE));
    }
	
	public static final String getTimeAmPmStr(Time time) {
    	if (time==null) {
    		return "";
    	}
    	Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        String am_pm = "";
        if(cal.get(Calendar.AM_PM) == 0)   am_pm = "AM";   else   am_pm = "PM";
    	return getFixedTwoStr(cal.get(Calendar.HOUR_OF_DAY)) + ":" + getFixedTwoStr(cal.get(Calendar.MINUTE)) + " " + am_pm;
    }
	
	
	public static final String getTimeDateStr(Date date) {
    	if (date==null) {
    		return null;
    	}
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
    	return getFixedTwoStr(cal.get(Calendar.HOUR_OF_DAY)) + ":" + getFixedTwoStr(cal.get(Calendar.MINUTE)) + " " +
    	getFixedTwoStr(cal.get(Calendar.DAY_OF_MONTH)) + "/" + getFixedTwoStr((cal.get(Calendar.MONTH) + 1)) + "/" + cal.get(Calendar.YEAR);
    }
	
	public static String convertTime(long time){
	    Date date = new Date(time);
	    Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
	    return format.format(date).toString();
	}
	
	public static String getWeekOfDate(Date dt) { 
		if (dt !=null) {
			String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; 
		    Calendar cal = Calendar.getInstance(); 
		    cal.setTime(dt); 
		    int w = cal.get(Calendar.DAY_OF_WEEK) - 1; 
		    if (w < 0) {
		    	w = 0; 
		    }
		    return weekDays[w];
		} else {
			return "";
		}
	     
	} 
	
	public static Date getFirstDateOfCurrentMonth() {
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
	public static Date getLastDateOfCurrentMonth() {
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
	public static final String getMonthEn(Date date) {
    	if (date==null) {
    		return "";
    	}
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        return getEnMonth(month+"");
    }
}

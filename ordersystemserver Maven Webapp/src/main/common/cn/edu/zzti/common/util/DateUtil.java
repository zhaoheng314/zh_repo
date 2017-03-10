package cn.edu.zzti.common.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class DateUtil {
	
	/**
	 * 日期格式
	 */
	public static final String YYYY_MM = "yyyy-MM";

	/**
	 * 日期格式
	 */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	
	/**
	 * 日期格式
	 */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	public static final String YYYY_MM_DD1 = "YYYY/MM/DD";
	
	/**
	 * 日期格式
	 */
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	
	/**
	 * 日期格式
	 */
	public static final String YYYY_MM_DD_HH_MM_SSS = "yyyyMMddHHmmsss";
	
	/**
	 * 日期格式
	 */
	public static final String YYYYMM = "yyyyMM";

	/**
	 * 日期格式
	 */
	public static String YYYYMMDD = "yyyyMMdd";
	
	/**
	 * 日期格式： xxxx年xx月xx日
	 */
	public static final String DATE_FORMAT_EIGHTEEN = "yyyy年MM月dd日";

	/**
	 * 获取时间格式："yyyy/MM/dd"
	 * @param date
	 * @return
	 */
	public static Date toDate(String date){  	

		return toDate(date, YYYY_MM_DD1);
	}

	/**
	 * ※日期的字符串格式转Date对象实例
	 * 
	 * @param date
	 *            字符串格式的日期
	 * @param pattern
	 *            日期格式
	 * @return 返回Date对象实例
	 */
	public static Date toDate(String date, String pattern) {
		Date date2;

		if (isEmpty(pattern)) {
			pattern = YYYY_MM_DD_HH_MM_SS;
		}
		try {
			if (date != null && !date.equals("")) {
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				date2 = sdf.parse(date);
			} else {
				date2 = toLocalDate(new Date(), pattern);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Date();
		}
		return date2;
	}

	/**
	 * 日期的字符串格式转Date对象实例
	 * @param date
	 * @return
	 */
	public static String toStr(Date date) {
		return toStr(date, YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 日期的字符串格式转Date对象实例
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String toStr(Date date, String pattern) {
		String date2 = "";

		if (isEmpty(pattern)) {
			pattern = YYYY_MM_DD_HH_MM_SS;
		}

		if (date != null && !date.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			date2 = sdf.format(date);
		}

		return date2;
	}

	//得到系统时间
	public  static Date getSystemTime() {
		Calendar now = Calendar.getInstance();
		return now.getTime();
	}

	//得到系统时间
	public  static Date getMinTime() {
		Calendar date = Calendar.getInstance();
		date.set(1997, 1, 1);
		return date.getTime();
	}

	public static String DateNow() {
		return toStr(getSystemTime(), YYYY_MM_DD_HH_MM_SS) ;
	}

	/**
	 * ※其他地区时间转成北京时间
	 * 
	 * @param date
	 *            日期实例对象
	 * @param pattern
	 *            日期格式
	 */
	public static Date toLocalDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		TimeZone zone = new SimpleTimeZone(28800000, "Asia/Shanghai");// +8区
		sdf.setTimeZone(zone);
		String sdate = sdf.format(date);

		SimpleDateFormat sdf2 = new SimpleDateFormat(pattern);
		try {
			return sdf2.parse(sdate);
		} catch (Exception e) {
			e.printStackTrace();
			return new Date();
		}
	}

	/**
	 * 获取年月日结构路径片段
	 * @return
	 */
	public static String getDatePath(){
		Calendar calendar = Calendar.getInstance();

		String path = "";
		int year = calendar.get(Calendar.YEAR);
		path = year+"";
		int month = calendar.get(Calendar.MONTH)+1;
		if(month<10)
			path = path+File.separator+"0"+month;
		else {
			path = year+File.separator+month;
		}
		int day = calendar.get(Calendar.DATE);
		if(day<10)
			return path+File.separator+"0"+day+File.separator;
		else {
			return path+File.separator+day+File.separator;
		}
	}

	/**
	 * 返回毫秒
	 * @param date
	 * @return
	 */
	public static long getMillis(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	private static boolean isEmpty(String str) {
		if (str != null && str.trim().length() > 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 获取某一天的开始时间 即 00:00:00 create by zhaoheng
	 * @return
	 */
	public static String getBeginTimeOfToday(){
		String nowTime = DateNow();
		String [] arr = nowTime.split(" ");
		StringBuffer sb = new StringBuffer(arr[0]);
		sb.append(" 00:00:00");
		String beginTimeOfToday = sb.toString();
		return beginTimeOfToday;
	}
    
	/**
	 * 获取本周一的开始时间 00:00:00 create by zhaoheng 2015-12-14 09:23
	 * @return
	 */
	public static String getMondayOfThisWeek() {
		//获得日历实例
		Calendar calender = Calendar.getInstance();
		//获得今天是周几 默认周日是1，周一是2，...周六是7
		int dayOfWeek = calender.get(Calendar.DAY_OF_WEEK);
		if ((dayOfWeek - 1) == 0){
			 dayOfWeek = 7;
		}else{
			dayOfWeek --;
		}  
		//获得周一的日期
		calender.add(Calendar.DATE, -dayOfWeek + 1);
		//创建日期格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//格式化日期
		String monday = sdf.format(calender.getTime());
		//分割时间字符串
		String [] timeArr = monday.split(" ");
		String time = null;
		if(timeArr.length > 0 && null != timeArr){
			time = timeArr[0] + " " + "00:00:00";
		}
		return time;
	}
	
	/**
	 * 获取本月1号开始时间 00:00:00 
	 * @return
	 */
	public static String getMonthOfFisrtday() {
		//获得日历实例
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.MONTH, 0);
		calender.set(Calendar.DAY_OF_MONTH, 1);//设置1号为本月第一天

		//创建日期格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//格式化日期
		String monday = sdf.format(calender.getTime());
		//分割时间字符串
		String [] timeArr = monday.split(" ");
		String time = null;
		if(timeArr.length > 0 && null != timeArr){
			time = timeArr[0] + " " + "00:00:00";
		}
		return time;
	}
	
	/**
	 * 获取本月有多少天
	 * @return
	 */
	public static int getMonthLastDay() {
		//获得日历实例
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.DATE, 1);////设置1号为本月第一天
		calender.roll(Calendar.DATE, -1);
		
		return calender.get(Calendar.DATE);
	}
	
	
	/**
	 * 获取今天是几号
	 * @return
	 */
	public static int getCurrentDayOfMonth() {
		Calendar calender = Calendar.getInstance();
		return calender.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 指定日期加一天
	 * @return
	 */
	public static String addOneDay(String startDate) {
		Date date = DateUtil.toDate(startDate, DateUtil.YYYY_MM_DD_HH_MM_SS);
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.DATE, 1);
		return DateUtil.toStr(calender.getTime(), DateUtil.YYYY_MM_DD_HH_MM_SS);
	}
	
	/**
	 * 获取某天开始时间 00:00:00
	 * @param day
	 * @return
	 */
	public static String getBeginTimeOfDay(String day) {
		String time = "";
	    time = day + " " + "00:00:00";
		return time;
	}
	
	/**
	 * 获取某天结束时间 23:59:59
	 * @param day
	 * @return
	 */
	public static String getEndTimeOfDay(String day) {
		String time = "";
	    time = day + " " + "23:59:59";
		return time;
	}
}

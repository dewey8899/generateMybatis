/**
 * Project Name:ezplatform
 * File Name:DateUtils.java
 * Package Name:com.enmore.utils
 * Date:2016年8月2日上午10:45:18
 * Copyright (c) 2016, 上海易贸供应链管理有限公司版权所有.
 *
*/

package com.company.project.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * ClassName:DateUtils <br/>
 * Function: 时间格式转换工具类 <br/>
 * Date: 2016年8月2日 上午10:45:18 <br/>
 * 
 * @author markhe
 * @version
 * @since JDK 1.6
 * @see
 */
public class DateUtils {
  /** yyyyMMdd */
  public final static String SHORT_FORMAT = "yyyyMMdd";

  /** yyyyMMddHHmmss */
  public final static String LONG_FORMAT = "yyyyMMddHHmmss";

  /** yyMMddHHmmssSSS */
  public final static String LONG_FORMAT_2 = "yyMMddHHmmssSSS";

  /** yyMMddHHmmSSS */
  public final static String LONG_FORMAT_3 = "yyMMddHHmmSSS";

  /** yyyy-MM-dd */
  public final static String WEB_FORMAT = "yyyy-MM-dd";

  /** HHmmss */
  public final static String TIME_FORMAT = "HHmmss";

  /** yyyyMM */
  public final static String MONTH_FORMAT = "yyyyMM";

  /** yyyy年MM月dd日 */
  public final static String CHINA_FORMAT = "yyyy年MM月dd日";

  /** yyyy-MM-dd HH:mm:ss */
  public final static String LONG_WEB_FORMAT = "yyyy-MM-dd HH:mm:ss";

  /** yyyy-MM-dd HH:mm */
  public final static String LONG_WEB_FORMAT_NO_SEC = "yyyy-MM-dd HH:mm";

  /**
   * 日期对象解析成日期字符串基础方法，可以据此封装出多种便捷的方法直接使用
   * 
   * @param date
   *          待格式化的日期对象
   * @param format
   *          输出的格式
   * @return 格式化的字符串
   */
  public static String format(Date date, String format) {
    if (date == null || StringUtils.isBlank(format)) {
      return "";
    }

    return new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE).format(date);
  }

  /**
   * 格式化当前时间
   * 
   * @param format
   *          输出的格式
   * @return
   */
  public static String formatCurrent(String format) {
    if (StringUtils.isBlank(format)) {
      return "";
    }

    return format(new Date(), format);
  }

  /**
   * 日期字符串解析成日期对象基础方法，可以在此封装出多种便捷的方法直接使用
   * 
   * @param dateStr
   *          日期字符串
   * @param format
   *          输入的格式
   * @return 日期对象
   * @throws ParseException
   */
  public static Date parse(String dateStr, String format) throws ParseException {
    if (StringUtils.isBlank(format)) {
      throw new ParseException("format can not be null.", 0);
    }

    if (dateStr == null || dateStr.length() < format.length()) {
      throw new ParseException("date string's length is too small.", 0);
    }

    return new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE).parse(dateStr);
  }

  /**
   * 日期字符串格式化基础方法，可以在此封装出多种便捷的方法直接使用
   * 
   * @param dateStr
   *          日期字符串
   * @param formatIn
   *          输入的日期字符串的格式
   * @param formatOut
   *          输出日期字符串的格式
   * @return 已经格式化的字符串
   * @throws ParseException
   */
  public static String format(String dateStr, String formatIn, String formatOut) throws ParseException {

    Date date = parse(dateStr, formatIn);
    return format(date, formatOut);
  }

  /**
   * 把日期对象按照<code>yyyyMMdd</code>格式解析成字符串
   * 
   * @param date
   *          待格式化的日期对象
   * @return 格式化的字符串
   */
  public static String formatShort(Date date) {
    return format(date, SHORT_FORMAT);
  }

  /**
   * 把日期字符串按照<code>yyyyMMdd</code>格式，进行格式化
   * 
   * @param dateStr
   *          待格式化的日期字符串
   * @param formatIn
   *          输入的日期字符串的格式
   * @return 格式化的字符串
   */
  public static String formatShort(String dateStr, String formatIn) throws ParseException {
    return format(dateStr, formatIn, SHORT_FORMAT);
  }

  /**
   * 把日期对象按照<code>yyyy-MM-dd</code>格式解析成字符串
   * 
   * @param date
   *          待格式化的日期对象
   * @return 格式化的字符串
   */
  public static String formatWeb(Date date) {
    return format(date, WEB_FORMAT);
  }

  /**
   * 把日期字符串按照<code>yyyy-MM-dd</code>格式，进行格式化
   * 
   * @param dateStr
   *          待格式化的日期字符串
   * @param formatIn
   *          输入的日期字符串的格式
   * @return 格式化的字符串
   * @throws ParseException
   */
  public static String formatWeb(String dateStr, String formatIn) throws ParseException {
    return format(dateStr, formatIn, WEB_FORMAT);
  }

  /**
   * 把日期对象按照<code>yyyyMM</code>格式解析成字符串
   * 
   * @param date
   *          待格式化的日期对象
   * @return 格式化的字符串
   */
  public static String formatMonth(Date date) {

    return format(date, MONTH_FORMAT);
  }

  /**
   * 把日期对象按照<code>HHmmss</code>格式解析成字符串
   * 
   * @param date
   *          待格式化的日期对象
   * @return 格式化的字符串
   */
  public static String formatTime(Date date) {
    return format(date, TIME_FORMAT);
  }

  /**
   * getCurrentMonth:或取当前档期 <br/>
   * 
   * @author Administrator
   * @return
   * @since JDK 1.6
   */
  public static String getCurrentPeriodDay() {
    Calendar newCalendar = Calendar.getInstance();

    int year = newCalendar.get(Calendar.YEAR);
    int month = newCalendar.get(Calendar.MONTH) + 1;
    if (month < 10) {

      return year + "-0" + month;

    } else {

      return year + "-" + month;
    }

  }

  /**
   * getOneWeekAgoDay:获取一周前的日期 <br/>
   * 
   * @author john
   * @return
   * @since JDK 1.6
   */
  public static String getOneWeekAgoDay() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, -7); // 得到一周前的日期
    long date = cal.getTimeInMillis();
    String startDay = sdf.format(new Date(date));
    return startDay;
  }

  /**
   * 功能: 返回date1与date2相差的天数
   * 
   * @param date1
   *          起始日期
   * @param date2
   *          截止日期
   * @return int
   */
  public static int dateDiff(Date date1, Date date2) {
    int i = (int) ((date2.getTime() - date1.getTime()) / 3600 / 24 / 1000);
    return i;
  }

  /**
   * 获取当前时间到晚上零点的秒数
   * 
   * @return
   */
  public static long getNightSecond() {
    Date date = new Date();
    long startTime = date.getTime();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, 1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    long endTime = calendar.getTimeInMillis();
    long second = (endTime - startTime) / 1000;
    return second;
  }

  /**
   * 
   * @param baseDate
   * @param latestDate
   * @return true, 代表没有过期， false，代表过期了
   */
  public static boolean compareDate(Date baseDate, Date latestDate) {
    if (baseDate.before(latestDate))
      return true;
    return false;
  }

  // min
  /**
   * 功能: 返回date1与date2相差的分钟数
   * 
   * @param date1
   * @param date2
   * @return int
   */
  public static int MinDiff(Date date1, Date date2) {
    int i = (int) ((date1.getTime() - date2.getTime()) / 1000 / 60 / 24);
    return i;
  }

  /**
   * 功能: 返回date1与date2相差的天数
   * 
   * @param date1
   * @param date2
   * @return int
   */
  public static int DateDiff(Date date1, Date date2) {
    int i = (int) ((date1.getTime() - date2.getTime()) / 3600 / 24 / 1000);
    return i;
  }
  
  /** 
   * 增加日期中某类型的某数值。如增加日期 
   * @param date 日期 
   * @param amount 数值
   * @return 计算后日期 
   */  
  public static Date addInteger(Date date,int amount) {  
      Date myDate = null;  
      if (date != null) {  
          Calendar calendar = Calendar.getInstance();  
          calendar.setTime(date);  
          calendar.add(Calendar.DATE, amount);  
          myDate = calendar.getTime();  
      }  
      return myDate;  
  }
  /**
   * 获取今天初始时间，格式为yyyy-MM-dd 00:00:00
   * @return
   */
  public static Date todayBeginDate() {
    try {
      return parse(format(new Date(), WEB_FORMAT), WEB_FORMAT);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Date formatDateByWebFormat (Date date) {
    try {
      return parse(format(date, WEB_FORMAT), WEB_FORMAT);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
  /**
   * 获取指定日期月份的第一天
   * @param date
   * @return
   */
  public static Date firstDayOfTheMonthFromTargetDate(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(formatDateByWebFormat(date));
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    return calendar.getTime();
  }
  /**
   * 相较于今天，获取某天的结束时间
   * @param diff 与今天相差的天数，正数表示往后
   */
  public static Date endDateByToday (int diff) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(todayBeginDate());
    cal.add(Calendar.DAY_OF_MONTH, diff + 1);
    cal.add(Calendar.MILLISECOND, -1);
    return cal.getTime();
  }

  /**
   * 相较于今天，获取某天的结束时间
   * @param date 与今天相差的天数，正数表示往后
   */
  public static Date endDateByDate (Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 999);
    return cal.getTime();
  }

  /** 
  * 获取日期的星期。失败返回null。 
  * @param date 日期 
  * @return 星期 
  */  
 public static int getWeek(Date date) {  
     Calendar calendar = Calendar.getInstance();  
     calendar.setTime(date);  
     int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;  
     if(weekNumber <= 0){
         weekNumber = 7;
     }
     return weekNumber;  
 } 
  
/**
 * 
 * 时间格式处理为 yyyy-mm-dd 00:00:00
 *
 * @author liguosheng
 * @param date
 * @return
 * @throws ParseException
 * @since JDK 1.6
 */
 
 public static Date formatValidStartTimeToLong(Date date) throws ParseException {
   Calendar calender = Calendar.getInstance();
   calender.setTime(date);
   calender.set(Calendar.HOUR_OF_DAY, 0);
   calender.set(Calendar.MINUTE, 0);
   calender.set(Calendar.SECOND, 0);
  return calender.getTime();
   
 }
 
 /**
  * 
  * 时间格式处理为 yyyy-mm-dd 23:59:59
  *
  * @author liguosheng
  * @param date
  * @return
  * @throws ParseException
  * @since JDK 1.6
  */
 
 public static Date formatValidEndTimeToLong(Date date) throws ParseException {
   Calendar calender = Calendar.getInstance();
   calender.setTime(date);
   calender.set(Calendar.HOUR_OF_DAY, 23);
   calender.set(Calendar.MINUTE, 59);
   calender.set(Calendar.SECOND, 59);
   
   return calender.getTime();
 }

  /**
   * @author：Dewey.Du
   * 2019年5月28日16:33:02
   * function:
   * 给定时间：返回给定时间的周一和周日，如：2019-05-28，则返回：2019-05-27，和 2019-06-02
   * @param time
   * @return
   */
  public static Date[] convertWeekByDate(Date time) {
    Date[] dates = new Date[2];
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
    Calendar cal = Calendar.getInstance();
    cal.setTime(time);
    int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得传入日期是一个星期的第几天
    if(1 == dayWeek) {
      cal.add(Calendar.DAY_OF_MONTH, -1);
    }
    System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期
    cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
    int day = cal.get(Calendar.DAY_OF_WEEK);//获得传入日期是一个星期的第几天
    cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给传入日期减去星期几与一个星期第一天的差值
    String Monday = sdf.format(cal.getTime());
    dates[0] = cal.getTime();
    System.out.println("所在周星期一的日期："+Monday);
    cal.add(Calendar.DATE, 6);
    String Sunday = sdf.format(cal.getTime());
    System.out.println("所在周星期日的日期："+Sunday);
    dates[1] = cal.getTime();
    return dates;
  }
}

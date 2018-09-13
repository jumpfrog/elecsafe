package com.holley.elecsafe.save.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import com.holley.platform.common.constants.CircleTypeEnum;
import com.holley.platform.common.constants.Globals;

public class DateUtil extends DateUtils {

    /* 带分秒的时间格式化 */
    public static String TIME_LONG      = "yyyy-MM-dd HH:mm:ss";

    /* 不带秒的时间格式 */
    public static String TIME_NO_SEC    = "yyyy-MM-dd HH:mm";
    public static String TIME_NO_SEC_CN = "yyyy年MM月dd日HH时mm分";

    /* 只有日期的时间格式化 */
    public static String TIME_SHORT     = "yyyy-MM-dd";
    public static String TIME_SHORT_CN  = "yyyy年MM月dd日";

    /* 不带分秒的时间格式 */
    public static String DATE_AND_MONTH = "yyyy-MM-dd HH";
    public static String TIME_NO_MIN_CN = "yyyy年MM月dd日HH时";

    /* 只有日期的年月格式化 */
    public static String YEAR_MONTH     = "yyMM";

    /* 只有日期的年格式化 */
    public static String YEAR           = "yy";

    public static String YEAR_LONG      = "yyyy";

    /* 只有日期的小时格式化 */
    public static String HOUR           = "HH";

    /* 只格式化小时分钟 */
    public static String HOUR_MIN       = "HH:mm";

    /* 只格式化日期的天 */
    public static String DAY            = "dd";

    public static String MONTH          = "MM";

    public static String YEARMONTH      = "yyyyMM";

    public static String MONTHTIME      = "yyyy-MM";

    public static String MONTHTIME_CN   = "yyyy年MM月";

    public static String MONTHDAY       = "MM-dd";

    /**
     * 得到每月的最大日期数字
     * 
     * @param date
     * @return
     */
    public static int getMaxMonthDay(Date date) {
        Calendar cr = Calendar.getInstance();
        cr.setTime(date);
        cr.set(Calendar.DAY_OF_MONTH, 1);
        cr.roll(Calendar.DAY_OF_MONTH, -1);
        return cr.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 日期按照HH:mm格式转化字符串
     * 
     * @param date
     * @return
     */
    public static String DateToHHMMStr(Date date) {
        return DateToStr(date, HOUR_MIN);
    }

    /**
     * 判断方案类型，转换年，月的表名
     * 
     * @param date
     * @param schemeId
     * @return
     */
    public static String getDateStrBySchemeId(Date date, Short schemeId) {
        if (CircleTypeEnum.MONTH.getShortValue().equals(schemeId)) {
            return DateUtil.DateToStr(date, DateUtil.YEAR);
        } else {
            return DateUtil.DateToStr(date, DateUtil.YEAR_MONTH);
        }
    }

    /**
     * 日期按照HH格式转化字符串
     * 
     * @param date
     * @return
     */
    public static String DateToHHStr(Date date) {
        return DateToStr(date, HOUR);
    }

    /**
     * 日期按照dd格式转化字符串
     * 
     * @param date
     * @return
     */
    public static String DateToDayStr(Date date) {
        return DateToStr(date, DAY);
    }

    /**
     * 日期按照yy格式转换字符串
     * 
     * @param date
     * @return
     */
    public static String DateToYYStr(Date date) {
        return DateToStr(date, YEAR);
    }

    /**
     * 日期按照yyyy格式转换字符串
     * 
     * @param date
     * @return
     */
    public static String DateToYYYYStr(Date date) {
        return DateToStr(date, YEAR_LONG);
    }

    /**
     * 日期按照yyMM格式转换字符串
     * 
     * @param date
     * @return
     */
    public static String DateToYYMMStr(Date date) {
        return DateToStr(date, YEAR_MONTH);
    }

    public static String DateToYYYYMMStr(Date date) {
        return DateToStr(date, MONTHTIME);
    }

    /**
     * 日期按照yyyy-MM-dd HH:mm:ss格式格式转换字符串
     * 
     * @param date
     * @param Format
     * @return
     */
    public static String DateToLongStr(Date date) {
        return DateToStr(date, TIME_LONG);
    }

    /**
     * 日期按照yyyy-MM-dd格式格式转换字符串
     * 
     * @param date
     * @return
     */
    public static String DateToShortStr(Date date) {
        return DateToStr(date, TIME_SHORT);
    }

    /**
     * 按照指定格式格式转换字符串
     * 
     * @param date
     * @param Format
     * @return
     */
    public static String DateToStr(Date date, String Format) {
        if (date == null) return "";
        SimpleDateFormat formater = new java.text.SimpleDateFormat(Format);
        return formater.format(date);
    }

    /**
     * 日期按照yyyy-MM-dd HH:mm格式转化字符串
     * 
     * @param date
     * @return
     */
    public static String DateToNosecStr(Date date) {
        return DateToStr(date, TIME_NO_SEC);
    }

    /**
     * 日期按照yyyy年MM月dd日HH时mm分格式转化字符串
     * 
     * @param date
     * @return
     */
    public static String DateToNosecStrCN(Date date) {
        return DateToStr(date, TIME_NO_SEC_CN);
    }

    /**
     * 日期按照yyyy年MM月dd日HH时格式转化字符串
     * 
     * @param date
     * @return
     */
    public static String DateToNominStrCN(Date date) {
        return DateToStr(date, TIME_NO_MIN_CN);
    }

    /**
     * 指定的格式yyyy-MM-dd HH:mm:ss的字符串按照转换为日期
     * 
     * @param dateStr
     * @param Format
     * @return
     */
    public static Date LongStrToDate(String dateStr) {
        if (dateStr.length() > 16) {
            return StrToDate(dateStr, TIME_LONG);
        } else if (dateStr.length() > 13) {
            return StrToDate(dateStr, TIME_NO_SEC);
        } else if (dateStr.length() > 9) {
            return StrToDate(dateStr, TIME_SHORT);
        } else if (dateStr.length() > 6) {
            return StrToDate(dateStr, MONTHTIME);
        } else if (dateStr.length() > 3) {
            return StrToDate(dateStr, "yyyy");
        } else {
            return null;
        }
    }

    /**
     * 指定的格式yyyy-MM-dd的字符串按照转换为日期
     * 
     * @param dateStr
     * @param Format
     * @return
     */
    public static Date ShortStrToDate(String dateStr) {
        return StrToDate(dateStr, TIME_SHORT);
    }

    /**
     * 指定的格式yyyy-MM-dd HH:mm的字符串按照转换为日期
     * 
     * @param dateStr
     * @return
     */
    public static Date NosecStrToDate(String dateStr) {
        return StrToDate(dateStr, TIME_NO_SEC);
    }

    /**
     * 指定的格式的字符串按照转换为日期
     * 
     * @param dateStr
     * @param Format
     * @return
     */
    public static Date StrToDate(String dateStr, String Format) {
        SimpleDateFormat formater = new java.text.SimpleDateFormat(Format);
        try {
            return formater.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 得到当前时间的开始的第一年
     * 
     * @param date
     * @return
     */
    public static Date getFirstYear(Date date) {
        if (date != null) {
            Calendar cr = Calendar.getInstance();
            cr.setTime(date);
            cr.set(Calendar.MONDAY, 0);
            cr.set(Calendar.DAY_OF_MONTH, 1);
            cr.set(Calendar.HOUR_OF_DAY, 0);
            cr.set(Calendar.MINUTE, 0);
            cr.set(Calendar.SECOND, 0);
            return cr.getTime();
        }

        return date;
    }

    /**
     * 计算两个任意时间中间的间隔天数
     * 
     * @param startday
     * @param endday
     * @return
     */
    public static int getIntervalDays(Date startday, Date endday) {
        if (startday.after(endday)) {
            Date cal = startday;
            startday = endday;
            endday = cal;
        }
        long sl = startday.getTime();
        long el = endday.getTime();
        long ei = el - sl;
        return (int) (ei / (1000 * 60 * 60 * 24));
    }

    public static int getIntervalHours(Date startday, Date endday) {
        if (startday.after(endday)) {
            Date cal = startday;
            startday = endday;
            endday = cal;
        }
        long sl = startday.getTime();
        long el = endday.getTime();
        long ei = el - sl;
        return (int) (ei / (1000 * 60 * 60));
    }

    public static int getIntervalMinutes(Date startday, Date endday) {
        if (startday.after(endday)) {
            Date cal = startday;
            startday = endday;
            endday = cal;
        }
        long sl = startday.getTime();
        long el = endday.getTime();
        long ei = el - sl;
        return (int) (ei / (1000 * 60));
    }

    /**
     * 得到date的 小时数字
     * 
     * @param date
     * @return
     */
    public static int getHourValue(Date date) {
        Calendar cr = Calendar.getInstance();
        cr.setTime(date);
        return cr.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 得到date的 秒数字
     * 
     * @param date
     * @return
     */
    public static int getSecondValue(Date date) {
        Calendar cr = Calendar.getInstance();
        cr.setTime(date);
        return cr.get(Calendar.SECOND);
    }

    public static int getMinuteValue(Date date) {
        Calendar cr = Calendar.getInstance();
        cr.setTime(date);
        return cr.get(Calendar.MINUTE);
    }

    /**
     * 得到date的日数字
     * 
     * @param date
     * @return
     */
    public static int getDayValue(Date date) {
        Calendar cr = Calendar.getInstance();
        cr.setTime(date);
        return cr.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 得到date的月数字
     * 
     * @param date
     * @return
     */
    public static int getMonthValue(Date date) {
        Calendar cr = Calendar.getInstance();
        cr.setTime(date);
        return cr.get(Calendar.MONTH) + 1;
    }

    /**
     * 得到date的年数字
     * 
     * @param date
     * @return
     */
    public static int getYearValue(Date date) {
        Calendar cr = Calendar.getInstance();
        cr.setTime(date);
        return cr.get(Calendar.YEAR);
    }

    /**
     * 得到每个月的第一天
     * 
     * @param date 传入的需要比较的时间
     * @return
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        a.set(Calendar.DAY_OF_MONTH, 1);// 把日期设置为当月第一天
        a.set(Calendar.HOUR, 0);
        a.set(Calendar.MINUTE, 0);
        a.set(Calendar.SECOND, 0);
        return a.getTime();
    }

    /**
     * 得到每个月的最后一天
     * 
     * @param date 传入的需要比较的时间
     * @return
     */
    public static Date getMonthLastDay(Date date) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        a.set(Calendar.DATE, 1); // 把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        a.set(Calendar.HOUR, 0);
        a.set(Calendar.MINUTE, 0);
        a.set(Calendar.SECOND, 0);
        return a.getTime();
    }

    public static int getMondayPlus(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    public static Boolean isWorkDay(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1 || dayOfWeek == 7) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 得到某个时间的星期1的日期
     * 
     * @param date
     * @return
     */
    public static Date getMonday(Date date) {
        int mondayPlus = DateUtil.getMondayPlus(date);
        Date value = DateUtils.addDays(date, mondayPlus);
        return value;
    }

    /**
     * 得到某个时间的星期7的日期
     * 
     * @param date
     * @return
     */
    public static Date getSunday(Date date) {
        Date value = DateUtil.getMonday(date);
        return DateUtils.addDays(value, 6);
    }

    /**
     * 得到季度开始时间
     * 
     * @param date
     * @return
     */
    public static Date getQuarter(Date date) {
        int month = DateUtil.getMonthValue(date);
        Date firstYear = DateUtil.getFirstYear(date);
        if (month < 4) {
            return firstYear;
        } else if (month < 7) {
            return DateUtils.addMonths(firstYear, 3);
        } else if (month < 10) {
            return DateUtils.addMonths(firstYear, 6);
        } else {
            return DateUtils.addMonths(firstYear, 9);
        }
    }

    /**
     * 由第几季度推算出是哪一月
     */
    public static String quarterTomm(String date) {
        String datetime = null;
        if (date.equals("0")) {
            datetime = "-01-01 00:00:00";
        } else if (date.equals("1")) {
            datetime = "-04-01 00:00:00";
        } else if (date.equals("2")) {
            datetime = "-07-01 00:00:00";
        } else if (date.equals("3")) {
            datetime = "-10-01 00:00:00";
        }
        return datetime;
    }

    public static Date getLastWorkDay(Date date) {
        Date value = DateUtil.addDays(date, -1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(value);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1 || dayOfWeek == 7) {
            return getLastWorkDay(value);
        }
        return value;
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month + 1);
        calendar.set(Calendar.DATE, 0);
        return calendar.getTime();
    }

    public static Date getLastMonthDayHourOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 11);
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month + 1);
        calendar.set(Calendar.DATE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getFirstHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // calendar.set(Calendar.DATE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getFirstMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // calendar.set(Calendar.DATE, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getPreHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // calendar.set(Calendar.DATE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, getHourValue(date) - 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getLastHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // calendar.set(Calendar.DATE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getSomeHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // calendar.set(Calendar.DATE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getSomeMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getSomeMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, month + getMonthValue(date) - 1);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, getHourValue(date));
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getLastDayOfMonth(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month + 1);
        calendar.set(Calendar.DATE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();

    }

    public static Date getSomeDay(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, days + getDayValue(date));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getLastMonthOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 11);
        return calendar.getTime();
    }

    public static Date getFirstMonthOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    public static Date getPreMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 计算日或者月时段,计算2个日期间的时段，用formatStr格式化,把结果放入list 不改变外面传入的formatStr格式化
     * 
     * @param beginDate
     * @param endDate
     * @param formatStr
     * @param calenderConstants Calendar.DAY_OF_MONTH等对应的整形
     * @return
     */
    public static List<String> getDateBetweenEx(Date beginDate, Date endDate, String formatStr, int calenderConstants) {
        DateFormat f = new SimpleDateFormat(formatStr);

        String beginDateStr = DateUtil.DateToStr(beginDate, formatStr);
        String endDateStr = DateUtil.DateToStr(endDate, formatStr);

        Date date1 = DateUtil.StrToDate(beginDateStr, formatStr);
        Date date2 = DateUtil.StrToDate(endDateStr, formatStr);

        List<String> list = new ArrayList<String>();
        if (date1 == null) {
            return list;
        }

        list.add(f.format(date1));

        if (endDate == null || beginDate.after(endDate)) {
            return list;
        }

        while (date1.compareTo(date2) < 0) {
            if (calenderConstants == Calendar.DAY_OF_MONTH) {
                date1 = DateUtils.addDays(date1, 1);
            } else if (calenderConstants == Calendar.MONTH) {
                date1 = DateUtils.addMonths(date1, 1);
            } else if (calenderConstants == Calendar.HOUR_OF_DAY) {
                date1 = DateUtils.addHours(date1, 1);
            } else {
                return list;
            }
            String date1Str = f.format(date1);
            if (!list.contains(date1Str)) { // 避免重复数据
                list.add(date1Str);
            }
        }
        return list;
    }

    /**
     * 计算日或者月时段,计算2个日期间的时段，用formatStr格式化,把结果放入list
     * 
     * @param beginDate
     * @param endDate
     * @param formatStr
     * @param calenderConstants Calendar.DAY_OF_MONTH等对应的整形
     * @return
     */
    public static List<String> getDateBetween(Date beginDate, Date endDate, String formatStr, int calenderConstants) {
        DateFormat f = new SimpleDateFormat(formatStr);

        if (calenderConstants == Calendar.DAY_OF_MONTH) {
            formatStr = DateUtil.TIME_SHORT;
        } else if (calenderConstants == Calendar.MONTH) {
            formatStr = DateUtil.YEARMONTH;
        } else if (calenderConstants == Calendar.HOUR_OF_DAY) {
            formatStr = DateUtil.DATE_AND_MONTH;
        } else if (calenderConstants == Calendar.MINUTE) {
            formatStr = DateUtil.TIME_NO_SEC;
        } else if (calenderConstants == Calendar.YEAR) {
            formatStr = DateUtil.YEAR_LONG;
        }
        String beginDateStr = DateUtil.DateToStr(beginDate, formatStr);
        String endDateStr = DateUtil.DateToStr(endDate, formatStr);

        Date date1 = DateUtil.StrToDate(beginDateStr, formatStr);
        Date date2 = DateUtil.StrToDate(endDateStr, formatStr);

        List<String> list = new ArrayList<String>();
        if (date1 == null) {
            return list;
        }

        list.add(f.format(date1));

        if (endDate == null || beginDate.after(endDate)) {
            return list;
        }

        while (date1.compareTo(date2) < 0) {
            if (calenderConstants == Calendar.DAY_OF_MONTH) {
                date1 = DateUtils.addDays(date1, 1);
            } else if (calenderConstants == Calendar.MONTH) {
                date1 = DateUtils.addMonths(date1, 1);
            } else if (calenderConstants == Calendar.HOUR_OF_DAY) {
                date1 = DateUtils.addHours(date1, 1);
            } else if (calenderConstants == Calendar.MINUTE) {
                date1 = DateUtils.addMinutes(date1, 15);
            } else if (calenderConstants == Calendar.YEAR) {
                date1 = DateUtils.addYears(date1, 1);
            } else {
                return list;
            }
            String date1Str = f.format(date1);
            if (!list.contains(date1Str)) { // 避免重复数据
                list.add(date1Str);
            }
        }
        return list;
    }

    /**
     * 计算日或者月时段,计算2个日期间的时段，用formatStr格式化,把结果放入list,降序
     * 
     * @param beginDate
     * @param endDate
     * @param formatStr
     * @param calenderConstants Calendar.DAY_OF_MONTH等对应的整形
     * @return
     */
    public static List<String> getDateBetweenByDesc(Date beginDate, Date endDate, String formatStr, int calenderConstants) {
        DateFormat f = new SimpleDateFormat(formatStr);

        if (calenderConstants == Calendar.DAY_OF_MONTH) {
            formatStr = DateUtil.TIME_SHORT;
        } else if (calenderConstants == Calendar.MONTH) {
            formatStr = DateUtil.YEARMONTH;
        } else if (calenderConstants == Calendar.HOUR_OF_DAY) {
            formatStr = DateUtil.DATE_AND_MONTH;
        } else if (calenderConstants == Calendar.MINUTE) {
            formatStr = DateUtil.TIME_NO_SEC;
        } else if (calenderConstants == Calendar.YEAR) {
            formatStr = DateUtil.YEAR_LONG;
        }
        String beginDateStr = DateUtil.DateToStr(beginDate, formatStr);
        String endDateStr = DateUtil.DateToStr(endDate, formatStr);

        Date date1 = DateUtil.StrToDate(beginDateStr, formatStr);
        Date date2 = DateUtil.StrToDate(endDateStr, formatStr);

        List<String> list = new ArrayList<String>();
        if (date2 == null) {
            return list;
        }

        list.add(f.format(date2));

        if (beginDate == null || beginDate.after(endDate)) {
            return list;
        }

        while (date2.compareTo(date1) > 0) {
            if (calenderConstants == Calendar.DAY_OF_MONTH) {
                date2 = DateUtils.addDays(date2, -1);
            } else if (calenderConstants == Calendar.MONTH) {
                date2 = DateUtils.addMonths(date2, -1);
            } else if (calenderConstants == Calendar.HOUR_OF_DAY) {
                date2 = DateUtils.addHours(date2, -1);
            } else if (calenderConstants == Calendar.MINUTE) {
                date2 = DateUtils.addMinutes(date2, -15);
            } else if (calenderConstants == Calendar.YEAR) {
                date2 = DateUtils.addYears(date2, -1);
            } else {
                return list;
            }
            String date2Str = f.format(date2);
            if (!list.contains(date2Str)) { // 避免重复数据
                list.add(date2Str);
            }
        }
        return list;
    }

    /**
     * 计算日或者月时段,计算2个日期间的时段，用formatStr格式化,把结果放入list
     * 
     * @param beginDate
     * @param endDate
     * @param formatStr
     * @param calenderConstants Calendar.DAY_OF_MONTH等对应的整形
     * @interval 间隔
     * @return
     */
    public static List<String> getDateStringBetween(Date beginDate, Date endDate, String formatStr, int calenderConstants, int interval) {
        DateFormat f = new SimpleDateFormat(formatStr);

        if (calenderConstants == Calendar.DAY_OF_MONTH) {
            formatStr = DateUtil.TIME_SHORT;
        } else if (calenderConstants == Calendar.MONTH) {
            formatStr = DateUtil.YEARMONTH;
        } else if (calenderConstants == Calendar.HOUR_OF_DAY) {
            formatStr = DateUtil.DATE_AND_MONTH;
        } else if (calenderConstants == Calendar.MINUTE) {
            formatStr = DateUtil.TIME_NO_SEC;
        } else if (calenderConstants == Calendar.YEAR) {
            formatStr = DateUtil.YEAR_LONG;
        }
        String beginDateStr = DateUtil.DateToStr(beginDate, formatStr);
        String endDateStr = DateUtil.DateToStr(endDate, formatStr);

        Date date1 = DateUtil.StrToDate(beginDateStr, formatStr);
        Date date2 = DateUtil.StrToDate(endDateStr, formatStr);

        List<String> list = new ArrayList<String>();
        if (date1 == null) {
            return list;
        }

        list.add(f.format(date1));

        if (endDate == null || beginDate.after(endDate)) {
            return list;
        }

        while (date1.compareTo(date2) < 0) {
            if (calenderConstants == Calendar.DAY_OF_MONTH) {
                date1 = DateUtils.addDays(date1, interval);
            } else if (calenderConstants == Calendar.MONTH) {
                date1 = DateUtils.addMonths(date1, interval);
            } else if (calenderConstants == Calendar.HOUR_OF_DAY) {
                date1 = DateUtils.addHours(date1, interval);
            } else if (calenderConstants == Calendar.MINUTE) {
                date1 = DateUtils.addMinutes(date1, interval);
            } else if (calenderConstants == Calendar.YEAR) {
                date1 = DateUtils.addYears(date1, interval);
            } else {
                return list;
            }
            String date1Str = f.format(date1);
            if (!list.contains(date1Str)) { // 避免重复数据
                list.add(date1Str);
            }
        }
        return list;
    }

    /**
     * 计算日或者月时段,计算2个日期间的时段，用formatStr格式化,把结果放入list
     * 
     * @param beginDate
     * @param endDate
     * @param formatStr
     * @param calenderConstants Calendar.DAY_OF_MONTH等对应的整形
     * @interval 间隔
     * @return
     */
    public static List<Date> getDateBetween(Date beginDate, Date endDate, String formatStr, int calenderConstants, int interval) {
        DateFormat f = new SimpleDateFormat(formatStr);

        if (calenderConstants == Calendar.DAY_OF_MONTH) {
            formatStr = DateUtil.TIME_SHORT;
        } else if (calenderConstants == Calendar.MONTH) {
            formatStr = DateUtil.YEARMONTH;
        } else if (calenderConstants == Calendar.HOUR_OF_DAY) {
            formatStr = DateUtil.DATE_AND_MONTH;
        } else if (calenderConstants == Calendar.MINUTE) {
            formatStr = DateUtil.TIME_NO_SEC;
        } else if (calenderConstants == Calendar.YEAR) {
            formatStr = DateUtil.YEAR_LONG;
        }
        String beginDateStr = DateUtil.DateToStr(beginDate, formatStr);
        String endDateStr = DateUtil.DateToStr(endDate, formatStr);

        Date date1 = DateUtil.StrToDate(beginDateStr, formatStr);
        Date date2 = DateUtil.StrToDate(endDateStr, formatStr);

        List<Date> list = new ArrayList<Date>();
        if (date1 == null || endDate == null || beginDate.after(endDate)) {
            return list;
        }

        while (date1.compareTo(date2) < 0) {
            list.add(date1);
            if (calenderConstants == Calendar.DAY_OF_MONTH) {
                date1 = DateUtils.addDays(date1, interval);
            } else if (calenderConstants == Calendar.MONTH) {
                date1 = DateUtils.addMonths(date1, interval);
            } else if (calenderConstants == Calendar.HOUR_OF_DAY) {
                date1 = DateUtils.addHours(date1, interval);
            } else if (calenderConstants == Calendar.MINUTE) {
                date1 = DateUtils.addMinutes(date1, interval);
            } else if (calenderConstants == Calendar.YEAR) {
                date1 = DateUtils.addYears(date1, interval);
            } else {
                return list;
            }
        }
        return list;
    }

    /**
     * 月末20点，日20点
     * 
     * @param beginDate
     * @param endDate
     * @param formatStr
     * @param calenderConstants
     * @return
     */
    public static List<String> getDateBetweenLast20Hour(Date beginDate, Date endDate, String formatStr, int calenderConstants) {
        DateFormat f = new SimpleDateFormat(formatStr);

        formatStr = DateUtil.TIME_LONG;
        String beginDateStr = DateUtil.DateToStr(beginDate, formatStr);
        String endDateStr = DateUtil.DateToStr(endDate, formatStr);

        Date date1 = DateUtil.StrToDate(beginDateStr, formatStr);
        Date date2 = DateUtil.StrToDate(endDateStr, formatStr);

        List<String> list = new ArrayList<String>();
        if (date1 == null) {
            return list;
        }
        if (calenderConstants == Calendar.DAY_OF_MONTH) {
            list.add(f.format(getLastDayOfMonth(date1, 20)));
        } else if (calenderConstants == Calendar.HOUR_OF_DAY) {
            list.add(f.format(getSomeHour(date1, 20)));
        }

        if (endDate == null || beginDate.after(endDate)) {
            return list;
        }

        while (date1.compareTo(date2) < 0) {
            if (calenderConstants == Calendar.DAY_OF_MONTH) {
                date1 = getLastDayOfMonth(DateUtils.addMonths(date1, 1), 20);// 月末20
            } else if (calenderConstants == Calendar.HOUR_OF_DAY) {
                date1 = getSomeHour(DateUtils.addDays(date1, 1), 20);// 日20点数据
            } else {
                return list;
            }
            String date1Str = f.format(date1);
            if (!list.contains(date1Str)) { // 避免重复数据
                list.add(date1Str);
            }
        }
        return list;
    }

    /**
     * 计算月份时段,计算2个日期间的时段，用formatStr格式化,把结果放入list
     * 
     * @param beginDate
     * @param endDate
     * @param formatStr
     * @return
     */
    public static List<String> getMonthBetween(Date beginDate, Date endDate, String formatStr) {
        return getDateBetween(beginDate, endDate, formatStr, Calendar.MONTH);
    }

    /**
     * 计算月份时段,计算2个日期间的时段，用formatStr格式化,把结果放入list,order by desc
     * 
     * @param beginDate
     * @param endDate
     * @param formatStr
     * @return
     */
    public static List<String> getMonthBetweenByDesc(Date beginDate, Date endDate, String formatStr) {
        return getDateBetweenByDesc(beginDate, endDate, formatStr, Calendar.MONTH);
    }

    /**
     * 计算年份时段,计算2个日期间的时段，用formatStr格式化,把结果放入list
     * 
     * @param beginDate
     * @param endDate
     * @param formatStr
     * @return
     */
    public static List<String> getYearBetween(Date beginDate, Date endDate, String formatStr) {
        DateFormat f = new SimpleDateFormat(formatStr);

        String beginDateStr = DateUtil.DateToStr(beginDate, formatStr);
        String endDateStr = DateUtil.DateToStr(endDate, formatStr);

        Date date1 = DateUtil.StrToDate(beginDateStr, formatStr);
        Date date2 = DateUtil.StrToDate(endDateStr, formatStr);

        List<String> list = new ArrayList<String>();
        list.add(beginDateStr);

        if (endDate == null || beginDate.after(endDate)) {
            return list;
        }

        while (date1.compareTo(date2) < 0) {
            date1 = DateUtils.addYears(date1, 1);
            list.add(f.format(date1));
        }
        return list;
    }

    /**
     * 日期转换成字符串
     * 
     * @param aDate 日期
     * @param dateSpan 时间分割0 为没分割
     * @param dateTimeSpan 日期和时间的分割字符 0 没分割符
     * @param timeSpan 时间的分割字符
     * @return Description of the Returned Value
     */
    public static String DateTimeToStr(Date aDate, char dateSpan, char dateTimeSpan, char timeSpan) {
        if (aDate == null) {
            return null;
        }
        StringBuffer dataBuf = new StringBuffer(20);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(aDate);
        dataBuf.append(calendar.get(Calendar.YEAR));
        if (dateSpan != 0) {
            dataBuf.append(dateSpan);
        }
        int month = calendar.get(Calendar.MONTH) + 1;
        appendInt(dataBuf, month);
        if (dateSpan != 0) {
            dataBuf.append(dateSpan);
        }
        int date = calendar.get(Calendar.DATE);
        appendInt(dataBuf, date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        if (hour + min + second > 0) {
            if (dateTimeSpan != 0) dataBuf.append(dateTimeSpan);
            appendInt(dataBuf, hour);
            if (timeSpan != 0) {
                dataBuf.append(timeSpan);
            }
            appendInt(dataBuf, min);
            if (timeSpan != 0) {
                dataBuf.append(timeSpan);
            }
            appendInt(dataBuf, second);
        }
        return dataBuf.toString();
    }

    public static Date getDate(int year, int month, int day, int hour, int min, int sec) {
        Calendar cl = Calendar.getInstance();
        cl.set(year, month, day, hour, min, sec);
        return cl.getTime();
    }

    private static void appendInt(StringBuffer buf, int nDate) {
        if (nDate < 10) {
            buf.append("0");
        }
        buf.append(nDate);
    }

    /**
     * 计算两个日期间的分钟数，截止日期必须大于起始日期
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static long calcMinuteBetween2Dates(Date startDate, Date endDate) {
        if (startDate.compareTo(endDate) > 0) {
            return 0;
        }
        long start = startDate.getTime();
        long end = endDate.getTime();
        return (end - start) / (1000 * 60);
    }

    /***
     * 根据周期类型和参考时间来调整时间，不改动原时间
     * 
     * @param srcDate
     * @param refDate
     * @param circletypeid
     * @return
     */
    public static Date adjustDate(Date srcDate, Date refDate, Short circletypeid) {
        Calendar src = Calendar.getInstance();
        src.setTime(srcDate);
        src.set(Calendar.SECOND, 0);
        src.set(Calendar.MILLISECOND, 0);
        Calendar ref = Calendar.getInstance();
        ref.setTime(refDate);
        if (circletypeid.intValue() == CircleTypeEnum.HOUR.getValue()) {
            src.set(Calendar.MINUTE, ref.get(Calendar.MINUTE));
        } else if (circletypeid.intValue() == CircleTypeEnum.DAY.getValue()) {
            src.set(Calendar.MINUTE, ref.get(Calendar.MINUTE));
            src.set(Calendar.HOUR_OF_DAY, ref.get(Calendar.HOUR_OF_DAY));
        } else if (circletypeid.intValue() == CircleTypeEnum.MONTH.getValue()) {
            if (ref.getActualMaximum(Calendar.DAY_OF_MONTH) == ref.get(Calendar.DAY_OF_MONTH)) {// 月末
                // src.add(Calendar.MONTH, -1);
                src.set(Calendar.DAY_OF_MONTH, src.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else {
                // if (src.get(Calendar.DAY_OF_MONTH) > 15) {
                // src.add(Calendar.MONTH, -1);
                // }
                src.set(Calendar.DAY_OF_MONTH, ref.get(Calendar.DAY_OF_MONTH));
            }
            src.set(Calendar.MINUTE, ref.get(Calendar.MINUTE));
            src.set(Calendar.HOUR_OF_DAY, ref.get(Calendar.HOUR_OF_DAY));

        }
        return src.getTime();
    }

    /***
     * 根据周期类型和参考时间来调整时间，不改动原时间 根据参考时间做前时标后时标调整，日>15为后时标，<15为前时标，小时<12为前时标>12为后时标
     * 
     * @param srcDate
     * @param refDate
     * @param circletypeid
     * @return
     */
    public static Date adjustDateEx(Date srcDate, Date refDate, Short circletypeid) {
        Calendar src = Calendar.getInstance();
        src.setTime(srcDate);
        src.set(Calendar.SECOND, 0);
        src.set(Calendar.MILLISECOND, 0);
        Calendar ref = Calendar.getInstance();
        ref.setTime(refDate);
        if (circletypeid.intValue() == CircleTypeEnum.HOUR.getValue()) {
            src.set(Calendar.MINUTE, ref.get(Calendar.MINUTE));
        } else if (circletypeid.intValue() == CircleTypeEnum.DAY.getValue()) {
            src.set(Calendar.MINUTE, ref.get(Calendar.MINUTE));
            if (src.get(Calendar.HOUR_OF_DAY) > 12) {
                src.add(Calendar.DAY_OF_MONTH, -1);
            }
            src.set(Calendar.HOUR_OF_DAY, ref.get(Calendar.HOUR_OF_DAY));
        } else if (circletypeid.intValue() == CircleTypeEnum.MONTH.getValue()) {
            if (ref.getActualMaximum(Calendar.DAY_OF_MONTH) == ref.get(Calendar.DAY_OF_MONTH)) {// 月末
                src.add(Calendar.MONTH, -1);
                src.set(Calendar.DAY_OF_MONTH, src.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else {
                if (src.get(Calendar.DAY_OF_MONTH) > 15) {
                    src.add(Calendar.MONTH, -1);
                }
                src.set(Calendar.DAY_OF_MONTH, ref.get(Calendar.DAY_OF_MONTH));
            }
            src.set(Calendar.MINUTE, ref.get(Calendar.MINUTE));
            src.set(Calendar.HOUR_OF_DAY, ref.get(Calendar.HOUR_OF_DAY));

        }
        return src.getTime();
    }

    /**
     * 通过周期类型，来获得指定时间往后推的结束时间 <br/>
     * 规则： 如果周期是日，往后推offset日, 月就往后推offset个月,其他依次
     * 
     * @param startTime 指定参考时间
     * @param circleTypeId 周期类型
     * @param offset 偏移量
     * @return
     */
    public static Date getCircleEndTime(Date startTime, int circleTypeId, int offset) {
        CircleTypeEnum circleTypeEnum = CircleTypeEnum.getEnmuByValue(circleTypeId);
        if (circleTypeEnum == null || startTime == null) {
            return null;
        }

        if (circleTypeEnum == CircleTypeEnum.HOUR) {
            return DateUtils.addHours(startTime, offset);
        } else if (circleTypeEnum == CircleTypeEnum.DAY) {
            return DateUtils.addDays(startTime, offset);
        } else if (circleTypeEnum == CircleTypeEnum.MONTH) {
            return DateUtils.addMonths(startTime, offset);
        } else if (circleTypeEnum == CircleTypeEnum.WEEK) {
            return DateUtils.addWeeks(startTime, offset);
        }

        return startTime;
    }

    /**
     * 根据周期类型转换date为指定的格式： yymm 或者 yy,作为分表的表名后缀
     * 
     * @param date
     * @param circleType
     * @return
     */
    public static String getTableNameByCircleType(Date date, int circleType) {
        if (circleType == CircleTypeEnum.DAY.getValue()) {
            return DateUtil.DateToYYMMStr(date);
        } else if (circleType == CircleTypeEnum.MONTH.getValue()) {
            return DateUtil.DateToYYStr(date);
        }
        return DateUtil.DateToYYMMStr(date);
    }

    /**
     * 没15分钟
     * 
     * @param date
     * @return
     */
    public static Date getCyc15Minute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, getHourValue(new Date()));
        int minute = getMinuteValue(new Date()) / 15 * 15;
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static List<String> getLossDateBetween(Date beginDate, Date endDate, String formatStr, int calenderConstants, int collectcyc) {
        DateFormat f = new SimpleDateFormat(formatStr);

        // if (calenderConstants == Calendar.DAY_OF_MONTH) {
        // formatStr = DateUtil.TIME_SHORT;
        // } else if (calenderConstants == Calendar.MONTH) {
        // formatStr = DateUtil.YEARMONTH;
        // } else if (calenderConstants == Calendar.HOUR_OF_DAY) {
        // formatStr = DateUtil.DATE_AND_MONTH;
        // }
        String beginDateStr = DateUtil.DateToStr(beginDate, formatStr);
        String endDateStr = DateUtil.DateToStr(endDate, formatStr);

        Date date1 = DateUtil.StrToDate(beginDateStr, formatStr);
        Date date2 = DateUtil.StrToDate(endDateStr, formatStr);

        List<String> list = new ArrayList<String>();
        if (date1 == null) {
            return list;
        }

        list.add(f.format(date1));

        if (endDate == null || beginDate.after(endDate)) {
            return list;
        }

        while (date1.compareTo(date2) < 0) {
            if (calenderConstants == Calendar.DAY_OF_MONTH) {
                date1 = DateUtils.addDays(date1, collectcyc);
            } else if (calenderConstants == Calendar.MONTH) {
                date1 = DateUtils.addMonths(date1, collectcyc);
            } else if (calenderConstants == Calendar.HOUR_OF_DAY) {
                date1 = DateUtils.addHours(date1, collectcyc);
            } else if (calenderConstants == Calendar.MINUTE) {
                date1 = DateUtils.addMinutes(date1, collectcyc);
            } else {
                return list;
            }
            String date1Str = f.format(date1);
            if (!list.contains(date1Str)) { // 避免重复数据
                list.add(date1Str);
            }
        }
        return list;
    }

    public static Date getCurrentDate() {
        return new Date(112, 8, 12);
    }

    public static int getCircleCount(int circleTypeId, Date start, Date end) {
        CircleTypeEnum circleTypeEnum = CircleTypeEnum.getEnmuByValue(circleTypeId);
        if (circleTypeEnum == null || start == null || end == null) {
            return 0;
        }
        int circleCount = 0;
        int offset = 1;
        while (start.before(end)) {
            circleCount++;
            if (circleTypeEnum == CircleTypeEnum.HOUR) {
                start = DateUtils.addHours(start, offset);
            } else if (circleTypeEnum == CircleTypeEnum.DAY) {
                start = DateUtils.addDays(start, offset);
            } else if (circleTypeEnum == CircleTypeEnum.MONTH) {
                start = DateUtils.addMonths(start, offset);
            }
        }
        return circleCount;
    }

    /**
     * 起始日期：2013-02-05 <br/>
     * 截止日期：2013-04－01 <br/>
     * 输出结果：<br/>
     * 1303:m <br/>
     * 1302:d2013-02-05@2013-03-01 <br/>
     * 1305:m 1304:m<br/>
     * 
     * @param start
     * @param end
     * @return
     */
    public static Map<String, String> getIntervalDayMonths(Date start, Date end) {
        Map<String, String> result = new HashMap<String, String>();
        Date temp = getMonthFirstDay(start);
        String key;
        String value;
        if (temp.compareTo(start) < 0) {
            key = DateToYYMMStr(temp);
            value = "d" + DateToShortStr(start);
            temp = DateUtils.addMonths(temp, 1);
            if (temp.compareTo(end) < 0) {
                value += Globals.ROWSPLIT + DateToShortStr(temp);
            } else {
                value += Globals.ROWSPLIT + DateToShortStr(end);
            }
            result.put(key, value);
        }
        while (temp.compareTo(end) < 0) {
            key = DateUtil.DateToYYMMStr(temp);
            result.put(key, "m");

            value = "d" + DateToShortStr(temp);
            temp = DateUtils.addMonths(temp, 1);
            if (temp.compareTo(end) > 0) {
                value += Globals.ROWSPLIT + DateToShortStr(end);
                result.put(key, value);
            }
        }
        Iterator<String> keys = result.keySet().iterator();
        while (keys.hasNext()) {
            key = keys.next();
            value = result.get(key);
            System.out.println(key + ":" + value);
        }
        return result;
    }

    /**
     * 起始日期：2013-02-05 <br/>
     * 截止日期：2013-05－23 <br/>
     * 输出结果：<br/>
     * d1302:2013-02-05@2013-03-01 <br/>
     * m1303:2013-03-01@2013-04-01 <br/>
     * d1305:2013-05-01@2013-05-24 <br/>
     * 
     * @param start
     * @param end
     * @return
     */
    public static Map<String, String> getIntervalDayMonths2(Date start, Date end) {
        end = DateUtil.addDays(end, 1);
        Map<String, String> result = new HashMap<String, String>();
        Date temp = getMonthFirstDay(start);
        String key;
        String value;
        String mvalue = "";
        String mkey = "";
        String tempvalue;
        if (temp.compareTo(start) < 0) {
            key = "d" + DateToYYMMStr(temp);
            value = DateToShortStr(start);
            temp = DateUtils.addMonths(temp, 1);
            if (temp.compareTo(end) < 0) {
                value += Globals.ROWSPLIT + DateToShortStr(temp);
            } else {
                value += Globals.ROWSPLIT + DateToShortStr(end);
            }
            result.put(key, value);
        }
        int count = 0;
        boolean flag = false;
        while (temp.compareTo(end) < 0) {
            key = DateToYYMMStr(temp);
            value = DateToShortStr(temp);
            tempvalue = DateToShortStr(temp);
            if (count == 0) {
                mvalue = value;
                mkey = "m" + key;
            }
            temp = DateUtils.addMonths(temp, 1);
            if (temp.compareTo(end) > 0) {
                key = "d" + key;
                value += Globals.ROWSPLIT + DateToShortStr(end);
                result.put(key, value);
                flag = true;
            }
            count++;
            if (temp.compareTo(end) >= 0) {
                if (flag == true) {
                    mvalue += Globals.ROWSPLIT + DateUtil.DateToShortStr(DateUtil.addMonths(DateUtil.ShortStrToDate(tempvalue), -1));
                } else {
                    mvalue += Globals.ROWSPLIT + tempvalue;
                }

            }
        }
        if (StringUtil.isNotEmpty(mkey) && StringUtil.isNotEmpty(mvalue) && !result.containsKey("d" + mkey.substring(1))) {
            result.put(mkey, mvalue);
        }
        Iterator<String> keys = result.keySet().iterator();
        while (keys.hasNext()) {
            key = keys.next();
            value = result.get(key);
            System.out.println(key + ":" + value);
        }
        return result;
    }

    /**
     * 得到时间点坐标集合，如circletype=1(小时)，得到[00,02,03...23]
     * 
     * @param circletypeid
     * @param maxMonthDay
     * @return
     */
    public static List<String> getTimePointList(Short circletypeid, int maxMonthDay) {
        List<String> datatimeList = new ArrayList<String>();
        if (CircleTypeEnum.HOUR.getShortValue().equals(circletypeid)) {// 逐时
            for (int i = 0; i < 24; i++) {
                if (i <= 9) {
                    datatimeList.add("0" + String.valueOf(i));
                } else {
                    datatimeList.add(String.valueOf(i));
                }
            }
        } else if (CircleTypeEnum.DAY.getShortValue().equals(circletypeid)) {// 逐日
            for (int i = 1; i <= maxMonthDay; i++) {
                if (i <= 9) {
                    datatimeList.add("0" + String.valueOf(i));
                } else {
                    datatimeList.add(String.valueOf(i));
                }
            }
        } else if (CircleTypeEnum.MONTH.getShortValue().equals(circletypeid)) {// 逐月
            for (int i = 1; i <= 12; i++) {
                if (i <= 9) {
                    datatimeList.add("0" + String.valueOf(i));
                } else {
                    datatimeList.add(String.valueOf(i));
                }
            }
        }
        return datatimeList;
    }

    public static void main(String[] args) {
        Date start = LongStrToDate("2015-04-13 02:28:00");
        Date end = LongStrToDate("2015-04-12 18:28:00");
        Date d = getFirstDayOfMonth(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.MONTH, -1);
        System.out.println(DateToLongStr(calendar.getTime()));

    }

    public static int getDetectorSecond(Date startday, Date endday) {
        if (startday.after(endday)) {
            Date cal = startday;
            startday = endday;
            endday = cal;
        }
        long sl = startday.getTime();
        long el = endday.getTime();
        long ei = el - sl;
        return (int) (ei / 1000);
    }

}

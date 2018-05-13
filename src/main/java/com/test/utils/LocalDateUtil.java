package com.test.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {

    private static Logger logger = LoggerFactory.getLogger(LocalDateUtil.class);

    public static final String DEFAULT_DATE_PATTEN = "yyyy-MM-dd";  //2018-04-05

    public static final String DEFAULT_DATE_TIME_PATTEN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_TIME_PATTEN = "HH:mm:ss";
    public static final String OnlyDigitDateTimePatten = "yyyyMMddHHmmss";
    public static final String OnlyDigitDatePatten = "yyyyMMdd";

    private static final DateTimeFormatter OnlyDigitDateTimeFormat = DateTimeFormatter.ofPattern(OnlyDigitDateTimePatten);
    private static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTEN);
    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTEN);
    private static final DateTimeFormatter DEFAULT_TIME_FORMAT = DateTimeFormatter.ofPattern(DEFAULT_TIME_PATTEN);

    public static void main(String[] args) {
        System.out.println(LocalDateUtil.compare("2017-10-15", "2017-10-16", "<="));
        System.out.println(LocalDateUtil.getNowTime());
    }

    /**
     * 得到当前的日期 yyyy-MM-dd
     *
     * @return
     */
    public static String getNowDate() {
        return LocalDateTime.now().format(DEFAULT_DATE_FORMAT);
    }

    /**
     * 得到当前日期时间 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getNowDateTime() {
        return LocalDateTime.now().format(DEFAULT_DATE_TIME_FORMAT);
    }

    public static String getNowDateTimeStrOnlyDigit() {
        return LocalDateTime.now().format(OnlyDigitDateTimeFormat);
    }

    public static String getNowTime() {
        return LocalDateTime.now().format(DEFAULT_TIME_FORMAT);
    }

    /**
     * 得到当前年
     *
     * @return
     */
    public static int getNowYear() {
        return LocalDateTime.now().getYear();
    }

    /**
     * 得到当前月
     *
     * @return
     */
    public static int getNowMonth() {
        return LocalDateTime.now().getMonthValue();
    }

    /**
     * 得到当前小时
     *
     * @return
     */
    public static int getNowHour() {
        return LocalDateTime.now().getHour();
    }

    /**
     * 得到当前分钟
     *
     * @return
     */
    public static int getNowMinute() {
        return LocalDateTime.now().getMinute();
    }

    /**
     * 将字符串日期转换为LocalDate日期
     *
     * @param s
     * @return
     */
    public static LocalDate strToDate(String s) {
        try {
            if (StringUtils.isBlank(s))
                return null;

            return LocalDate.parse(s, DEFAULT_DATE_FORMAT);
        } catch (Exception e) {
            logger.error("String 转 LocalDate 失败！", e);
        }

        return null;
    }

    public static LocalDate strToDate(String s, String format) {
        try {
            if (StringUtils.isAnyBlank(s, format))
                return null;
            return LocalDate.parse(s, DateTimeFormatter.ofPattern(format));
        } catch (Exception e) {
            logger.error("String 转 LocalDate 失败！", e);
        }

        return null;
    }

    /**
     * 支持以下几种格式的时间,
     * 1. yyyy-MM-dd
     * 2. yyyy-M-dd
     * 3. yyyy-M-d
     * 4. yyyy-MM-d
     **/
    public static LocalDate strToDateWithLooseFormat(String s) {
        if (StringUtils.isBlank(s))
            return null;
        s = s.trim();
        try {
            return LocalDate.parse(s, DEFAULT_DATE_FORMAT);
        } catch (Exception e) {
            return manuParseDate(s);
        }
    }

    public static LocalDate manuParseDate(String s) {
        String[] ss = s.split("-");
        if (ss.length != 3)
            return null;
        ss[0] = ss[0].trim();
        if (ss[0].length() != 4)
            return null;
        ss[1] = ss[1].trim();
        if (ss[1].length() > 2 || ss[1].length() < 1)
            return null;
        ss[2] = ss[2].trim();
        if (ss[2].length() > 2 || ss[2].length() < 1)
            return null;
        try {
            int year = Integer.parseInt(ss[0]);
            //最早支持1900年吧
            if (year < 1900)
                return null;

            int month = Integer.parseInt(ss[1]);
            if (month < 1 || month > 12)
                return null;

            int day = Integer.parseInt(ss[2]);
            if (day < 1 || day > 31)
                return null;
            return LocalDate.of(year, month, day);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将字符串日期转换为LocalDateTime日期
     *
     * @param s
     * @return
     */
    public static LocalDateTime strToDateTime(String s) {
        try {
            if (s == null) {
                return null;
            }

            if (s.indexOf(":") == -1) {
                s += " 00:00:00";
            }

            return LocalDateTime.parse(s, DEFAULT_DATE_TIME_FORMAT);
        } catch (Exception e) {
            logger.error("String 转 LocalDateTime 失败！", e);
        }

        return null;
    }

    /**
     * 将字符串日期转换为LocalDateTime日期
     *
     * @param s      字符串时间
     * @param format 格式
     * @return
     */
    public static LocalDateTime strToDateTime(String s, String format) {
        try {
            if (StringUtils.isAnyBlank(s, format))
                return null;

            return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(format));
        } catch (Exception e) {
            logger.error("String 转 LocalDateTime 失败！", e);
        }

        return null;
    }

    /**
     * 根据格式解析日期和时间，如果解析不成功，返回null，日期和时间中间间隔一个空格，主要格式为：
     * yyyy-MM-dd HH:mm:ss
     * 日期部分支持以下几种格式
     * 1.  yyyy-MM-dd    2018-04-05
     * 2.  yyyy-M-dd     2018-4-15
     * 3.  yyyy-MM-d     2018-11-5
     * 4.  yyyy-M-d      2018-4-5
     * 时间部分支持以下几种格式
     * 1.  HH:mm:ss    小时部分为2位数，如： 01，12
     * 2.  H:mm:ss     小时部分为1位数，小于10省略前面的0，  如1
     **/
    public static LocalDateTime strToDateTimeWithLooseFormat(String dateTimeStr) {
        if (StringUtils.isBlank(dateTimeStr))
            return null;

        dateTimeStr = dateTimeStr.trim();
        try {
            return LocalDateTime.parse(dateTimeStr, DEFAULT_DATE_TIME_FORMAT);
        } catch (Exception e) {
            try {
                String[] parts = dateTimeStr.split(" ");
                if (parts.length != 2)
                    return null;

                LocalDate date = strToDateWithLooseFormat(parts[0]);
                if (null == date)
                    return null;

                LocalTime time = strToTimeWithLooseFormat(parts[1]);
                if (null == time)
                    return null;

                return LocalDateTime.of(date, time);
            } catch (Exception e1) {
                return null;
            }
        }
    }

    /**
     * 解析时间部分，支持以下2种格式
     * 1. HH-mm-ss 小时部分小于10时，前面补0
     * 2. H-mm-ss  小时部分小于10时，省略前面的0
     **/
    public static LocalTime strToTimeWithLooseFormat(String timeStr) {
        if (StringUtils.isBlank(timeStr))
            return null;

        timeStr = timeStr.trim();
        try {
            String[] parts = timeStr.split(":");
            if (parts.length != 3)
                return null;
            int hour = Integer.parseInt(parts[0]);
            if (hour < 0 || hour > 23)
                return null;

            int minute = Integer.parseInt(parts[1]);
            if (minute < 0 || minute > 59)
                return null;

            int second = Integer.parseInt(parts[2]);
            if (second < 0 || second > 59)
                return null;
            return LocalTime.of(hour, minute, second);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将LocalDateTime日期转换为字符串日期 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String dateTimeToStr(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return date.format(DEFAULT_DATE_TIME_FORMAT);
    }

    public static String dateTimeToDateStr(LocalDateTime dateTime) {
        if (dateTime == null)
            return null;

        return dateTime.format(DEFAULT_DATE_FORMAT);
    }

    /**
     * 将LocalDateTime日期转换为字符串日期 yyyy-MM-dd HH:mm:ss
     *
     * @param date   datetime类型
     * @param format 格式
     * @return
     */
    public static String dateTimeToStr(LocalDateTime date, String format) {
        if (date == null || format == null) {
            return null;
        }
        return date.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 将LocalDate日期转换为字符串日期 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String dateToStr(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(DEFAULT_DATE_FORMAT);
    }

    /**
     * 将LocalDate日期转换为字符串日期 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String dateToStr(LocalDate date, String format) {
        if (date == null) {
            return null;
        }
        return date.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 比较时间
     *
     * @param dateStr1 字符串时间1
     * @param dateStr2 字符串时间2
     * @param operator 比较符（=,==,>,<,>=,<=,!=）
     * @return
     */
    public static boolean compare(String dateStr1, String dateStr2, String operator) {
        long time1 = strToDateTime(dateStr1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long time2 = strToDateTime(dateStr2).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if ("=".equals(operator) || "==".equals(operator)) {
            return time1 == time2;
        } else if (">".equals(operator)) {
            return time1 > time2;
        } else if ("<".equals(operator)) {
            return time1 < time2;
        } else if (">=".equals(operator)) {
            return time1 >= time2;
        } else if ("<=".equals(operator)) {
            return time1 <= time2;
        } else if ("!=".equals(operator)) {
            return time1 != time2;
        }

        return false;
    }

    /**
     * 得到日期的毫秒数
     *
     * @param dateStr 字符串时间
     * @return
     */
    public static long toEpochMilli(String dateStr) {
        return strToDateTime(dateStr).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 得到日期的毫秒数
     *
     * @param dateStr 字符串时间
     * @return
     */
    public static long toEpochSecond(String dateStr) {
        return strToDateTime(dateStr).atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    /**
     * 根据时间增减天，count为负表示减
     *
     * @param dateStr
     * @param count
     * @return
     */
    public static String addDay(String dateStr, int count) {
        if (dateStr.indexOf(":") == -1) {
            return strToDateTime(dateStr).plusDays(count).format(DEFAULT_DATE_FORMAT);
        }
        return strToDateTime(dateStr).plusDays(count).format(DEFAULT_DATE_TIME_FORMAT);
    }

    /**
     * 根据时间增减小时，count为负表示减
     *
     * @param dateStr
     * @param count
     * @return
     */
    public static String addHour(String dateStr, int count) {
        if (dateStr.indexOf(":") == -1) {
            return strToDateTime(dateStr).plusHours(count).format(DEFAULT_DATE_FORMAT);
        }
        return strToDateTime(dateStr).plusHours(count).format(DEFAULT_DATE_TIME_FORMAT);
    }

    /**
     * 根据时间增减分钟，count为负表示减
     *
     * @param dateStr
     * @param count
     * @return
     */
    public static String addMinute(String dateStr, int count) {
        if (dateStr.indexOf(":") == -1) {
            return strToDateTime(dateStr).plusMinutes(count).format(DEFAULT_DATE_FORMAT);
        }
        return strToDateTime(dateStr).plusMinutes(count).format(DEFAULT_DATE_TIME_FORMAT);
    }

    public static LocalDateTime addMinute(LocalDateTime time, int count) {
        if (null == time)
            return null;
        return time.plusMinutes(count);
    }

    /**
     * 根据时间增减秒钟，count为负表示减
     *
     * @param dateStr
     * @param count
     * @return
     */
    public static String addSecond(String dateStr, int count) {
        if (dateStr.indexOf(":") == -1) {
            return strToDateTime(dateStr).plusSeconds(count).format(DEFAULT_DATE_FORMAT);
        }
        return strToDateTime(dateStr).plusSeconds(count).format(DEFAULT_DATE_TIME_FORMAT);
    }

    /**
     * 返回时间差 ， 单位为毫秒
     *
     * @param date1
     * @param date2
     * @return data1 - data2
     */
    public static long diffMilli(LocalDateTime date1, LocalDateTime date2) {
        return date1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                - date2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 返回时间差 ， 单位为毫秒
     *
     * @param dateStr1
     * @param dateStr2
     * @return dateStr1 - dateStr2
     */
    public static long diffMilli(String dateStr1, String dateStr2) {
        long time1 = strToDateTime(dateStr1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long time2 = strToDateTime(dateStr2).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return time1 - time2;
    }

    /**
     * 返回时间差 ， 单位为秒
     *
     * @param date1
     * @param date2
     * @return data1 - data2
     */
    public static long diffSecond(LocalDateTime date1, LocalDateTime date2) {
        return diffMilli(date1, date2) / 1000l;
    }

    /**
     * 返回时间差 ， 单位为毫秒
     *
     * @param dateStr1
     * @param dateStr2
     * @return dateStr1 - dateStr2
     */
    public static long diffSecond(String dateStr1, String dateStr2) {
        return diffMilli(dateStr1, dateStr2) / 1000l;
    }

    /**
     * 返回时间差 ， 单位为秒
     *
     * @param date1
     * @param date2
     * @return data1 - data2
     */
    public static long diffMinute(LocalDateTime date1, LocalDateTime date2) {
        return diffMilli(date1, date2) / (1000l * 60l);
    }

    /**
     * 返回时间差 ， 单位为天
     *
     * @param dateStr1
     * @param dateStr2
     * @return data1 - data2
     */
    public static long diffDay(String dateStr1, String dateStr2) {
        return diffMilli(dateStr1, dateStr2) / (1000l * 60 * 60 * 24);
    }

    /**
     * 返回时间差 ， 单位为天
     *
     * @param date1
     * @param date2
     * @return data1 - data2
     */
    public static long diffDay(LocalDateTime date1, LocalDateTime date2) {
        return diffMilli(date1, date2) / (1000l * 60l * 60 * 24);
    }

    public static boolean isTimeBetweenClock(LocalDateTime date, int startClock, int endClock) {
        if (startClock > endClock && endClock == 0) {
            endClock = 24;
        }
        long currentClock = date.getHour();
        return startClock <= currentClock && endClock > currentClock;
    }

    public static LocalTime parseTimeStringHHMM(String time) {
        if (StringUtils.isBlank(time))
            return null;
        time = time.trim();
        if (time.length() != 4)
            return null;
        String hourStr = time.substring(0, 2);
        String minuteStr = time.substring(2);
        return parseTimeStringHHAndMM(hourStr, minuteStr);
    }

    public static LocalTime parseTimeStringHHAndMM(String hourStr, String minuteStr) {
        int hour = Integer.parseInt(hourStr);
        if (hour < 0 || hour > 23)
            return null;

        int minute = Integer.parseInt(minuteStr);
        if (minute < 0 || minute > 59)
            return null;
        return LocalTime.of(hour, minute);
    }

    public static Integer parseMinuteStringHHMM(String time) {
        LocalTime localTime = parseTimeStringHHMM(time);
        if (null == localTime)
            return null;

        return calcMinutes(localTime.getHour(), localTime.getMinute());
    }

    public static Integer parseMinuteStringHHAndMM(String hourStr, String minuteStr) {
        LocalTime localTime = parseTimeStringHHAndMM(hourStr, minuteStr);
        if (null == localTime)
            return null;

        return calcMinutes(localTime.getHour(), localTime.getMinute());
    }

    private static Integer calcMinutes(int hour, int minute) {
        return hour * 60 + minute;
    }

    /**
     * 把UTC的HHMM时间转换成北京时间的小时和分钟, 加上8小时
     **/
    public static LocalTime UTC2BeijingTime(String strTime) {
        if (StringUtils.isBlank(strTime)) {
            return null;
        }
        try {
            String hourStr = strTime.substring(0, 2);
            String minStr = strTime.substring(2, 4);

            int hour = Integer.parseInt(hourStr);
            if (hour < 0 || hour >= 24)
                return null;
            int minutes = Integer.parseInt(minStr);
            if (minutes < 0 || minutes >= 60)
                return null;


            //UTC转北京时间， 如果大于24取摸
            hour += 8;
            hour = hour % 24;

            return LocalTime.of(hour, minutes);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 时间格式为 yyyy-MM-dd HH:mm:ss (中间有个空格,可以根据空格来分割日期和时间部分)
     * 或者 yyyy-MM-dd
     **/
    public static String formatDateTimeStr2DateStr(String datetimeStr) {
        if (StringUtils.isBlank(datetimeStr))
            return null;

        LocalDate date = LocalDateUtil.strToDateWithLooseFormat(datetimeStr.split(" ")[0]);
        return LocalDateUtil.dateToStr(date);
    }

    public static String formatDateTimeStr(String datetimeStr) {
        if (StringUtils.isBlank(datetimeStr))
            return null;

        LocalDateTime dateTime = LocalDateUtil.strToDateTimeWithLooseFormat(datetimeStr);
        return LocalDateUtil.dateTimeToStr(dateTime);
    }
}
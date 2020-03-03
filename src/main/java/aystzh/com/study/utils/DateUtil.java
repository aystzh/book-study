package aystzh.com.study.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author yilanqunzhi
 * @title: DateUtil
 * @projectName search
 * @packageName com.elens.search.utils
 * @description: 日期工具类
 * @date 2019/8/30 16:06
 */
public class DateUtil {

    private static Calendar calendar = Calendar.getInstance();
    //ES 日期格式:

    /**
     * 获取 N 天时间 (-7 代表前 7 天 )
     *
     * @return n
     */
    public static Date getDay(int n) throws Exception {

        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, n);

        return calendar.getTime();
    }

    /**
     * 获取 N 个月前日期  (-1 代表前 1 月 )
     *
     * @return n
     */
    public static Date getMonth(int n) throws Exception {

        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, n);

        return calendar.getTime();
    }

    /**
     * 获取 N 个年前日期  (-1 代表前 1 年 )
     *
     * @return n
     */
    public static Date getYear(int n) throws Exception {

        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, n);
        Date date = calendar.getTime();

        return calendar.getTime();
    }


    //获取当天的开始时间
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //获取当天的结束时间
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    //获取昨天的开始时间
    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    //获取昨天的结束时间
    public static Date getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    //获取明天的开始时间
    public static Date getBeginDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }

    //获取明天的结束时间
    public static Date getEndDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    //获取本周的开始时间
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    //获取本周的结束时间
    public static Date getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    //获取本月的开始时间
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }

    //获取本月的结束时间
    public static Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }

    //获取本年的开始时间
    public static Date getBeginDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        // cal.set
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);

        return getDayStartTime(cal.getTime());
    }

    //获取本年的结束时间
    public static Date getEndDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }

    //获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    //获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    //获取今年是哪一年
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    //获取本月是哪一月
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    //两个日期相减得到的天数
    public static int getDiffDays(Date beginDate, Date endDate) {

        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }

        long diff = (endDate.getTime() - beginDate.getTime())
                / (1000 * 60 * 60 * 24);

        int days = new Long(diff).intValue();

        return days;
    }

    //两个日期相减得到的毫秒数
    public static long dateDiff(Date beginDate, Date endDate) {
        long date1ms = beginDate.getTime();
        long date2ms = endDate.getTime();
        return date2ms - date1ms;
    }

    //获取两个日期中的最大日期
    public static Date max(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return beginDate;
        }
        return endDate;
    }

    //获取两个日期中的最小日期
    public static Date min(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return endDate;
        }
        return beginDate;
    }

    //返回某月该季度的第一个月
    public static Date getFirstSeasonDate(Date date) {
        final int[] SEASON = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int sean = SEASON[cal.get(Calendar.MONTH)];
        cal.set(Calendar.MONTH, sean * 3 - 3);
        return cal.getTime();
    }

    //返回某个日期下几天的日期
    public static Date getNextDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
        return cal.getTime();
    }

    //返回某个日期前几天的日期
    public static Date getFrontDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
        return cal.getTime();
    }

    //获取某年某月到某年某月按天的切片日期集合（间隔天数的日期集合）
    public static List getTimeList(int beginYear, int beginMonth, int endYear,
                                   int endMonth, int k) {
        List list = new ArrayList();
        if (beginYear == endYear) {
            for (int j = beginMonth; j <= endMonth; j++) {
                list.add(getTimeList(beginYear, j, k));

            }
        } else {
            {
                for (int j = beginMonth; j < 12; j++) {
                    list.add(getTimeList(beginYear, j, k));
                }

                for (int i = beginYear + 1; i < endYear; i++) {
                    for (int j = 0; j < 12; j++) {
                        list.add(getTimeList(i, j, k));
                    }
                }
                for (int j = 0; j <= endMonth; j++) {
                    list.add(getTimeList(endYear, j, k));
                }
            }
        }
        return list;
    }

    //获取某年某月按天切片日期集合（某个月间隔多少天的日期集合）
    public static List getTimeList(int beginYear, int beginMonth, int k) {
        List list = new ArrayList();
        Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
        int max = begincal.getActualMaximum(Calendar.DATE);
        for (int i = 1; i < max; i = i + k) {
            list.add(begincal.getTime());
            begincal.add(Calendar.DATE, k);
        }
        begincal = new GregorianCalendar(beginYear, beginMonth, max);
        list.add(begincal.getTime());
        return list;
    }


    /**
     * 默认日期格式
     */
    public static final String DEF_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 默认时间格式
     */
    public static final String DEF_TIME_FORMAT = "HH:mm:ss";
    /**
     * 默认日期时间格式
     */
    public static final String DEF_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 精确到分钟时间格式
     */
    public static final String MINUTE_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * 默认日期匹配格式
     */
    public static final String DEF_DATE_PATTERN = "\\d{4}\\-\\d{2}-\\d{2}";
    /**
     * 默认日期时间匹配格式
     */
    public static final String DEF_DATETIME_PATTERN = "\\d{4}\\-\\d{2}-\\d{2}\\p{javaWhitespace}\\d{2}:\\d{2}:\\d{2}";


    /**
     * 取得系统当前时间戳
     *
     * @return 系统当前时间戳对象
     */
    public static Timestamp getSysTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 将yyyy-MM-dd格式的字符串转换为日期对象
     *
     * @param date yyyy-MM-dd格式字符串
     * @return 转换后的日期对象，无法转换时返回null
     */
    public static Date getDate(String date) {
        if (!matchesPattern(date, DEF_DATE_PATTERN)) {
            return null;
        }
        return parseDate(date, DEF_DATE_FORMAT);
    }

    /**
     * 将yyyy-MM-dd格式的字符串转换为时间戳对象
     *
     * @param date yyyy-MM-dd格式字符串
     * @return 转换后的时间戳对象，无法转换时返回null
     */
    public static Timestamp getTimestamp(String date) {
        if (!matchesPattern(date, DEF_DATE_PATTERN)) {
            return null;
        }
        return new Timestamp(parseDate(date, DEF_DATE_FORMAT).getTime());
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式的字符串转换为日期对象
     *
     * @param datetime yyyy-MM-dd HH:mm:ss格式字符串
     * @return 转换后的日期对象，无法转换时返回null
     */
    public static Date getDateTime(String datetime) {
        if (!matchesPattern(datetime, DEF_DATETIME_PATTERN)) {
            return null;
        }
        return parseDate(datetime, DEF_DATETIME_FORMAT);
    }

    public static Date getPatternDate(Date date) {
        if (date == null) {
            return null;
        }
        return parseDate(formatDateTime(date), DEF_DATETIME_FORMAT);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式的字符串转换为时间戳期对象
     *
     * @param datetime yyyy-MM-dd HH:mm:ss格式字符串
     * @return 转换后的时间戳对象，无法转换时返回null
     */
    public static Timestamp getDateTimeStamp(String datetime) {
        if (!matchesPattern(datetime, DEF_DATETIME_PATTERN)) {
            return null;
        }
        return new Timestamp(parseDate(datetime, DEF_DATETIME_FORMAT).getTime());
    }

    /**
     * 将指定格式的字符串对象转换为日期对象
     *
     * @param date    字符串
     * @param pattern 指定的格式
     * @return 转换后的日期，无法转换时返回null
     */
    public static Date getDate(String date, String pattern) {
        return getDate(date, pattern, null);
    }

    /**
     * 将指定格式的字符串对象转换为日期对象
     *
     * @param date    字符串
     * @param pattern 指定的格式
     * @param defVal  默认返回值
     * @return 转换后的日期，无法转换时返回defVal指定值
     */
    public static Date getDate(String date, String pattern, Date defVal) {
        if (date == null || pattern == null) {
            return null;
        }
        Date ret = parseDate(date, pattern);
        return (ret == null) ? defVal : ret;
    }

    /**
     * 根据指定的格式格式将传入字符串转化为日期对象
     *
     * @param date   传入字符串
     * @param format 指定格式
     * @return 格式化后日期对象
     */
    private static Date parseDate(String date, String format) {
        Date d;
        try {
            d = new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            d = null;
        }
        return d;
    }

    /**
     * 检测输入字符串是否与指定格式匹配
     *
     * @param input   待检测字符串
     * @param pattern 检测格式
     * @return <li>true：匹配</li>
     * <li>false：不匹配</li>
     */
    private static boolean matchesPattern(String input, String pattern) {
        return (input != null) && (input.matches(pattern));
    }

    /**
     * 将日期对象格式化成yyyy-mm-dd类型的字符串
     *
     * @param date 日期对象
     * @return 格式化后的字符串，无法格式化时，返回null
     */
    public static String formatDate(Date date) {
        return formatDateToString(date, DEF_DATE_FORMAT);
    }

    /**
     * 将日期对象格式化成HH:mm:ss类型的字符串
     *
     * @param date 日期对象
     * @return 格式化后的字符串，无法格式化时，返回null
     */
    public static String formatTime(Date date) {
        return formatDateToString(date, DEF_TIME_FORMAT);
    }

    /**
     * 将日期对象格式化成yyyy-MM-dd HH:mm:ss类型的字符串
     *
     * @param date 日期对象
     * @return 格式化后的字符串，无法格式化时，返回null
     */
    public static String formatDateTime(Date date) {
        return formatDateToString(date, DEF_DATETIME_FORMAT);
    }

    /**
     * 将日期对象格式化成yyyy-MM-dd HH:mm类型的字符串
     *
     * @param date 日期对象
     * @return 格式化后的字符串，无法格式化时，返回null
     */
    public static String formateMinuteDate(Date date) {
        return formatDateToString(date, MINUTE_FORMAT);
    }

    /**
     * 将日期对象格式化成指定的格式字符串
     *
     * @param date   日期对象
     * @param format 格式
     * @return 格式化后的字符串，无法格式化时，返回null
     */
    private static String formatDateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 将日期对象格式化成指定格式的字符串
     *
     * @param date   日期对象
     * @param format 格式
     * @return 格式化后的字符串，无法格式化时，返回null
     */
    public static String formatDate(Date date, String format) {
        if (date == null || format == null) {
            return null;
        }
        String ret;
        try {
            ret = new SimpleDateFormat(format).format(date);
        } catch (RuntimeException e) {
            ret = null;
        }
        return ret;
    }

    /**
     * 取得指定日期所在月的最后一天日期对象
     *
     * @param d 指定日期
     * @return 指定日期当月的最后一天日期对象，如指定日期为null时，返回当前月的最后一天日期对象
     */
    public static Date getLastDayObjectInMonth(Date d) {
        Calendar cal = Calendar.getInstance();
        if (d != null) {
            cal.setTime(d);
        }
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 取得指定日期所在月的第一天日期对象
     *
     * @param d 指定日期
     * @return 指定日期当月的第一天日期对象，如指定日期为null时，返回当前月的第一天日期对象
     */
    public static Date getFirstDayObjectInMonth(Date d) {
        Calendar cal = Calendar.getInstance();
        if (d != null) {
            cal.setTime(d);
        }
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return cal.getTime();
    }

    /**
     * 取得指定日期所在月的最后一天日期值
     *
     * @param d 指定日期
     * @return 当月的最后一天日期值，如指定日期为null时，返回当前月的最后一天日期值
     * @see #getLastDayObjectInMonth(Date)
     */
    public static int getLastDayInMonth(Date d) {
        Date date = getLastDayObjectInMonth(d);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (24 * 60 * 60 * 1000);

        return Integer.parseInt(String.valueOf(between_days));
    }

    @SuppressWarnings("deprecation")
    public static long compailer(Date date11, Date date22) throws Exception {
        //System.out.println(2.75%0.5);
        Date date1 = new Date();
        Date date2 = new Date();
        date11 = new Date();
        date22 = new Date("2017-05-06 12:50:50");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm:ss");

        //date11 = format.format(date1);
        //date22 = format.format(date2);
        date1 = format.parse(format.format(date11));
        date2 = format.parse(format.format(date22));
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long test = Math.abs(time2 - time1) / 1000;
        System.out.println("time1" + time1);
        System.out.println("time2" + time2);
        System.out.println(test);
        Date result = new Date();
        result.setTime(test);
        System.out.println(timeformat.format(result));

        return test;
    }


    /**
     * 计算两个日期之间相差的秒数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int secondsBetween(Date smdate, Date bdate) throws ParseException {

        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 新增的格式化时间类,将时间进行标准化格式,适用于将前台传入的日期格式化为实际可行的日期
     * 如将20050600格式化为20050601,或将20050631格式化为20050630
     *
     * @param _dateTime 传入的原时间串
     * @param _format   格式符,YYYYMMDDHH24MISS,YYYYMMDDHH12MISS
     * @return String
     * @throws Exception
     */
    public static String formatDateTime(String _dateTime, String _format) throws Exception {
        String returnValue = "";
        String formatString = _format.toUpperCase();
        String strYear = "";
        String strMonth = "";
        String strDay = "";
        String strHour = "";
        String strMinu = "";
        String strSec = "";
        int hourType = 12; //12小时制,24小时制
        int yearType = 1; //1为平年,2为闰年
        try {
            if (formatString.indexOf("YYYY") >= 0) {
                int tempBeginPlace = formatString.indexOf("YYYY");
                int temEndPlace = tempBeginPlace + 4;
                strYear = _dateTime.substring(tempBeginPlace, temEndPlace);
            }
            if (formatString.indexOf("MM") >= 0) {
                int tempBeginPlace = formatString.indexOf("MM");
                int temEndPlace = tempBeginPlace + 2;
                strMonth = _dateTime.substring(tempBeginPlace, temEndPlace);
            }
            if (formatString.indexOf("DD") >= 0) {
                int tempBeginPlace = formatString.indexOf("DD");
                int temEndPlace = tempBeginPlace + 2;
                strDay = _dateTime.substring(tempBeginPlace, temEndPlace);
            }
            if (formatString.indexOf("HH24") >= 0) {
                int tempBeginPlace = formatString.indexOf("HH24");
                int temEndPlace = tempBeginPlace + 2;
                strHour = _dateTime.substring(tempBeginPlace, temEndPlace);
                formatString = formatString.replaceAll("24", "");
                //为了保持位数一致,去除24
                hourType = 24;
            } else if (formatString.indexOf("HH12") >= 0) {
                int tempBeginPlace = formatString.indexOf("HH12");
                int temEndPlace = tempBeginPlace + 2;
                strHour = _dateTime.substring(tempBeginPlace, temEndPlace);
                formatString = formatString.replaceAll("12", "");
                //为了保持位数一致,去除12
                hourType = 12;
            } else if (formatString.indexOf("HH") >= 0) {
                int tempBeginPlace = formatString.indexOf("HH");
                int temEndPlace = tempBeginPlace + 2;
                strHour = _dateTime.substring(tempBeginPlace, temEndPlace);
                hourType = 12; //如果未指定小时制,则默认为12小时制;
            }
            if (formatString.indexOf("MI") >= 0) {
                int tempBeginPlace = formatString.indexOf("MI");
                int temEndPlace = tempBeginPlace + 2;
                strMinu = _dateTime.substring(tempBeginPlace, temEndPlace);
            }
            if (formatString.indexOf("SS") >= 0) {
                int tempBeginPlace = formatString.indexOf("SS");
                int temEndPlace = tempBeginPlace + 2;
                strSec = _dateTime.substring(tempBeginPlace, temEndPlace);
            }

            //判断是否是闰年
            if (!"".equals(strYear)) {
                int intYear = Integer.parseInt(strYear);
                //能被4整除，但不能被100整除② 能被4整除，且能被400
                if (intYear % 4 == 0) {
                    if (intYear % 100 != 0) {
                        yearType = 2;
                    }
                }
                if (intYear % 4 == 0) {
                    if (intYear % 400 == 0) {
                        yearType = 2;
                    }
                }
            }
            //格式化月
            if (!"".equals(strMonth)) {
                int intMonth = Integer.parseInt(strMonth);
                if (intMonth == 0) {
                    strMonth = "01";
                    intMonth = 1;
                }
                if (intMonth > 12) {
                    strMonth = "12";
                    intMonth = 12;
                }
            }

            //格式化日
            if (!"".equals(strDay)) {
                int intDay = Integer.parseInt(strDay);
                if (intDay == 0) {
                    strDay = "01";
                    intDay = 1;
                }
                if (intDay > 31) {
                    strDay = "31";
                    intDay = 31;
                }
                if (("01".equals(strMonth))
                        || ("03".equals(strMonth))
                        || ("05".equals(strMonth))
                        || ("07".equals(strMonth))
                        || ("08".equals(strMonth))
                        || ("10".equals(strMonth))
                        || ("12".equals(strMonth))) {
                    if (intDay > 31) {
                        strDay = "31";
                        intDay = 31;
                    }
                }
                if (("02".equals(strMonth))
                        || ("04".equals(strMonth))
                        || ("06".equals(strMonth))
                        || ("09".equals(strMonth))
                        || ("11".equals(strMonth))) {
                    if (intDay > 30) {
                        strDay = "30";
                        intDay = 30;
                    }
                    if ("02".equals(strMonth)) { //对2月的特别处理
                        if (yearType == 2) {
                            if (intDay > 29) {
                                strDay = "29";
                                intDay = 29;
                            }
                        } else {
                            if (intDay > 28) {
                                strDay = "28";
                                intDay = 28;
                            }
                        }
                    }
                }

                //格式化小时
                if (!"".equals(strHour)) {
                    int intHour = Integer.parseInt(strHour);
                    if (intHour > 24) {
                        strHour = "24";
                        intHour = 24;
                    }
                    if (hourType == 12) {
                        if (intHour == 0) {
                            intHour = 1;
                            strHour = "01";
                        }
                        if (intHour > 12) {
                            intHour = intHour - 12;
                            strHour = "0" + intHour;
                        }
                    } else {
                        if (intHour > 23) {
                            intHour = 23;
                            strHour = "23";
                        }
                    }
                }
                //格式化分
                if (!"".equals(strMinu)) {
                    int intMinu = Integer.parseInt(strMinu);
                    if (intMinu > 59) {
                        strMinu = "59";
                        intMinu = 59;
                    }
                }
                //格式化秒
                if (!"".equals(strSec)) {
                    int intSec = Integer.parseInt(strSec);
                    if (intSec > 59) {
                        strSec = "59";
                        intSec = 59;
                    }
                }
            }
            returnValue = strYear + strMonth + strDay + strHour + strMinu + strSec;
            return returnValue;
        } catch (Exception e) {
            throw e;
        }
    }

    public static  String [] getRecentSevenDay(){
        String [] arr = new String[7];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = null;
        for (int i=0;i<7;i++){
            c=Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, - i);
            arr[6-i] =sdf.format(c.getTime());

        }
        return arr;
    }

    /*
    * 将时间戳转换为时间
    */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    public static boolean isToday(String str, String formatStr) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH)+1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH)+1;
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        if(year1 == year2 && month1 == month2 && day1 == day2){
            return true;
        }
        return false;
    }
}

package com.tangyongdong.sale.user.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *
 * 时间处理工具类
 *
 * @author tangyongdong
 * @create 2018-05-02 10:04
 */
public final class DateUtil {

    /**
     * Date转换为LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime date2LocalDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param time
     * @return
     */
    public static Date localDate2Date(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当前时间的 "yyyy-MM-dd HH:mm:ss"格式字符串
     *
     * @return
     */
    public static String now() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


    /**
     * 时间格式转化 long -> "yyyy-MM-dd HH:mm:ss"格式字符串
     *
     * @param time
     * @return
     */
    public static String long2DateString(long time) {
        return time <= 0 ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time));
    }

    /**
     * 时间格式转化 long -> 对应格式字符串
     *
     * @param time
     * @param dateFormatStr
     * @return
     */
    public static String long2DateString(long time, String dateFormatStr) {
        return time <= 0 ? "" : new SimpleDateFormat(dateFormatStr).format(new Date(time));
    }

    /**
     * 时间格式转化 "yyyy-MM-dd HH:mm:ss"格式字符串 -> long
     *
     * @param timeStr
     * @return
     */
    public static long string2Long(String timeStr) {
        Date parse = null;
        try {
            parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse.getTime();
    }

    /**
     * 时间格式转化 long-> Date
     *
     * @param time
     * @return
     */
    public static Date long2Date(long time) {
        return time <= 0 ? null : new Date(time);
    }

    /**
     * 时间格式化 "yyyy-MM-dd HH:mm:ss"格式字符串 -> Date
     *
     * @param timeStr
     * @return
     */
    public static Date dateFormat(String timeStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算间隔时间
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 时间差
     */
    public static long subTime(long beginTime, long endTime) {
        return endTime - beginTime;
    }

    /**
     * 计算间隔时间
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return 时间差
     */
    public static long subDate(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            return 0L;
        }
        return beginDate.getTime() - endDate.getTime();
    }

    /**
     * 时间转化为字符串
     *
     * @param time
     * @return
     */
    public static String secViewTime(long time) {
        if (time <= 60) {
            return "1分钟";
        }
        int days = 0, hours = 0, minutes = 0;
        time = time / 1000;
        if (time >= 86400) {
            days = Double.valueOf(Math.floor(time / 86400)).intValue();
            time = time % 86400;
        }
        if (time >= 3600) {
            hours = Double.valueOf(Math.floor(time / 3600)).intValue();
            time = time % 3600;
        }
        if (time >= 60) {
            minutes = Double.valueOf(Math.floor(time / 60)).intValue();
        }
        StringBuilder timeBuilder = new StringBuilder();

        if (days > 0) {
            timeBuilder.append(days).append("天");
        }
        if (hours > 0) {
            timeBuilder.append(hours).append("小时");
        }
        if (minutes > 0) {
            timeBuilder.append(minutes).append("分钟");
        }
        return timeBuilder.toString();
    }

    /**
     * 获取时间差
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String getDayHoursMinutes(LocalDateTime startTime, LocalDateTime endTime) {
        long days = ChronoUnit.DAYS.between(endTime, startTime);
        long hours = ChronoUnit.HOURS.between(endTime, startTime);
        long minutes = ChronoUnit.MINUTES.between(endTime, startTime);
        StringBuilder timeBuilder = new StringBuilder();
        if (days >= 0) {
            timeBuilder.append(days).append("天");
        }
        if (hours >= 0) {
            timeBuilder.append(hours).append("小时");
        }
        if (minutes > 0) {
            timeBuilder.append(minutes).append("分钟");
        }
        return timeBuilder.toString();
    }
}

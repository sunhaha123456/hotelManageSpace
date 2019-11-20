package com.rose.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public final static String DATEFORMAT = "yyyy-MM-dd";
    public final static String TIMEFORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentDate() {
        return format(new Date(), DATEFORMAT);
    }

    public static String formatDate2Date(Date date) {
        return format(date, DATEFORMAT);
    }

    public static String formatDate2Time(Date date) {
        return format(date, TIMEFORMAT);
    }

    public static Date formatStr2Date(String date) {
        return format(date, DATEFORMAT);
    }

    public static Date formatStr2Time(String date) {
        return format(date, TIMEFORMAT);
    }

    public static String formatStrTime(String date) {
        return format(format(date, TIMEFORMAT), TIMEFORMAT);
    }

    public static String format(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date format(String strDate, String format) {
        if (StringUtil.isNotEmpty(strDate)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.parse(strDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Date addDate(Date strDate, int number) {
        Date date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(strDate);
        cal.add(Calendar.DATE, number);
        date = cal.getTime();
        return date;
    }

    public static String addDate(String strDate, int number) {
        return formatDate2Date(addDate(formatStr2Date(strDate), number));
    }
}
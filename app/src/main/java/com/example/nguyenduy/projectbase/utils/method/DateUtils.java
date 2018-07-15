package com.example.nguyenduy.projectbase.utils.method;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

    public final static int PASSED_DAY = -1;
    public final static int CURRENT_DAY = 0;
    public final static int FUTURE_DAY = 1;

    private static final String DATE_PATTERN_ONLY_DAY = "dd";
    private static final String DATE_PATTERN_ONLY_MONTH = "MM";
    private static final String DATE_PATTERN_ONLY_YEAR = "yyyy";
    private static final String DATE_PATTERN_ONLY_HOUR = "hh";
    private static final String DATE_PATTERN_ONLY_MINUTE = "mm";
    private static final String DATE_PATTERN_ONLY_SECOND = "ss";
    private static final String DATE_PATTERN_ONLY_NAME_MONTH = "MMM";
    private static final String DATE_PATTERN_MMDDYYYY_HHMMSS = "MM/dd/yyyy hh:mm:ss";
    private static final String DATE_PATTERN_DDMMM_HHMMA = "dd MMM, hh:mm a";
    private static final String DATE_PATTERN_MMDDYYYY = "MM/dd/yyyy";
    private static final String DATE_PATTERN_HHMMA = "hh:mm a";
    private static final String DATE_PATTERN_HHMMSS = "hh:mm:ss";
    private static final String DATE_PATTERN_EEE_MMMDDYYYY = "EEE, MMM dd, yyyy";
    private static final String DATE_PATTERN_EEE_MMMDD_YYYY = "MMM dd, yyyy";

    public static int getOnlyYear(Calendar date) {
        return date.get(Calendar.YEAR);
    }

    public static int getOnlyMonth(Calendar date) {
        return date.get(Calendar.MONTH);
    }

    public static int getOnlyDay(Calendar date) {
        return date.get(Calendar.DAY_OF_MONTH);
    }

    public static int getOnlyHour12h(Calendar date) {
        return date.get(Calendar.HOUR);
    }

    public static int getOnlyHour24h(Calendar date) {
        return date.get(Calendar.HOUR_OF_DAY);
    }

    public static int getOnlyMinute(Calendar date) {
        return date.get(Calendar.MINUTE);
    }

    public static int getOnlySecond(Calendar date) {
        return date.get(Calendar.SECOND);
    }

    public static int getOnlyMillisecond(Calendar date) {
        return date.get(Calendar.MILLISECOND);
    }

    public static String getOnlyNameMonth(Calendar date) {
        return new SimpleDateFormat(DATE_PATTERN_ONLY_NAME_MONTH).format(date.getTime());
    }

    public static String getOnlyNameDay(Calendar date) {
        // TODO
        return "";
    }

    public static Calendar getFirstDayOfMonth(Calendar date) {
        date.set(Calendar.DAY_OF_MONTH, 1);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date;
    }

    public static Calendar getLastDayOfMonth(Calendar date) {
        date.set(Calendar.DAY_OF_MONTH, 1);
        date.add(Calendar.MONTH, 1);
        date.add(Calendar.DATE, -1);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date;
    }

    // = 1 khi date 1 > date 2
    // = 0 khi date 1 = date 2
    // = -1 neu date 1 < date 2
    public static int compare(Calendar date1, Calendar date2) {
        int year1 = getOnlyYear(date1);
        int year2 = getOnlyYear(date2);
        if (year1 < year2) {
            return PASSED_DAY;
        } else if (year1 > year2) {
            return FUTURE_DAY;
        }
        // same year
        int month1 = getOnlyMonth(date1);
        int month2 = getOnlyMonth(date2);
        if (month1 < month2) {
            return PASSED_DAY;
        } else if (month1 > month2) {
            return FUTURE_DAY;
        }
        // same month
        int day1 = getOnlyDay(date1);
        int day2 = getOnlyMonth(date2);
        if (day1 < day2) {
            return PASSED_DAY;
        } else if (day1 > day2) {
            return FUTURE_DAY;
        }
        // same day
        return CURRENT_DAY;
    }

    public static boolean isWeekend(Calendar date) {
        if (date == null) {
            return false;
        }
        return date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }
}

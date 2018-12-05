package com.example.qa.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

  public static String FORMAT_YYYYMMDD = "yyyyMMdd";

  /** Convert a timestamp to string with the specified format* */
  public static String millisToDateStr(long millis, String format) {
    DateFormat dateTimeFormat = new SimpleDateFormat(format);
    String dateStr = dateTimeFormat.format(new Date(millis));
    return dateStr;
  }

  /** Parse the string representation of date that follows the specified format, returns a Date* */
  public static Date strToDate(String dateStr, String format) {
    DateFormat dateTimeFormat = new SimpleDateFormat(format);
    Date date = null;
    try {
      date = dateTimeFormat.parse(dateStr);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  /**
   * Parse the string representation of date that follows the specified format, returns a Calendar*
   */
  public static Calendar strToCalender(String dateStr, String format) {
    Date date = strToDate(dateStr, format);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal;
  }
}

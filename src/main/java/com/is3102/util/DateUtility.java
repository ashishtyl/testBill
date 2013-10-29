package com.is3102.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple date formatting utility class
 * @author Emre Simtay <emre@simtay.com>
 */

public class DateUtility {

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static String getCurrentDateTime() {
            DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            Date date = new Date();
            return dateFormat.format(date);
	}

    public static Date getStringFromDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.format(date);
        System.out.println(date.toString());
        return date;
    }
}
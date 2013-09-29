/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Swarit
 */

public class HandleDates {

    /**
     * @param args the command line arguments
     */
    
    public static String convertDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         //= new Date();
        String s_date="";
        StringBuilder dateR = new StringBuilder(dateFormat.format(date));
        s_date = dateR.toString();
        return s_date;
    }
    
     public static Date getDateFromString(String s) throws ParseException {
        //String s = null;
        Date dateValue;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateValue = sdf.parse(s);
        return dateValue;
    }
    
    public static String GetFirstDayOfMonth () {  
  
        Date today = new Date();  
  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(today);  
  
        calendar.set(Calendar.DAY_OF_MONTH, 1);  
  
        Date firstDayOfMonth = calendar.getTime();  
  
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder date_t = new StringBuilder(sdf.format(today));
        System.out.println("Today             : " + date_t.toString());
        StringBuilder date_f = new StringBuilder(sdf.format(firstDayOfMonth));
        System.out.println("First Day of Month: " + date_f.toString());
        return date_f.toString();
    }  
    
    public static String GetLastDayOfMonth () {  
  
        Date today = new Date();  
  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(today);  
  
        calendar.add(Calendar.MONTH, 1); 
        calendar.set(Calendar.DAY_OF_MONTH, 1); 
        calendar.add(Calendar.DATE, -1);
  
        Date lastDayOfMonth = calendar.getTime();  
  
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder date_t = new StringBuilder(sdf.format(today));
        System.out.println("Today             : " + date_t.toString());
        StringBuilder date_l = new StringBuilder(sdf.format(lastDayOfMonth));
        System.out.println("First Day of Month: " + date_l.toString());
        return date_l.toString();
    }
}

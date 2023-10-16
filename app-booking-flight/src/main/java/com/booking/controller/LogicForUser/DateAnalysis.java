package com.booking.controller.LogicForUser;

import java.util.Calendar;
public class DateAnalysis {
    public static int[] dateToInt(String input) {
        int separator1, separator2;
        separator1 = separator2 = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '/' || input.charAt(i) == '-') {
                if (separator1 != 0) {
                    separator2 = i;
                    break;
                }
                else separator1 = i;
            }
        }

        int[] date = new int[3];
        date[0] = Integer.parseInt(input.substring(0,separator1)); // Date
        date[1] = Integer.parseInt(input.substring(separator1+1,separator2)); // Month
        date[2] = Integer.parseInt(input.substring(separator2+1)); // Year
        return date;
    }
    public static int[] timeToInt(String input) {
        int separator = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ':' || input.charAt(i) == '.') {
                separator = i;
                break;
            }
        }
        int[] time = new int[2];
        time[0] = Integer.parseInt(input.substring(0,separator)); // Hour
        time[1] = Integer.parseInt(input.substring(separator+1)); // Minute
        return time;
    }
    public static Calendar dateToCal(String input) {
        int[] dateInt = dateToInt(input);
        Calendar dateCal = Calendar.getInstance();
        dateCal.set(dateInt[2],dateInt[1]-1,dateInt[0]);
        return dateCal;
    }
    public static Calendar datetimeToCal(String inputDate, String inputTime) {
        int[] dateInt = dateToInt(inputDate);
        int[] timeInt = timeToInt(inputTime);
        Calendar dateCal = Calendar.getInstance();
        dateCal.set(dateInt[2],dateInt[1]-1,dateInt[0],timeInt[0],timeInt[1]);
        return dateCal;
    }
}

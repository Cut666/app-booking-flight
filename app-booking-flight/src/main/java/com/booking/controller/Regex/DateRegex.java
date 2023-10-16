package com.booking.controller.Regex;

import com.booking.controller.LogicForUser.DateAnalysis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public class DateRegex {
    public static boolean dateRegex(String inputDate) {
        String regexDate = "(^[0-9]{1,2}/[0-9]{1,2}/[0-9]{2,4}$)|" +
                "(^[0-9]{1,2}-[0-9]{1,2}-[0-9]{2,4}$)";
		// accepts both dd/mm/yyyy and dd-mm-yyyy
        boolean valid = Pattern.matches(regexDate,inputDate);
        return !valid; // Match regex => false => while loop won't be activated
    }
    public static boolean realDate(String inputDate) {
        int[] analysedDate = DateAnalysis.dateToInt(inputDate);

        boolean bYear = analysedDate[2] >= 1970 && analysedDate[2] <= 2038;
        // Year range from 1970 to 2038

        boolean bMonth = analysedDate[1] >= 1 && analysedDate[1] <= 12;
        // Month range from 1 to 12

        boolean bDay;
        switch (analysedDate[1]) {
            case 1, 3, 5, 7, 8, 10, 12 -> bDay = analysedDate[0] >= 1 && analysedDate[0] <= 31;
            case 4, 6, 9, 11 -> bDay = analysedDate[0] >= 1 && analysedDate[0] <= 30;
            case 2 -> {
                if (analysedDate[2] % 4 == 0)
                    bDay = analysedDate[0] >= 1 && analysedDate[0] <= 29;
                else bDay = analysedDate[0] >= 1 && analysedDate[0] <= 28;
            }
            // Ignore years divisible by 100 but not by 400, since none within 1970-2038 is applicable
            default -> bDay = false;
        } // Day range from 1 to 28-31 depending on the month

        boolean valid = bYear && bMonth && bDay;
        return !valid;
    }
    public static boolean timeRegex(String inputTime) {
        String regexTime = "^[0-9]{1,2}(:|\\.)[0-9]{1,2}$";
        // accepts both hh:mm and hh.mm (only 24-hour format)
        boolean valid = Pattern.matches(regexTime,inputTime);
        return !valid; // Match regex => false => while loop won't be activated
    }
    public static boolean realTime(String inputTime) {
        int[] analysedTime = DateAnalysis.timeToInt(inputTime);

        boolean bHour = analysedTime[0] >= 0 && analysedTime[0] <= 23;
        // Hour range from 0 to 23

        boolean bMin = analysedTime[1] >= 0 && analysedTime[1] <= 59;
        // Minute range from 0 to 59

        boolean valid = bHour && bMin;
        return !valid;
    }

//    public  String getDate(long milliSeconds, String dateFormat)
//    {
//        // Create a DateFormatter object for displaying date in specified format.
//        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
//
//        // Create a calendar object that will convert the date and time value in milliseconds to date.
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(milliSeconds);
//        return formatter.format(calendar.getTime());
//    }

}

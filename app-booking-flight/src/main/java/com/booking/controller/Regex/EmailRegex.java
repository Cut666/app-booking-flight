package com.booking.controller.Regex;

import java.util.regex.Pattern;

public class EmailRegex {
    public static boolean emailRegex(String inputEmail) {
        String regexEmail = "^\\w+([+.-]\\w+)*@\\w+\\.(com(.vn)?|vn|net)$";
        boolean valid = Pattern.matches(regexEmail,inputEmail.trim());
        return !valid;
    }
}
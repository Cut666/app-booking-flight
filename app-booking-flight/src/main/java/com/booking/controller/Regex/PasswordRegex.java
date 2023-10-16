package com.booking.controller.Regex;

import java.util.regex.Pattern;

public class PasswordRegex {
    public static boolean passwordRegex(String inputPw) {
        String regexPw = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\p{Punct}).{8,}$";
        boolean valid = Pattern.matches(regexPw,inputPw);
        return !valid;
    }
}

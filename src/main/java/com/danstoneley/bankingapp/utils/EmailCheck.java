package com.danstoneley.bankingapp.utils;

import java.util.regex.Pattern;

public class EmailCheck {
    private static final Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w-]+\\.[A-Za-z]{2,}$");

    public static boolean emailChecker(String email) {
        if (email == null) return false;
        email = email.trim();
        return emailPattern.matcher(email).matches();
    }
}

package com.danstoneley.bankingapp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHash {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashPassword(String password) {
        return encoder.encode(password);
    }
    public static boolean checkHashedPassword(String password, String hashedPassword) {
        return encoder.matches(password, hashedPassword);
    }
}


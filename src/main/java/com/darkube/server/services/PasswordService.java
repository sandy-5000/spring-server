package com.darkube.server.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashPassword(String passwd, String username) {
        return encoder.encode(passwd + username);
    }

    public static boolean verifyPassword(String passwd, String username,
            String hashed) {
        return encoder.matches(passwd + username, hashed);
    }

}

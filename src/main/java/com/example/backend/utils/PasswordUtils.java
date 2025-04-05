package com.example.backend.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
//用于密码加密
@Component
public class PasswordUtils {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String encrypt(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}

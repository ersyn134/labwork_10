package com.example.labwork2;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin123"));   // Захешированный пароль
        System.out.println(encoder.encode("teacher123"));
        System.out.println(encoder.encode("student123"));
    }
}

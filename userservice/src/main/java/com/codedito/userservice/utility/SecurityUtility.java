package com.codedito.userservice.utility;


import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class SecurityUtility {
    private static final String SALT = "salt"; //Salt should be protected carefully

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    @Bean
    public static String randomPassword() {
        final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        final StringBuilder salt = new StringBuilder();
        final Random rnd = new Random();

        while (salt.length() < 18) {
            final int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        final String saltStr = salt.toString();

        return saltStr;

    }
}
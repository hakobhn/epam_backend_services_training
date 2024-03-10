package com.epam.training.backend_services.authdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.security.SecureRandom;

@Configuration
public class PasswordEncoderConfig {

    @Bean
    @Primary
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        int strength = 10;
        return new BCryptPasswordEncoder(strength, new SecureRandom());
    }

    @Bean
    public Pbkdf2PasswordEncoder pbkdf2PasswordEncoder() {
        String salt = "epam-salt"; // secret key used by password encoding
        int iterations = 200000;  // number of hash iteration
        int hashWidth = 256;

        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder(salt, iterations, hashWidth);
        pbkdf2PasswordEncoder.setEncodeHashAsBase64(true);
        return pbkdf2PasswordEncoder;
    }
}

package com.epam.training.backend_services.authdemo.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Hashing: Hashing is converting a plain text password into a fixed-length, irreversible hash value.
 * Spring Security supports several hashing algorithms, such as BCrypt, SCrypt, and PBKDF2.
 *
 * Salting: Salting is a technique that adds a random string of characters to the password
 * before hashing. This makes it more difficult for attackers to crack the hash because they
 * must first guess the salt value. Salting techniques to store passwords securely,
 * including basic salt, random salt, user-specific salt, time-based salt, and combined salt.
 */
@Slf4j
@SpringBootTest
class PasswordEncoderTest {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private Pbkdf2PasswordEncoder pbkdf2PasswordEncoder;

    @Test
    void shouldEncodeString_bCryptPasswordEncoder() {
        String result = bCryptPasswordEncoder.encode("myPassword");
        log.info("Password encoding result: {}", result);
        assertTrue(bCryptPasswordEncoder.matches("myPassword", result));
    }

    @Test
    void shouldEncodeString_pbkdf2PasswordEncoder () {
        String result = pbkdf2PasswordEncoder.encode("myPassword");
        log.info("Password encoding result: {}", result);
        assertTrue(pbkdf2PasswordEncoder.matches("myPassword", result));
    }

}

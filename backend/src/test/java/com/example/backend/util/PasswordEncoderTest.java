package com.example.backend.util;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordEncoderTest {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void encodedPassword_shouldMatchRawPassword() {
        String rawPassword = "testPassword123";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Check that the encoded password matches the raw password
        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword), "The encoded password should match the raw password.");
    }

    @Test
    void differentRawPasswords_shouldNotMatchSameHash() {
        String rawPassword1 = "testPassword123";
        String rawPassword2 = "anotherPassword456";

        // Encode both passwords
        String encodedPassword1 = passwordEncoder.encode(rawPassword1);
        String encodedPassword2 = passwordEncoder.encode(rawPassword2);

        // Check that the encoded passwords are not the same
        assertFalse(encodedPassword1.equals(encodedPassword2), "Different raw passwords should not produce the same hash.");
    }
}

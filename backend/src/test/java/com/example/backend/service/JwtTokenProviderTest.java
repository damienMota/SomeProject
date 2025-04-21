package com.example.backend.service;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() {
        jwtTokenProvider = new JwtTokenProvider();

        // manually set the secret key (since we're not using Spring injection here)
        String testSecret = "my-very-secret-and-secure-jwt-key-for-testing-12345";
        SecretKey key = Keys.hmacShaKeyFor(testSecret.getBytes());
        jwtTokenProvider.setSecretKey(key);
    }

    @Test
    void testGenerateToken_shouldReturnValidToken() {
        String token = jwtTokenProvider.generateToken("testuser");
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void testGetUsernameFromToken_shouldExtractCorrectUsername() {
        String username = "testuser";
        String token = jwtTokenProvider.generateToken(username);

        String extractedUsername = jwtTokenProvider.getUsernameFromToken(token);
        assertEquals(username, extractedUsername);
    }

    @Test
    void testValidateToken_shouldReturnTrueForValidToken() {
        String token = jwtTokenProvider.generateToken("validuser");
        assertTrue(jwtTokenProvider.validateToken(token));
    }

    @Test
    void testValidateToken_shouldReturnFalseForInvalidToken() {
        String invalidToken = "this.is.not.a.valid.token";
        assertFalse(jwtTokenProvider.validateToken(invalidToken));
    }

    @Test
    void testGetAuthentication() {
        String username = "testuser";
        String token = jwtTokenProvider.generateToken(username);

        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        assertNotNull(authentication);
        assertTrue(authentication instanceof UsernamePasswordAuthenticationToken);
        assertEquals(username, authentication.getName());
    }
}



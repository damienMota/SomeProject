package com.example.backend.controller;

import com.example.backend.config.SecurityTestConfig;
import com.example.backend.dto.LoginRequest;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc
@Import(SecurityTestConfig.class)  // Import the custom security configuration for testing
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void testLogin_shouldReturnJwtToken() throws Exception {
        String email = "test@example.com";
        String rawPassword = "password";
        String encodedPassword = "$2a$10$encoded";  // Example encoded password
        String token = "fake-jwt-token";

        // Create a mock user
        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);  // Pretend it's encoded
        user.setRole("USER");

        // Mocking UserRepository behavior
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Mocking AuthenticationManager behavior
        Authentication mockAuth = Mockito.mock(Authentication.class);
        Mockito.when(mockAuth.getName()).thenReturn(email);
        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(mockAuth);
        SecurityContextHolder.getContext().setAuthentication(mockAuth);

        // Mocking JWT Token generation
        Mockito.when(jwtTokenProvider.generateToken(email)).thenReturn(token);

        // Preparing the login request payload
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(rawPassword);

        // Convert to JSON string
        String requestBody = new ObjectMapper().writeValueAsString(loginRequest);

        // Perform the test and check the response
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())  // Check if status is OK
                .andExpect(jsonPath("$.token").value(token))  // Check if token is in the response
                .andExpect(jsonPath("$.role").value("USER"));  // Check if role is "USER"
    }
    @Test
    void login_shouldReturn401ForInvalidCredentials() throws Exception {
        String email = "test@example.com";
        String rawPassword = "wrongpassword";  // Invalid password
        String encodedPassword = "$2a$10$encoded";  // Example encoded password

        // Create a mock user with a valid password
        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);  // Pretend it's encoded
        user.setRole("USER");

        // Mocking UserRepository behavior
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Mocking AuthenticationManager behavior to fail authentication for invalid password
        Authentication mockAuth = Mockito.mock(Authentication.class);
        Mockito.when(mockAuth.getName()).thenReturn(email);
        Mockito.when(authenticationManager.authenticate(Mockito.any()))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        // Preparing the login request payload
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(rawPassword);

        // Convert to JSON string
        String requestBody = new ObjectMapper().writeValueAsString(loginRequest);

        // Perform the test and check the response
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnauthorized());  // Check if status is 401
    }

}

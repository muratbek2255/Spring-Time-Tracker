package com.example.userservice.service;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.example.userservice.User;
import com.example.userservice.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import io.jsonwebtoken.Claims;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class JWTServiceTest {

    @Mock
    private UserRepository userRepository;

    private JWTService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JWTService(userRepository);
    }

    @Test
    void testExtractUsername() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiJ9.cOx9XV0UJN6U_xg6MfIwOmpSPpPyYsCcJZKj2IkpAuc";
        String username = jwtService.extractUsername(token);
        assertEquals("admin", username);
    }

    @Test
    void testExtractClaim() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiJ9.cOx9XV0UJN6U_xg6MfIwOmpSPpPyYsCcJZKj2IkpAuc";
        String subject = jwtService.extractClaim(token, Claims::getSubject);
        assertEquals("admin", subject);
    }

//    @Test
//    void testGenerateToken() {
//        Date date = new Date();
//
//        User user = new User(1, "Loik", "Rustamov", "loik", Timestamp.from(Instant.now()), "+996707058235", "adsjkskj",
//                "bhsgajd", "loik@gmail.com", "idinahui12", date.getTime(), "MALE", "USER", Boolean.FALSE);
//        when(userRepository.findByMiddleName("admin")).thenReturn(user);
//
//        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                Collections.emptyList()
//        );
//
//        String token = jwtService.generateToken(userDetails);
//        assertNotNull(token);
//    }

//    @Test
//    void testIsTokenValid() {
//        Date date = new Date();
//        User user = new User(1, "Loik", "Rustamov", "loik", Timestamp.from(Instant.now()), "+996707058235", "adsjkskj",
//                "bhsgajd", "loik@gmail.com", "idinahui12", Date.d, "MALE", "USER", Boolean.FALSE);
//        when(userRepository.findByMiddleName("admin")).thenReturn(user);
//
//        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                Collections.emptyList()
//        );
//
//        String token = jwtService.generateToken(userDetails);
//
//        boolean isValid = jwtService.isTokenValid(token, userDetails);
//        assertTrue(isValid);
//    }

    @Test
    void testIsTokenValidWithNullUserDetails() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiJ9.cOx9XV0UJN6U_xg6MfIwOmpSPpPyYsCcJZKj2IkpAuc";
        assertThrows(UsernameNotFoundException.class, () -> jwtService.isTokenValid(token, null));
    }

    @Test
    void testIsTokenExpired() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiJ9.cOx9XV0UJN6U_xg6MfIwOmpSPpPyYsCcJZKj2IkpAuc";
        boolean isExpired = jwtService.isTokenExpired(token);
        assertFalse(isExpired);
    }
}

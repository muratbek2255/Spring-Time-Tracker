package com.example.userservice.service;


import brave.Span;
import brave.Tracer;
import com.example.userservice.*;
import com.example.userservice.config.WebClientConfig;
import com.example.userservice.dto.AuthenticationRequest;
import com.example.userservice.dto.AuthenticationResponse;
import com.example.userservice.dto.ConformationTokenRequest;
import com.example.userservice.dto.RegistrationRequest;
import com.example.userservice.enumClass.ROLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.time.Instant;


@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    private final WebClientConfig webClient;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                 JWTService jwtService, AuthenticationManager authenticationManager,
                                 WebClientConfig webClientConfig) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.webClient = webClientConfig;
    }

    public AuthenticationResponse registration(RegistrationRequest registerRequest) {

        User user = new User();

        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setMiddleName(registerRequest.getMiddleName());
        user.setFirstTimeLogin(Timestamp.from(Instant.now()));
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setAvatar(registerRequest.getAvatar());
        user.setBio(registerRequest.getBio());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setDateBirthday(registerRequest.getDateBirthday());
        user.setGender(registerRequest.getGender());
        user.setRole(ROLE.USER);

        userRepository.save(user);

        ConformationTokenRequest conformationTokenRequest = webClient.webClient().build().
                post().uri("http://confirmation-token/api/v1/token", uriBuilder -> uriBuilder
                        .queryParam("userId", user.getId())
                        .queryParam("email", registerRequest.getEmail())
                        .build())
                .retrieve()
                .bodyToMono(ConformationTokenRequest.class).block();

        var jwtToken = jwtService.generateToken(user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(
                jwtToken
        );

        return authenticationResponse;
    }

    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));

        var user = userRepository.findByMiddleName(authenticationRequest.getUsername());

        var jwtToken = jwtService.generateToken(user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtToken);

        return authenticationResponse;
    }
}

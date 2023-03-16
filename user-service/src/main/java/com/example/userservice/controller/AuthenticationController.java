package com.example.userservice.controller;


import com.example.userservice.dto.*;
import com.example.userservice.service.AuthenticationService;
import com.example.userservice.service.JWTService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    private final JWTService jwtService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, JWTService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    @CircuitBreaker(name = "confirmation-token", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "confirmation-token")
    @Retry(name = "confirmation-token")
    public CompletableFuture<ResponseEntity<AuthenticationResponse>> register(@RequestBody RegistrationRequest
                                                                   registerRequest){
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(authenticationService.registration(registerRequest)));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest
                                                                   authenticationRequest){
        return ResponseEntity.ok(authenticationService.authentication(authenticationRequest));
    }

    @PostMapping("/parse")
    public ResponseEntity<?> getSomeSensitiveData(@RequestBody JsonParseRequestDto parseRequestDto) {

        try {
            JwtParseResponseDto jwtParseResponseDto = jwtService.parseJwt(parseRequestDto.getToken());
            return new ResponseEntity<>(jwtParseResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("JWT parsing error: {}, token: {}", e.getLocalizedMessage(), parseRequestDto);
            e.printStackTrace();

            return new ResponseEntity<>(new ErrorDto(e.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    public CompletableFuture<String> fallbackMethod(RegistrationRequest registrationRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please order after some time!");
    }
}

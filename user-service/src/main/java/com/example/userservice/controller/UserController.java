package com.example.userservice.controller;


import com.example.userservice.User;
import com.example.userservice.dto.RegistrationRequest;
import com.example.userservice.dto.UserOrganizationRequest;
import com.example.userservice.service.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getByIdUser(@PathVariable int id) {

        return ResponseEntity.status(200).body(userService.getByIdUser(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody RegistrationRequest userRequest,
                                             @PathVariable int id) {

        return ResponseEntity.status(201).body(userService.updateUser(userRequest, id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {

        return ResponseEntity.status(202).body(userService.deleteUser(id));

    }

    @GetMapping("/byEmail")
    public ResponseEntity<User> getUserByEmail(@Param("email") String email) {

        return ResponseEntity.status(200).body(userService.getByEmailUser(email));

    }

    @PutMapping("/add-admin/{id}")
    @CircuitBreaker(name = "organization", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "organization")
    @Retry(name = "organization")
    public CompletableFuture<ResponseEntity<String>> updateUser(@PathVariable int id) {

        return CompletableFuture.supplyAsync(() -> ResponseEntity.status(201).body(userService.addAdmin(id)));

    }

    @PutMapping("/add-user-for-organization/{id}")
    @CircuitBreaker(name = "user-organization", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "user-organization")
    @Retry(name = "user-organization")
    public CompletableFuture<ResponseEntity<String>> addUser(@PathVariable int id,
                                                             @RequestBody UserOrganizationRequest userOrganizationRequest) {

        return CompletableFuture.supplyAsync(() -> ResponseEntity.status(201).body(userService.addUserForUserOrganization(id, userOrganizationRequest)));

    }

    public CompletableFuture<String> fallbackMethod(RegistrationRequest registrationRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please order after some time!");
    }
}

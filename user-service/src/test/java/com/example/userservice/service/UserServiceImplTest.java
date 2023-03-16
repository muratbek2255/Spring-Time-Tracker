package com.example.userservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.userservice.User;
import com.example.userservice.UserRepository;
import com.example.userservice.config.WebClientConfig;
import com.example.userservice.dto.OrganizationRequest;
import com.example.userservice.dto.RegistrationRequest;
import com.example.userservice.dto.UserOrganizationRequest;
import com.example.userservice.enumClass.ROLE;

@SpringBootTest
public class UserServiceImplTest {

    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private WebClientConfig webClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, webClient);
    }

    @Test
    public void testGetByIdUser() {
        User user = new User();
        user.setId(1);
        user.setEmail("john@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        when(userRepository.getById(1)).thenReturn(user);
        User result = userService.getByIdUser(1);
        Assertions.assertEquals(user.getEmail(), result.getEmail());
        Assertions.assertEquals(user.getFirstName(), result.getFirstName());
        Assertions.assertEquals(user.getLastName(), result.getLastName());
    }

    @Test
    public void testUpdateUser() {
        RegistrationRequest userRequest = new RegistrationRequest();
        userRequest.setFirstName("John");
        userRequest.setLastName("Doe");
        User user = new User();
        user.setId(1);
        user.setFirstName("Jane");
        user.setLastName("Doe");
        when(userRepository.getById(1)).thenReturn(user);
        String result = userService.updateUser(userRequest, 1);
        Assertions.assertEquals("User Updated", result);
        Assertions.assertEquals(userRequest.getFirstName(), user.getFirstName());
        Assertions.assertEquals(userRequest.getLastName(), user.getLastName());
        Assertions.assertNotNull(user.getFirstTimeLogin());
        Assertions.assertEquals(userRequest.getPhoneNumber(), user.getPhoneNumber());
        Assertions.assertEquals(userRequest.getAvatar(), user.getAvatar());
        Assertions.assertEquals(userRequest.getBio(), user.getBio());
        Assertions.assertEquals(userRequest.getEmail(), user.getEmail());
        Assertions.assertEquals(userRequest.getPassword(), user.getPassword());
        Assertions.assertEquals(userRequest.getDateBirthday(), user.getDateBirthday());
        Assertions.assertEquals(userRequest.getGender(), user.getGender());
    }

    @Test
    public void testDeleteUser() {
        String result = userService.deleteUser(1);
        Assertions.assertEquals("User deleted", result);
    }

    @Test
    public void testGetByEmailUser() {
        User user = new User();
        user.setId(1);
        user.setEmail("john@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        when(userRepository.findByEmail("john@example.com")).thenReturn(user);
        User result = userService.getByEmailUser("john@example.com");
        Assertions.assertEquals(user.getId(), result.getId());
        Assertions.assertEquals(user.getFirstName(), result.getFirstName());
        Assertions.assertEquals(user.getLastName(), result.getLastName());
    }
}
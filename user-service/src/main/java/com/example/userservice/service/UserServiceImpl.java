package com.example.userservice.service;


import com.example.userservice.config.WebClientConfig;
import com.example.userservice.dto.OrganizationRequest;
import com.example.userservice.dto.RegistrationRequest;
import com.example.userservice.User;
import com.example.userservice.UserRepository;
import com.example.userservice.dto.UserOrganizationRequest;
import com.example.userservice.enumClass.ROLE;
import com.example.userservice.event.MessagePlacedEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WebClientConfig webClient;

    private final KafkaTemplate<String, MessagePlacedEvent> kafkaTemplate;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, WebClientConfig webClient, KafkaTemplate<String, MessagePlacedEvent> kafkaTemplate) {
        this.userRepository = userRepository;
        this.webClient = webClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public User getByIdUser(int id) {

        User user = userRepository.getById(id);

        return user;

    }

    @Override
    public String updateUser(RegistrationRequest userRequest, int id) {

        User user = userRepository.getById(id);

        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setMiddleName(userRequest.getMiddleName());
        user.setFirstTimeLogin(Timestamp.from(Instant.now()));
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setAvatar(userRequest.getAvatar());
        user.setBio(userRequest.getBio());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setDateBirthday(userRequest.getDateBirthday());
        user.setGender(userRequest.getGender());

        userRepository.save(user);

        return "User Updated";
    }

    @Override
    public String deleteUser(int id) {

        userRepository.deleteById(id);

        return "User deleted";
    }

    @Override
    public User getByEmailUser(String email) {

        User user = userRepository.findByEmail(email);

        return user;

    }

    public String addAdmin(int id) {

        User user = userRepository.getById(id);

        user.setRole(ROLE.ADMIN);

        userRepository.save(user);
        OrganizationRequest organizationRequest = webClient.webClient().build().
                post().uri("http://organization-service/api/v1/organization", uriBuilder -> uriBuilder
                        .queryParam("firstName", user.getFirstName())
                        .queryParam("lastName", user.getLastName())
                        .build())
                .retrieve()
                .bodyToMono(OrganizationRequest.class).block();

        kafkaTemplate.send("notificationTopic", new MessagePlacedEvent(user.getFirstName()));

        return "Add admin";
    }

    public String addUserForUserOrganization(int id, UserOrganizationRequest userOrganizationRequest2) {

        User user = userRepository.getById(id);

        UserOrganizationRequest userOrganizationRequest = webClient.webClient().build().
                put().uri("http://user-organization-service/api/v1/user-organization/add-user/" + userOrganizationRequest2.getId(),
                        uriBuilder -> uriBuilder
                        .queryParam("firstName", user.getFirstName())
                        .queryParam("lastName", user.getLastName())
                        .build())
                .retrieve()
                .bodyToMono(UserOrganizationRequest.class).block();

        kafkaTemplate.send("notificationTopic", new MessagePlacedEvent(user.getLastName()));

        return "Send data for user-organization-service";
    }


}

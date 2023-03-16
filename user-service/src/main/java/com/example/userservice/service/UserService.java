package com.example.userservice.service;

import com.example.userservice.dto.RegistrationRequest;
import com.example.userservice.User;

public interface UserService {

    public User getByIdUser(int id);

    public String updateUser(RegistrationRequest userRequest, int id);

    public String deleteUser(int id);

    public User getByEmailUser(String email);
}

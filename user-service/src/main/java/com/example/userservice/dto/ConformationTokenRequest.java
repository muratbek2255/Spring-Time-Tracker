package com.example.userservice.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class ConformationTokenRequest {

    private Integer userId;

    private String email;
}

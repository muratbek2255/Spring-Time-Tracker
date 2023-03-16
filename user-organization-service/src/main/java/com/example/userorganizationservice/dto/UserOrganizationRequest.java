package com.example.userorganizationservice.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@RequiredArgsConstructor
public class UserOrganizationRequest {

    private String position;

    private WorkTimeRequest workTimeRequest;
}

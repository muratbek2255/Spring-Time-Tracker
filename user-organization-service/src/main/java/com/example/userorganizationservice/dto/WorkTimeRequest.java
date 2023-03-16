package com.example.userorganizationservice.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class WorkTimeRequest {

    private Integer id;

    private String startTime;

    private String endTime;
}

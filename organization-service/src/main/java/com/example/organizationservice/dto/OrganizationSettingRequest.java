package com.example.organizationservice.dto;


import com.example.organizationservice.entity.Organization;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
public class OrganizationSettingRequest {

    private Organization organization;

    private Timestamp startTime;

    private Timestamp endTime;

    private Integer nonFinedMinute;

    private String latitude;

    private String longitude;

    private String qrCode;
}

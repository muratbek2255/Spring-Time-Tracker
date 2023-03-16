package com.example.organizationservice.dto;


import com.example.organizationservice.entity.Organization;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class OrganizationAddParentDto {

    private Organization organization;
}

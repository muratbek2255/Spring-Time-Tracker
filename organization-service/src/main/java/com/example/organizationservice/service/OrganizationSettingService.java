package com.example.organizationservice.service;

import com.example.organizationservice.dto.OrganizationSettingRequest;
import com.example.organizationservice.entity.Organization;
import com.example.organizationservice.entity.OrganizationSetting;

public interface OrganizationSettingService {

    public String addOrganizationSetting(OrganizationSettingRequest organizationSettingRequest);

    public String updateOrganizationSetting(OrganizationSettingRequest organizationSettingRequest, int id);

    public String deleteOrganizationSetting(int id);

    public OrganizationSetting getByIdOrganizationSetting(int id);

    public OrganizationSetting getOrganizationSettingByOrganization(int id);
}

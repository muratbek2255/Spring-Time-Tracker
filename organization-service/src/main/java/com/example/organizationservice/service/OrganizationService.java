package com.example.organizationservice.service;


import com.example.organizationservice.dto.OrganizationUpdateRequest;
import com.example.organizationservice.entity.Organization;

public interface OrganizationService {

    public String addOrganization(String firstName, String lastName);

    public Organization getByIdOrganization(int id);

    public Organization getByParentId(int parentId);

    public Organization getCheckChild(int id);

    public Organization getExistsByIsDepartment();

    public String deleteOrganization(int id);

    public String updateOrganization(OrganizationUpdateRequest organizationUpdateRequest, int id);
}

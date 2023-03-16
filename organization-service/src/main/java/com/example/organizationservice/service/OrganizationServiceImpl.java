package com.example.organizationservice.service;


import com.example.organizationservice.WebClientConfig;
import com.example.organizationservice.dto.OrganizationAddParentDto;
import com.example.organizationservice.dto.OrganizationUpdateRequest;
import com.example.organizationservice.dto.UserOrganizationRequest;
import com.example.organizationservice.entity.Organization;
import com.example.organizationservice.repository.OrganizationRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    private final WebClientConfig webClient;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository, WebClientConfig webClient) {
        this.organizationRepository = organizationRepository;
        this.webClient = webClient;
    }


    @SneakyThrows
    @Override
    public String addOrganization(String firstName, String lastName) {

        Organization organization = new Organization();

        organization.setIsDepartment(Boolean.FALSE);
        organization.setAdminName(firstName);
        organization.setAdminLastname(lastName);

        organizationRepository.save(organization);

        return "Add Organization";
    }

    @Override
    public Organization getByIdOrganization(int id) {
        Organization organization = organizationRepository.getById(id);

        return organization;
    }

    @Override
    public Organization getByParentId(int parentId) {

        Organization organization = organizationRepository.getByParentId(parentId);

        return organization;
    }

    @Override
    public Organization getCheckChild(int id) {

        Organization organization = organizationRepository.getCheckChild(id);

        return organization;
    }

    @Override
    public Organization getExistsByIsDepartment() {

        Organization organization = organizationRepository.getExistsByIsDepartment();

        return organization;
    }

    @Override
    public String deleteOrganization(int id) {

        organizationRepository.deleteById(id);

        return "Delete organization";
    }

    @Override
    public String updateOrganization(OrganizationUpdateRequest organizationUpdateRequest, int id) {

        Organization organization = new Organization();

        organization.setIsDepartment(Boolean.TRUE);
        organization.setName(organizationUpdateRequest.getName());

        organizationRepository.save(organization);

        return "Add Organization";
    }

    public void updateParentCategory(OrganizationAddParentDto organizationAddParentDto, int id) {

        Organization organization = organizationRepository.getById(id);

        Organization organization1 = getByIdOrganization(organizationAddParentDto.getOrganization().getId());

        organization.setParent(organization1);

        organizationRepository.save(organization);
    }

    public void removeParentCategory(int id) {

        Organization organization = organizationRepository.getById(id);

        organization.setParent(null);

        organizationRepository.save(organization);

    }

    public String addOrganizationForUserOrganization(int id, UserOrganizationRequest userOrganizationRequest2) {

        Organization organization = organizationRepository.getById(id);

        UserOrganizationRequest userOrganizationRequest = webClient.webClient().build().
                put().uri("http://user-organization-service/api/v1/user-organization/add-organization/" + userOrganizationRequest2.getId(),
                        uriBuilder -> uriBuilder
                                .queryParam("organizationName", organization.getName())
                                .build())
                .retrieve()
                .bodyToMono(UserOrganizationRequest.class).block();

        return "Send data for user-organization-service";
    }
}

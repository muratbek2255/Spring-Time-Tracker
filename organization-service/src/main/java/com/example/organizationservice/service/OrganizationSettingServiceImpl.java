package com.example.organizationservice.service;

import com.example.organizationservice.dto.OrganizationSettingRequest;
import com.example.organizationservice.entity.Organization;
import com.example.organizationservice.entity.OrganizationSetting;
import com.example.organizationservice.repository.OrganizationSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrganizationSettingServiceImpl implements OrganizationSettingService {

    private final OrganizationSettingRepository organizationSettingRepository;

    private final OrganizationServiceImpl organizationService;

    @Autowired
    public OrganizationSettingServiceImpl(OrganizationSettingRepository organizationSettingRepository,
                                          OrganizationServiceImpl organizationService) {
        this.organizationSettingRepository = organizationSettingRepository;
        this.organizationService = organizationService;
    }


    @Override
    public String addOrganizationSetting(OrganizationSettingRequest organizationSettingRequest) {
        OrganizationSetting organizationSetting = new OrganizationSetting();

        Organization organization = organizationService.getByIdOrganization(
                organizationSettingRequest.getOrganization().getId());

        organizationSetting.setOrganizationId(organization);
        organizationSetting.setStartTime(organizationSettingRequest.getStartTime());
        organizationSetting.setEndTime(organizationSettingRequest.getEndTime());
        organizationSetting.setNonFinedMinute(organizationSettingRequest.getNonFinedMinute());
        organizationSetting.setLatitude(organizationSettingRequest.getLatitude());
        organizationSetting.setLongitude(organizationSettingRequest.getLongitude());
        organizationSetting.setQrCode(organizationSettingRequest.getQrCode());

        organizationSettingRepository.save(organizationSetting);

        return "Create Organization Setting";
    }

    @Override
    public String updateOrganizationSetting(OrganizationSettingRequest organizationSettingRequest, int id) {

        OrganizationSetting organizationSetting = organizationSettingRepository.getById(id);

        Organization organization1 = organizationService.getByIdOrganization(
                organizationSettingRequest.getOrganization().getId());

        organizationSetting.setOrganizationId(organization1);
        organizationSetting.setStartTime(organizationSettingRequest.getStartTime());
        organizationSetting.setEndTime(organizationSettingRequest.getEndTime());
        organizationSetting.setNonFinedMinute(organizationSettingRequest.getNonFinedMinute());
        organizationSetting.setLatitude(organizationSettingRequest.getLatitude());
        organizationSetting.setLongitude(organizationSettingRequest.getLongitude());
        organizationSetting.setQrCode(organizationSettingRequest.getQrCode());

        organizationSettingRepository.save(organizationSetting);

        return "Update Organization Setting";
    }

    @Override
    public String deleteOrganizationSetting(int id) {

        organizationSettingRepository.deleteById(id);

        return "Delete Organization Setting";
    }

    @Override
    public OrganizationSetting getByIdOrganizationSetting(int id) {

        OrganizationSetting organizationSetting = organizationSettingRepository.getById(id);

        return organizationSetting;
    }

    @Override
    public OrganizationSetting getOrganizationSettingByOrganization(int id) {

        OrganizationSetting organizationSetting = organizationSettingRepository.getByOrganizationId(id);

        return organizationSetting;
    }
}

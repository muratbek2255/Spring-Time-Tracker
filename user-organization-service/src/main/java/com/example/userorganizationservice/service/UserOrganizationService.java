package com.example.userorganizationservice.service;


import com.example.userorganizationservice.dto.UserOrganizationRequest;
import com.example.userorganizationservice.dto.UserQrCodeRequest;

public interface UserOrganizationService {

    public String addUserOrganization(UserOrganizationRequest userOrganizationRequest);

    public String addOrganizationForUserOrganization(String organizationName, int id);

    public String updateAndAddUserForUserOrganization(String firstName, String lastName, int id);

    public String addQrCode(UserQrCodeRequest userQrCodeRequest, int id);

    public String checkAndSaveWorkTime(String qrCode, int id);

    public String checkAndSaveEndTimeWork(String qrCode, int id);
}

package com.example.userorganizationservice.service;


import com.example.userorganizationservice.dto.UserOrganizationRequest;
import com.example.userorganizationservice.dto.UserQrCodeRequest;
import com.example.userorganizationservice.entity.UserOrganization;
import com.example.userorganizationservice.entity.WorkTime;
import com.example.userorganizationservice.repository.UserOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


@Service
public class UserOrganizationServiceImpl implements UserOrganizationService {

    private final UserOrganizationRepository userOrganizationRepository;
    private final WorkTimeServiceImpl workTimeService;

    private Locale locale = new Locale("kk","KZ");
    private DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);

    @Autowired
    public UserOrganizationServiceImpl(UserOrganizationRepository userOrganizationRepository, WorkTimeServiceImpl workTimeService) {
        this.userOrganizationRepository = userOrganizationRepository;
        this.workTimeService = workTimeService;
    }


    public UserOrganization getByIdOrganization(int id) {

        UserOrganization userOrganization = userOrganizationRepository.getById(id);

        return userOrganization;
    }

    @Override
    public String addUserOrganization(UserOrganizationRequest userOrganizationRequest) {

        UserOrganization userOrganization = new UserOrganization();

        WorkTime workTime = workTimeService.getById(userOrganizationRequest.getWorkTimeRequest().getId());

        userOrganization.setPosition(userOrganizationRequest.getPosition());
        userOrganization.setWorkTime(workTime);

        userOrganizationRepository.save(userOrganization);

        return "Add User Organization";
    }

    @Override
    public String addOrganizationForUserOrganization(String organizationName, int id) {

        UserOrganization userOrganization = userOrganizationRepository.getById(id);

        userOrganization.setOrganizationName(organizationName);

        userOrganizationRepository.save(userOrganization);

        return "Add Organization For User Organization";
    }

    @Override
    public String updateAndAddUserForUserOrganization(String firstName, String lastName, int id) {

        UserOrganization userOrganization = userOrganizationRepository.getById(id);

        userOrganization.setUserFirstName(firstName);
        userOrganization.setUserLastName(lastName);

        userOrganizationRepository.save(userOrganization);

        return "Add User For User Organization";
    }

    @Override
    public String addQrCode(UserQrCodeRequest userQrCodeRequest, int id) {

        UserOrganization userOrganization = userOrganizationRepository.getById(id);

        userOrganization.setQrCode(userQrCodeRequest.getQrCode());

        userOrganizationRepository.save(userOrganization);

        return "Add Qr Code";
    }

    @Override
    public String checkAndSaveWorkTime(String qrCode, int id) {

        UserOrganization userOrganization = userOrganizationRepository.getById(id);

        if (!userOrganization.getQrCode().equals(qrCode)) {
            return "Not this is qr Code";
        }

        userOrganization.setIsChecked(Boolean.TRUE);

        if(userOrganization.getIsChecked().equals(Boolean.TRUE)) {

            userOrganization.setStartTime(dateFormat.format(new Date()));

            if(!userOrganization.getStartTime().equals(userOrganization.getWorkTime().getStartTime())) {

                userOrganization.getWorkTime().setIsLate(Boolean.TRUE);
                userOrganization.setNonFinedMinute(userOrganization.getNonFinedMinute() + 10);
            }
        }

        userOrganizationRepository.save(userOrganization);
        return "User Organization checked";
    }

    @Override
    public String checkAndSaveEndTimeWork(String qrCode, int id) {

        UserOrganization userOrganization = userOrganizationRepository.getById(id);

        if(qrCode.equals(userOrganization.getQrCode())) {
            userOrganization.setEndTime(dateFormat.format(new Date()));
            userOrganization.setIsChecked(Boolean.FALSE);
            userOrganization.getWorkTime().setIsLate(Boolean.FALSE);
        }

        userOrganizationRepository.save(userOrganization);

        return "User Organization Full Checked";
    }


}

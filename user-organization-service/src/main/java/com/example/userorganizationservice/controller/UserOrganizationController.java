package com.example.userorganizationservice.controller;


import com.example.userorganizationservice.dto.UserOrganizationRequest;
import com.example.userorganizationservice.dto.UserQrCodeRequest;
import com.example.userorganizationservice.entity.UserOrganization;
import com.example.userorganizationservice.service.UserOrganizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user-organization")
public class UserOrganizationController {

    private final UserOrganizationServiceImpl userOrganizationService;

    @Autowired
    public UserOrganizationController(UserOrganizationServiceImpl userOrganizationService) {
        this.userOrganizationService = userOrganizationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOrganization> getByIdUserOrganization(@PathVariable int id) {

        return ResponseEntity.status(200).body(userOrganizationService.getByIdOrganization(id));
    }

    @PostMapping
    public ResponseEntity<String> addUserOrganization(@RequestBody UserOrganizationRequest userOrganizationRequest) {

        return ResponseEntity.status(201).body(userOrganizationService.addUserOrganization(userOrganizationRequest));
    }

    @PutMapping("/add-organization/{id}")
    public ResponseEntity<String> addOrganizationForUserOrganization(@Param("organizationName") String organizationName,
                                                                     @PathVariable int id) {

        return ResponseEntity.status(201).body(userOrganizationService.addOrganizationForUserOrganization(organizationName, id));
    }

    @PutMapping("/add-user/{id}")
    public ResponseEntity<String> addUserForUserOrganization(@Param("firstName") String firstName,
                                                             @Param("lastName") String lastName,
                                                             @PathVariable int id) {

        return ResponseEntity.status(201).body(userOrganizationService.updateAndAddUserForUserOrganization(firstName, lastName, id));
    }

    @PutMapping("/add-qr-code/{id}")
    public ResponseEntity<String> addQrCode(@RequestBody UserQrCodeRequest userQrCodeRequest,
                                            @PathVariable int id) {

        return ResponseEntity.status(201).body(userOrganizationService.addQrCode(userQrCodeRequest, id));
    }

    @PutMapping("/save-start-time/{id}")
    public ResponseEntity<String> checkAndSaveWorkTimeById(@Param("qrCode") String qrCode,
                                                           @PathVariable int id) {

        return ResponseEntity.status(201).body(userOrganizationService.checkAndSaveWorkTime(qrCode, id));
    }

    @PutMapping("/save-end-time/{id}")
    public ResponseEntity<String> checkAndSaveEndTimeWorkById(@Param("qrCode") String qrCode,
                                                              @PathVariable int id) {

        return ResponseEntity.status(201).body(userOrganizationService.checkAndSaveEndTimeWork(qrCode, id));
    }
}

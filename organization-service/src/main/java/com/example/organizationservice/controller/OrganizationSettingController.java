package com.example.organizationservice.controller;


import com.example.organizationservice.dto.OrganizationSettingRequest;
import com.example.organizationservice.entity.OrganizationSetting;
import com.example.organizationservice.service.OrganizationSettingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/organization-setting")
public class OrganizationSettingController {

    private final OrganizationSettingServiceImpl organizationSettingService;

    @Autowired
    public OrganizationSettingController(OrganizationSettingServiceImpl organizationSettingService) {
        this.organizationSettingService = organizationSettingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationSetting> getByIdOrganizationSetting(@PathVariable int id) {

        return ResponseEntity.status(200).body(organizationSettingService.getByIdOrganizationSetting(id));
    }

    @GetMapping("/organization/{id}")
    public ResponseEntity<OrganizationSetting> getOrganizationSettingByOrganization(@PathVariable int id) {

        return ResponseEntity.status(200).body(organizationSettingService.getOrganizationSettingByOrganization(id));
    }

    @PostMapping
    public ResponseEntity<String> addOrganizationSetting(@RequestBody OrganizationSettingRequest organizationSettingRequest) {

        return ResponseEntity.status(201).body(organizationSettingService.addOrganizationSetting(organizationSettingRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrganizationSetting(@RequestBody OrganizationSettingRequest organizationSettingRequest,
                                                            @PathVariable int id) {

        return ResponseEntity.status(201).body(organizationSettingService.updateOrganizationSetting(organizationSettingRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrganizationSetting(@PathVariable int id) {

        return ResponseEntity.status(202).body(organizationSettingService.deleteOrganizationSetting(id));
    }
}

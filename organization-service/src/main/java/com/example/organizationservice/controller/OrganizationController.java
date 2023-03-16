package com.example.organizationservice.controller;


import com.example.organizationservice.dto.UserOrganizationRequest;
import com.example.organizationservice.service.OrganizationServiceImpl;
import com.example.organizationservice.dto.OrganizationAddParentDto;
import com.example.organizationservice.dto.OrganizationUpdateRequest;
import com.example.organizationservice.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private final OrganizationServiceImpl organizationService;

    @Autowired
    public OrganizationController(OrganizationServiceImpl organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<String> addOrganization(@Param("firstName") String firstName,
                                                  @Param("lastName") String lastName) {

        return ResponseEntity.status(201).body(organizationService.addOrganization(firstName, lastName));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Organization> getByIdOrganization(@PathVariable int id) {

        return ResponseEntity.status(200).body(organizationService.getByIdOrganization(id));
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<Organization> getByParentId(@PathVariable int parentId) {

        return ResponseEntity.status(200).body(organizationService.getByParentId(parentId));
    }

    @GetMapping("/check-child/{id}")
    public ResponseEntity<Organization> getCheckChild(@PathVariable int id) {

        return ResponseEntity.status(200).body(organizationService.getCheckChild(id));
    }

    @GetMapping("/exists-is-department")
    public ResponseEntity<Organization> getExistsByIsDepartment() {

        return ResponseEntity.status(200).body(organizationService.getExistsByIsDepartment());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrganization(int id) {

        return ResponseEntity.status(202).body(organizationService.deleteOrganization(id));
    }

    @PutMapping("/update-parent-in-organization/{id}")
    public void updateParentCategory(@RequestBody OrganizationAddParentDto organizationAddParentDto,
                                     @PathVariable int id) {

        organizationService.updateParentCategory(organizationAddParentDto, id);
    }

    @DeleteMapping("/delete-parent-in-organization/{id}")
    public void deleteParentCategory(@PathVariable int id) {

        organizationService.removeParentCategory(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrganization(@RequestBody OrganizationUpdateRequest organizationUpdateRequest,
                                                     @PathVariable int id) {

        return ResponseEntity.status(201).body(organizationService.updateOrganization(organizationUpdateRequest, id));
    }

    @PutMapping("/add-organization-for-user-organization/{id}")
    public ResponseEntity<String> addOrganizationForUserOrganization(@RequestBody UserOrganizationRequest userOrganizationRequest,
                                                                     @PathVariable int id) {

        return ResponseEntity.status(201).body(organizationService.addOrganizationForUserOrganization(id, userOrganizationRequest));
    }
}

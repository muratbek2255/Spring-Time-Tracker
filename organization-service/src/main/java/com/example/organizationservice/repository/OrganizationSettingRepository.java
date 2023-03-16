package com.example.organizationservice.repository;

import com.example.organizationservice.entity.OrganizationSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrganizationSettingRepository extends JpaRepository<OrganizationSetting, Integer> {

    @Query(value = "SELECT * FROM organization_settings WHERE organization_settings.id = ?1", nativeQuery = true)
    OrganizationSetting getById(int id);

    @Query(value = "SELECT id FROM organization_settings WHERE organization_settings.organization_id = ?1", nativeQuery = true)
    OrganizationSetting getByOrganizationId(@Param("id") int id);
}

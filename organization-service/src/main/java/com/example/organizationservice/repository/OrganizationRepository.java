package com.example.organizationservice.repository;

import com.example.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

    @Query(value = "SELECT * FROM organizations WHERE organizations.id = ?1", nativeQuery = true)
    Organization getById(@Param("id") int id);

    @Query(value = "SELECT id from organizations WHERE parent_id=?1", nativeQuery = true)
    Organization getByParentId(@Param("parentId") int parentId);

    @Query(value = "SELECT * FROM organizations WHERE organizations.parent_id IS NOT NULL",
            nativeQuery = true)
    Organization getCheckChild(@Param("id") int id);

    @Query(value = "SELECT * FROM organizations WHERE organizations.is_department=TRUE",
            nativeQuery = true)
    Organization getExistsByIsDepartment();
}

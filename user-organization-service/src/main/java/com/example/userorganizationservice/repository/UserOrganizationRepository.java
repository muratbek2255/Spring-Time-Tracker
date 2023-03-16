package com.example.userorganizationservice.repository;

import com.example.userorganizationservice.entity.UserOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserOrganizationRepository extends JpaRepository<UserOrganization, Integer> {

    @Query(value = "SELECT * FROM user_organizations WHERE user_organizations.id = ?1", nativeQuery = true)
    UserOrganization getById(@Param("id") int id);
}

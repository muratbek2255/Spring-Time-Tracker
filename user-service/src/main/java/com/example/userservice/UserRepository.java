package com.example.userservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    User findByMiddleName(String middleName);

    @Query(value = "SELECT * FROM users WHERE users.id = ?1", nativeQuery = true)
    User getById(@Param("id") int id);

    @Query(value = "SELECT * FROM users WHERE users.role = ?1", nativeQuery = true)
    User getByRole(String role);
}

package com.example.userorganizationservice.repository;

import com.example.userorganizationservice.entity.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface WorkTimeRepository extends JpaRepository<WorkTime, Integer> {
    @Query(value = "SELECT * FROM work_times WHERE work_times.id = ?1", nativeQuery = true)
    WorkTime getById(@Param("id") int id);
}

package com.example.userorganizationservice.controller;


import com.example.userorganizationservice.dto.WorkTimeRequest;
import com.example.userorganizationservice.entity.WorkTime;
import com.example.userorganizationservice.service.WorkTimeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/work-time")
public class WorkTimeController {

    private final WorkTimeServiceImpl workTimeService;

    @Autowired
    public WorkTimeController(WorkTimeServiceImpl workTimeService) {
        this.workTimeService = workTimeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkTime> getByIdWorkTime(@PathVariable int id) {

        return ResponseEntity.status(200).body(workTimeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<String> addWorkTime(@RequestBody WorkTimeRequest workTimeRequest) {

        return ResponseEntity.status(201).body(workTimeService.addWorkTime(workTimeRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkTime(@PathVariable int id) {

        return ResponseEntity.status(202).body(workTimeService.deleteWorkTime(id));
    }
}

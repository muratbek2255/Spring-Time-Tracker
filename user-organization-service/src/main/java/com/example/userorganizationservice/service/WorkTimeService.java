package com.example.userorganizationservice.service;

import com.example.userorganizationservice.dto.WorkTimeRequest;
import com.example.userorganizationservice.entity.WorkTime;

import java.text.ParseException;

public interface WorkTimeService {

    public WorkTime getById(int id);

    public String addWorkTime(WorkTimeRequest workTimeRequest) throws ParseException;

    public String deleteWorkTime(int id);
}

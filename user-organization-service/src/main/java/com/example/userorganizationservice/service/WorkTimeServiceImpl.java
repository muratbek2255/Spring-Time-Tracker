package com.example.userorganizationservice.service;

import com.example.userorganizationservice.dto.WorkTimeRequest;
import com.example.userorganizationservice.entity.WorkTime;
import com.example.userorganizationservice.repository.WorkTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


@Service
public class WorkTimeServiceImpl implements WorkTimeService {

    private final WorkTimeRepository workTimeRepository;

//    private Locale locale = new Locale("kk","KZ");
//    private DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);

    private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    public WorkTimeServiceImpl(WorkTimeRepository workTimeRepository) {
        this.workTimeRepository = workTimeRepository;
    }

    @Override
    public WorkTime getById(int id) {

        WorkTime workTime = workTimeRepository.getById(id);

        return workTime;
    }

    @Override
    public String addWorkTime(WorkTimeRequest workTimeRequest){

        WorkTime workTime = new WorkTime();

        workTime.setStartTime(workTimeRequest.getStartTime());
        workTime.setEndTime(workTimeRequest.getEndTime());
        workTime.setIsLate(Boolean.FALSE);

        workTimeRepository.save(workTime);

        return "Add Work Time";
    }

    @Override
    public String deleteWorkTime(int id) {

        workTimeRepository.deleteById(id);

        return "Delete Work Time";
    }
}

package com.example.userorganizationservice.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "work_times")
public class WorkTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //    Начало рабочего дня
    @Column(name = "start_time")
    private String startTime;

    //    Конец рабочего дня
    @Column(name = "end_time")
    private String endTime;

    //    Опоздал ли?
    @Column(name = "is_late")
    private Boolean isLate;
}

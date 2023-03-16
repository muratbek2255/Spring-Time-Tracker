package com.example.userorganizationservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "user_organizations")
//@JsonIgnoreProperties({"work_time"})
public class UserOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_first_name")
    private String userFirstName;

    @Column(name = "user_lastname")
    private String userLastName;

    @Column(name = "position")
    private String position;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "is_checked")
    private Boolean isChecked;

    //    Начало рабочего дня
    @Column(name = "start_time")
    private String startTime;

    //    Конец рабочего дня
    @Column(name = "end_time")
    private String endTime;

    //    Не штрафуемые минуты
    @Column(name = "non_fined_minute")
    private Integer nonFinedMinute=10;

    @Column(name = "qr_code")
    private String qrCode;

    @OneToOne
    @JoinColumn(name = "worktime_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private WorkTime workTime;
}

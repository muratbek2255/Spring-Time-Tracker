package com.example.organizationservice.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "organization_settings")
public class OrganizationSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    private Organization organizationId;

//    Начало рабочего дня
    @Column(name = "start_time")
    private Timestamp startTime;

//    Конец рабочего дня
    @Column(name = "end_time")
    private Timestamp endTime;

//    Не штрафуемые минуты
    @Column(name = "non_fined_minute")
    private Integer nonFinedMinute;

//    Широта
    @Column(name = "latitude")
    private String latitude;

//    Долгота
    @Column(name = "longitude")
    private String longitude;

    @Column(name = "qr_code")
    private String qrCode;
}

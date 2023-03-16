package com.example.userservice.dto;


import com.example.userservice.enumClass.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
public class RegistrationRequest {

    private Integer id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String phoneNumber;

    private String avatar;

    private String bio;

    private String email;

    private String password;

    private Date dateBirthday;

    private Gender gender;

}

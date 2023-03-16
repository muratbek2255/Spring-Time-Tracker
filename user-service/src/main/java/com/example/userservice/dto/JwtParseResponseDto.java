package com.example.userservice.dto;


import java.util.List;

public class JwtParseResponseDto {

    private String middleName;

    private List<String> authorities;

    public JwtParseResponseDto() {
    }

    public JwtParseResponseDto(String middleName, List<String> authorities) {
        this.middleName = middleName;
        this.authorities = authorities;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}

package com.example.apigateway.dto;


import java.util.List;

public class JwtParseResponseDto {

    private String middleName;

    private List<String> authorities;

    public JwtParseResponseDto() {
    }

    public JwtParseResponseDto(String middleName) {
        this.middleName = middleName;
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

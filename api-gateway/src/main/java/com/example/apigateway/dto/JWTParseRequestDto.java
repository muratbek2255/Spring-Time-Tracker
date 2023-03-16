package com.example.apigateway.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class JWTParseRequestDto {

    private String token;

    public JWTParseRequestDto(String token) {
    }
}

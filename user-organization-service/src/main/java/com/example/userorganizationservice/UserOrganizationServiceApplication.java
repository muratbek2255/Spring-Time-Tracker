package com.example.userorganizationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserOrganizationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserOrganizationServiceApplication.class, args);
    }

}

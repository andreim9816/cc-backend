package com.example.bankingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.domain"})
@EnableFeignClients
public class BankingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingApiApplication.class, args);
    }

}

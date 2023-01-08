package com.example.bankingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.domain"})
public class BankingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingApiApplication.class, args);
    }

}

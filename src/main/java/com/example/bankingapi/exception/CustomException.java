package com.example.bankingapi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends Exception {

    private String message;

    public CustomException(String message) {
        this.message = message;
    }
}

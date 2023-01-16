package com.example.bankingapi.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.bankingapi.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class Advice {

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<?> handleTokenNotVerified(JWTVerificationException ex, WebRequest request) {
        return ResponseEntity.badRequest()
                .header(HttpHeaders.SET_COOKIE, JwtService.generateEmptyCookie().toString())
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.badRequest()
                .body(Error.builder().errorMessage(ex.getMessage()).build());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return ResponseEntity.badRequest()
                .body(Error.builder().errorMessage(ex.getMessage()).build());
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static
    class Error {
        private String errorMessage;
    }

}

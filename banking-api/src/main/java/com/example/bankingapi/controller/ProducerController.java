package com.example.bankingapi.controller;

import com.example.bankingapi.service.ProducerService;
import com.example.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping("/produce")
    public ResponseEntity<User> sendMessage(@RequestBody User user) {
        producerService.sendMessage(user);
        log.info("user sent: " + user);
        return ResponseEntity.ok(user);
    }
}

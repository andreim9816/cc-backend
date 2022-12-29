package com.example.bankingapi.service;

import com.example.bankingapi.dto.request.RegisterDto;
import com.example.bankingapi.dto.response.UserDto;
import com.example.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class Mapper {

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public User toEntity(RegisterDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .bankAccounts(Collections.emptyList())
                .email(dto.getEmail())
                .firstname(dto.getFirstName())
                .lastname(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .password(dto.getPassword())
                .build();
    }

}

package com.example.bankingapi.service;

import com.example.bankingapi.domain.BankAccount;
import com.example.bankingapi.domain.User;
import com.example.bankingapi.dto.request.RegisterDto;
import com.example.bankingapi.dto.response.BankAccountDto;
import com.example.bankingapi.dto.response.UserDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class Mapper {

  public BankAccountDto toDto(BankAccount account) {
    return BankAccountDto.builder()
      .id(account.getId())
      .iban(account.getIban())
      .currency(account.getCurrency())
      .amount(account.getAmount())
      .userId(account.getUser().getId())
      .build();
  }

  public UserDto toDto(User user) {
    return UserDto.builder()
      .id(user.getId())
      .username(user.getUsername())
      .email(user.getEmail())
      .phoneNumber(user.getPhoneNumber())
      .bankAccounts(user.getBankAccounts().stream().map(this::toDto).collect(Collectors.toList()))
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

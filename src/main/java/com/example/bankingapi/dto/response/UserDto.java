package com.example.bankingapi.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String username;
    private String phoneNumber;
    private String email;
    private List<BankAccountDto> bankAccounts;
}

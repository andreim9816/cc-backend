package com.example.bankingapi.dto.response;

import com.example.domain.model.type.Currency;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankAccountDto {

    private Long id;
    private String iban;
    private Currency currency;
    private Double amount;
    private Long userId;
}

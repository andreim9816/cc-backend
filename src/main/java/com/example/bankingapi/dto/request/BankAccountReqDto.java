package com.example.bankingapi.dto.request;

import com.example.bankingapi.domain.type.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountReqDto {

    @NotNull
    private Currency currency;
}

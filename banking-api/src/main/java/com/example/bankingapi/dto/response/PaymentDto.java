package com.example.bankingapi.dto.response;

import com.example.domain.model.BankAccount;
import com.example.domain.model.type.Currency;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {

  private Double amount;
  private Currency currency;
  private String ibanTo;
  private String ibanFrom;
  private Timestamp timestamp;
}

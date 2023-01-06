package com.example.payment.dto.request;

import com.example.domain.model.type.Currency;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
public class PaymentReqDto {
  @NotBlank
  String ibanTo;
  @NotBlank
  String ibanFrom;
  @NotNull
  Currency currency;
  @NotNull
  Double amount;
  @NotNull
  Timestamp timestamp;
}

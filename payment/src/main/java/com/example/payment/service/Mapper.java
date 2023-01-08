package com.example.payment.service;

import com.example.domain.model.Payment;
import com.example.payment.dto.request.PaymentReqDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
  public PaymentReqDto toDto(Payment payment) {
    return PaymentReqDto.builder()
      .amount(payment.getAmount())
      .currency(payment.getCurrency())
      .ibanTo(payment.getIbanTo())
      .ibanFrom(payment.getIbanFrom()).timestamp(payment.getTimestamp())
      .build();
  }
}

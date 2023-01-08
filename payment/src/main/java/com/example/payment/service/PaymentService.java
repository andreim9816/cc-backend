package com.example.payment.service;

import com.example.domain.model.Payment;
import com.example.payment.dto.request.PaymentReqDto;
import com.example.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class PaymentService {
  private final PaymentRepository paymentRepository;


  public Payment create(PaymentReqDto dto, String ibanTo, String ibanFrom) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    Payment newPayment = Payment.builder()
      .ibanTo(ibanTo)
      .ibanFrom(ibanFrom)
      .amount(dto.getAmount())
      .currency(dto.getCurrency())
      .timestamp(timestamp)
      .build();

    return paymentRepository.save(newPayment);
  }
}

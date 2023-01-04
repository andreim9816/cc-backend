package com.example.payment.service;

import com.example.domain.model.BankAccount;
import com.example.domain.model.Payment;
import com.example.payment.dto.request.PaymentReqDto;
import com.example.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
  private final PaymentRepository paymentRepository;


  public Payment create(PaymentReqDto dto, BankAccount bankAccountTo, BankAccount bankAccountFrom) {
    Payment newPayment = Payment.builder()
      .bankAccountTo(bankAccountTo)
      .bankAccountFrom(bankAccountFrom)
      .amount(dto.getAmount())
      .currency(dto.getCurrency())
      .build();

    return paymentRepository.save(newPayment);
  }
}

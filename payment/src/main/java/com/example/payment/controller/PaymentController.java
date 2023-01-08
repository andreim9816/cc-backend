package com.example.payment.controller;

import com.example.payment.dto.request.PaymentReqDto;
import com.example.payment.service.Mapper;
import com.example.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

  private final PaymentService paymentService;
  private final Mapper mapper;

  @GetMapping("/{iban}")
  List<PaymentReqDto> getPayments(@PathVariable String iban) {
    return paymentService.getPayments(iban).stream().map(payment -> mapper.toDto(payment)).collect(Collectors.toList());
  }

}

package com.example.bankingapi.service;

import com.example.bankingapi.dto.response.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "payment-service.default.svc.cluster.local", url = "http://localhost:8082/api")
public interface PaymentServiceProxy {

  @GetMapping("/payments/{iban}")
  List<PaymentDto> getPayments(@PathVariable String iban);
}

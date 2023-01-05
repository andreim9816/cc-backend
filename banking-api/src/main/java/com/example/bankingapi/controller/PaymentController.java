package com.example.bankingapi.controller;

import com.example.bankingapi.dto.request.PaymentReqDto;
import com.example.bankingapi.exception.DifferentCurrencyException;
import com.example.bankingapi.exception.NegativeAmountException;
import com.example.bankingapi.exception.NotEnoughAmountException;
import com.example.bankingapi.service.BankAccountService;
import com.example.bankingapi.service.ProducerService;
import com.example.domain.model.BankAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.bankingapi.security.JwtService.API_PATH;

@RestController
@RequestMapping(API_PATH + "/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final ProducerService producerService;
    private final BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity<PaymentReqDto> sendMessage(@RequestBody PaymentReqDto paymentDto) {
        log.info("in controller" + paymentDto);

        BankAccount bankAccountTo = bankAccountService.getBankAccountByIban(paymentDto.getIbanTo());
        BankAccount bankAccountFrom = bankAccountService.getBankAccountByIban(paymentDto.getIbanFrom());

        if (bankAccountTo.getCurrency() != bankAccountFrom.getCurrency())
            throw new DifferentCurrencyException("The accounts have different currencies");

        Double sentAmount = paymentDto.getAmount();
        if(sentAmount < 0)
            throw new NegativeAmountException("The sent amount cannot be negative");
        if (bankAccountFrom.getAmount() - sentAmount < 0)
            throw new NotEnoughAmountException("The account doesn't have enough resources to finish the payment");

        producerService.sendMessage(paymentDto);
        log.info("payment sent: " + paymentDto);
        return ResponseEntity.ok(paymentDto);
    }
}

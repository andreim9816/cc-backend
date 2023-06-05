package com.example.bankingapi.controller;

import com.example.bankingapi.dto.request.AmountReqDto;
import com.example.bankingapi.dto.request.BankAccountReqDto;
import com.example.bankingapi.dto.response.BankAccountDto;
import com.example.bankingapi.dto.response.PaymentDto;
import com.example.bankingapi.service.BankAccountService;
import com.example.bankingapi.service.Mapper;
import com.example.bankingapi.service.PaymentServiceProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final PaymentServiceProxy paymentServiceProxy;
    private final Mapper mapper;

    @GetMapping
    public List<BankAccountDto> getBankAccounts() {
        return bankAccountService.findAllForCurrentUser().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @PostMapping
    public BankAccountDto addBankAccount(@RequestBody @Valid BankAccountReqDto dto) {
        return mapper.toDto(bankAccountService.create(dto));
    }

    @PutMapping("/{iban}")
    public BankAccountDto updateAmountForBankAccount(@PathVariable("iban") String iban,
                                         @RequestBody AmountReqDto dto) {
        return mapper.toDto(bankAccountService.updateAmount(iban, dto));
    }

    @GetMapping("/{iban}")
    public List<PaymentDto> getPaymentsForBankAccount(@PathVariable("iban") String iban) {
        return paymentServiceProxy.getPayments(iban);
    }
}

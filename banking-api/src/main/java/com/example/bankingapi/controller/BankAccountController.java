package com.example.bankingapi.controller;

import com.example.bankingapi.dto.request.AmountReqDto;
import com.example.bankingapi.dto.request.BankAccountReqDto;
import com.example.bankingapi.dto.response.BankAccountDto;
import com.example.bankingapi.exception.CustomException;
import com.example.bankingapi.service.BankAccountService;
import com.example.bankingapi.service.Mapper;
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

    private final BankAccountService service;
    private final Mapper mapper;

    @GetMapping
    public List<BankAccountDto> getBankAccounts() {
        return service.findAllForCurrentUser().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @PostMapping
    public BankAccountDto addBankAccount(@RequestBody @Valid BankAccountReqDto dto) {
        return mapper.toDto(service.create(dto));
    }

    @PostMapping("/{iban}")
    public BankAccountDto addBankAccount(@PathVariable("iban") String iban,
                                         @RequestBody AmountReqDto dto) throws CustomException {
        return mapper.toDto(service.updateAmount(iban, dto));
    }
}

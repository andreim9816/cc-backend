package com.example.payment.service;

import com.example.domain.model.BankAccount;
import com.example.payment.exception.BankAccountNotFoundException;
import com.example.payment.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountService {

  private final BankAccountRepository accountRepository;

  public BankAccount getBankAccountByIban(String iban) {
    Optional<BankAccount> bankAccountToOpt = accountRepository.findByIban(iban);
    if (bankAccountToOpt.isEmpty())
      throw new BankAccountNotFoundException("The iban " + iban + " doesn't exist");
    return bankAccountToOpt.get();
  }
}

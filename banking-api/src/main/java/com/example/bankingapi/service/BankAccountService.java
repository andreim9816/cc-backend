package com.example.bankingapi.service;

import com.example.bankingapi.dto.request.AmountReqDto;
import com.example.bankingapi.dto.request.BankAccountReqDto;
import com.example.bankingapi.exception.BankAccountNotFoundException;
import com.example.bankingapi.exception.NegativeAmountException;
import com.example.bankingapi.repository.BankAccountRepository;
import com.example.bankingapi.security.WebSecuritySupport;
import com.example.domain.model.BankAccount;
import com.example.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BankAccountService {

  private final BankAccountRepository accountRepository;
  private final WebSecuritySupport webSecuritySupport;

  public List<BankAccount> findAllForCurrentUser() {
    User user = webSecuritySupport.getUser();
    return accountRepository.findAllByUserUsername(user.getUsername());
  }

    public BankAccount save(BankAccount bankAccount) {
        return accountRepository.save(bankAccount);
    }

    public BankAccount create(BankAccountReqDto dto) {
      User user = webSecuritySupport.getUser();
      BankAccount newBankAccount = BankAccount.builder()
        .currency(dto.getCurrency())
        .iban(generateIban())
        .amount(0.0)
        .user(user)
        .build();

      return accountRepository.save(newBankAccount);
    }

  public BankAccount getBankAccountByIban(String iban) {
    Optional<BankAccount> bankAccountToOpt = accountRepository.findByIban(iban);
    if (bankAccountToOpt.isEmpty())
      throw new BankAccountNotFoundException("The iban " + iban + " doesn't exist");
    return bankAccountToOpt.get();
  }

  public BankAccount updateAmount(String iban, AmountReqDto reqDto) {
    Optional<BankAccount> bankAccountOpt = accountRepository.findByIban(iban);
    if (bankAccountOpt.isEmpty())
      throw new BankAccountNotFoundException("The bank account was not found");
    if (reqDto.getAmount() < 0) {
      throw new NegativeAmountException("The amount cannot be negative");
    }

    BankAccount bankAccount = bankAccountOpt.get();
    Double newAmount = bankAccount.getAmount() + reqDto.getAmount();
    bankAccount.setAmount(newAmount);
    return accountRepository.save(bankAccount);
  }

  private String generateIban() {
    String alphabet = "0123456789ABCDE";
    int N = alphabet.length();

    while (true) {
      StringBuilder sb = new StringBuilder("RO");
      Random r = new Random();

      for (int i = 0; i < 14; i++) {
        sb.append(alphabet.charAt(r.nextInt(N)));
      }
      String newIban = sb.toString();
      if (!existsIban(newIban)) {
        return newIban;
      }
    }
  }

  private boolean existsIban(String iban) {
    return accountRepository.findByIban(iban).isPresent();
  }
}

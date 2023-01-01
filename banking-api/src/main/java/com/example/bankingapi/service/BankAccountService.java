package com.example.bankingapi.service;

import com.example.bankingapi.dto.request.BankAccountReqDto;
import com.example.bankingapi.repository.BankAccountRepository;
import com.example.bankingapi.security.WebSecuritySupport;
import com.example.domain.model.BankAccount;
import com.example.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

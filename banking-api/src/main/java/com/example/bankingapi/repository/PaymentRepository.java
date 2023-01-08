package com.example.bankingapi.repository;

import com.example.domain.model.BankAccount;
import com.example.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
  List<Payment> getPaymentByBankAccountFromOrBankAccountToOrderByTimestamp(BankAccount bankAccountFrom, BankAccount bankAccountTo);

}

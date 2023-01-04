package com.example.payment.service;

import com.example.domain.model.BankAccount;
import com.example.domain.model.Payment;
import com.example.payment.exception.DifferentCurrencyException;
import com.example.payment.exception.NotEnoughAmountException;
import com.example.payment.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProducerService {
  private final BankAccountRepository accountRepository;

  @RabbitListener(queues = "${spring.rabbitmq.queue}")
  public void receivePayment(Payment payment) {
    BankAccount bankAccountTo = payment.getBankAccountTo();
    BankAccount bankAccountFrom = payment.getBankAccountFrom();
    if (bankAccountTo.getCurrency() != bankAccountFrom.getCurrency())
      throw new DifferentCurrencyException("The accounts have different currencies");

    Double sentAmount = payment.getAmount();
    if (bankAccountFrom.getAmount() - sentAmount < 0)
      throw new NotEnoughAmountException("The account doesn't have enough resources to finish the payment");
    bankAccountTo.setAmount(bankAccountTo.getAmount() + sentAmount);
    bankAccountFrom.setAmount(bankAccountFrom.getAmount() - sentAmount);

    accountRepository.save(bankAccountTo);
    accountRepository.save(bankAccountFrom);
  }
}

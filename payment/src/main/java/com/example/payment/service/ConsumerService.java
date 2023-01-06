package com.example.payment.service;

import com.example.domain.model.BankAccount;
import com.example.domain.model.Payment;
import com.example.payment.dto.request.PaymentReqDto;
import com.example.payment.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {
    private final BankAccountRepository accountRepository;
    private final PaymentService paymentService;
    private final BankAccountService accountService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(PaymentReqDto paymentDto) {
        log.info("payment received: " + paymentDto);
        BankAccount bankAccountFrom = accountService.getBankAccountByIban(paymentDto.getIbanFrom());
        BankAccount bankAccountTo = accountService.getBankAccountByIban(paymentDto.getIbanTo());

        Payment payment = paymentService.create(paymentDto, bankAccountTo, bankAccountFrom);

        Double sentAmount = payment.getAmount();
        bankAccountTo.setAmount(bankAccountTo.getAmount() + sentAmount);
        bankAccountFrom.setAmount(bankAccountFrom.getAmount() - sentAmount);

        accountRepository.save(bankAccountTo);
        accountRepository.save(bankAccountFrom);
    }
}

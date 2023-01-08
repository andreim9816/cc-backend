package com.example.payment.service;

import com.example.payment.dto.request.PaymentReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {
    private final PaymentService paymentService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(PaymentReqDto paymentDto) {
        log.info("payment received: " + paymentDto);
        paymentService.create(paymentDto, paymentDto.getIbanTo(), paymentDto.getIbanFrom());
    }
}

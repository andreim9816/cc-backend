package com.example.bankingapi.service;

import com.example.bankingapi.dto.request.PaymentReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    public void sendMessage(PaymentReqDto paymentDto) {
        rabbitTemplate.convertAndSend(exchange, routingkey, paymentDto);
    }
}

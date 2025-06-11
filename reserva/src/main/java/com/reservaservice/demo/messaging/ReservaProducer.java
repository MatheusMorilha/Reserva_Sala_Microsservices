package com.reservaservice.demo.messaging;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.reservaservice.demo.config.RabbitConfig;

@Component
public class ReservaProducer {

    private final RabbitTemplate rabbitTemplate;

    public ReservaProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendReservaMessage(String mensagem) {
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.ROUTING_KEY, mensagem);
        System.out.println("Mensagem enviada: " + mensagem);
    }
}

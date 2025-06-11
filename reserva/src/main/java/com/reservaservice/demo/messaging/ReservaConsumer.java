package com.reservaservice.demo.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.reservaservice.demo.config.RabbitConfig;

@Component
public class ReservaConsumer {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveMessage(String mensagem) {
        System.out.println("Mensagem recebida: " + mensagem);
    }
}


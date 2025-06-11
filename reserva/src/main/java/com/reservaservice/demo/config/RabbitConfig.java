package com.reservaservice.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME = "reservaQueue";
    public static final String EXCHANGE_NAME = "reservaExchange";
    public static final String ROUTING_KEY = "reserva.key";
    public static final String RESERVA_QUEUE = QUEUE_NAME;

    @Bean
    public Queue reservaQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public TopicExchange reservaExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue reservaQueue, TopicExchange reservaExchange) {
        return BindingBuilder.bind(reservaQueue).to(reservaExchange).with(ROUTING_KEY);
    }
}


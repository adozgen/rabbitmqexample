package com.adozgen.rabbitmqexample.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Configuration
public class RabbitMqConfiguration2 {

    @Value("${notification.queue.name}")
    private String queueName;
    @Value("${notification.routing.name}")
    private String routingName;
    @Value("${notification.exchange.name}")
    private String exchangeName;

    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }
    @Bean
    public DirectExchange exchange2() {
        return new DirectExchange(exchangeName);
    }
    @Bean
    public Binding binding(final Queue queue){
        return BindingBuilder.bind(queue).to(exchange2()).with(routingName);
    }

}

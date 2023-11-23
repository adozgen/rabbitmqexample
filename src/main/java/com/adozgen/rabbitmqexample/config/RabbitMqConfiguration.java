package com.adozgen.rabbitmqexample.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Properties;


@Configuration
public class RabbitMqConfiguration {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    private final QueueProperties queueProperties;

    public RabbitMqConfiguration(QueueProperties queueProperties) {
        this.queueProperties = queueProperties;
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }
    @Bean
    public Declarables declarables() {
        List<Declarable> declarables = new ArrayList<>();
        Map<String, String> queues = queueProperties.getQueue();
        if (queues != null) {
            queues.forEach((key, value) -> {
                Queue queue = new Queue(value, true);
                Binding binding = BindingBuilder.bind(queue).to(exchange()).with(value);
                declarables.add(queue);
                declarables.add(binding);
            });
        }

        return new Declarables(declarables);
    }

}

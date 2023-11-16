package com.adozgen.rabbitmqexample.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMqConfiguration {

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
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }
    @Bean
    public Binding binding(final Queue queue, final DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(routingName);
    }

}

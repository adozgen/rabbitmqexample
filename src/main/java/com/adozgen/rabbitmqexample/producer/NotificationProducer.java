package com.adozgen.rabbitmqexample.producer;

import com.adozgen.rabbitmqexample.entity.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {


    @Value("${notification.routing.name}")
    private String routingName;
    @Value("${notification.exchange.name}")
    private String exchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sendNotification(Notification notification) {
        rabbitTemplate.convertAndSend(exchangeName, routingName, notification);
    }
}

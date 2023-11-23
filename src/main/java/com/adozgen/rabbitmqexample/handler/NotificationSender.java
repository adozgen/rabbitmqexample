package com.adozgen.rabbitmqexample.handler;

import com.adozgen.rabbitmqexample.entity.Notification;
import com.adozgen.rabbitmqexample.producer.NotificationProducer;
import com.adozgen.rabbitmqexample.producer.RabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.util.Date;
import java.util.UUID;

@Component
public class NotificationSender {

    @Autowired
    private RabbitProducer producer;

    @Value("${notification.queue.name}")
    private String queueName;

    public void sendNotify() {
        Notification notification = new Notification();
        notification.setNotificationId(UUID.randomUUID().toString());
        notification.setDate(new Date());
        notification.setMessage("mesaj var...");
        notification.setSeen(false);
        producer.send(queueName, notification);
    }


    @PostConstruct
    public void init() {
        System.out.println("Notification sender");
        sendNotify();

    }
}
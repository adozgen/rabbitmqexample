package com.adozgen.rabbitmqexample.consumer;

import com.adozgen.rabbitmqexample.entity.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @RabbitListener(queues = {"${notification.queue.name}"})
    public void handle(Notification notification) {
        System.out.println("Message received..");
        System.out.println(notification.toString());
    }
}

package com.adozgen.rabbitmqexample.handler;

import com.adozgen.rabbitmqexample.entity.Notification;
import com.adozgen.rabbitmqexample.producer.NotificationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.util.Date;
import java.util.UUID;

@Component
public class NotificationSender {

    @Autowired
    private NotificationProducer producer;

    public void getThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                Notification notification = new Notification();
                notification.setNotificationId(UUID.randomUUID().toString());
                notification.setDate(new Date());
                notification.setMessage("mesaj var...");
                notification.setSeen(false);
                producer.sendNotification(notification);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("Notification sender");
        thread.start();
    }


    @PostConstruct
    public void init() {
        System.out.println("Notification sender");
        // getThread();

    }
}
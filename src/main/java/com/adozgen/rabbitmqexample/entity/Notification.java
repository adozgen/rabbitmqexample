package com.adozgen.rabbitmqexample.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Notification implements Serializable {
    private String notificationId;
    private String message;
    private Date date;
    private Boolean seen;


    @Override
    public String toString() {
        return "Notification{" +
                "notificationId='" + notificationId + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", seen=" + seen +
                '}';
    }
}

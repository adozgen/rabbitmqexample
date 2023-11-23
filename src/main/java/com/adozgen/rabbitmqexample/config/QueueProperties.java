package com.adozgen.rabbitmqexample.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Map;


@Getter
@ConfigurationProperties(prefix = "default")
public class QueueProperties {
    // Getter and setter
    private Map<String, String> queue;

    public void setQueue(Map<String, String> queue) {
        this.queue = queue;
    }
}


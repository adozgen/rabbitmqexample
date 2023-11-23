package com.adozgen.rabbitmqexample;

import com.adozgen.rabbitmqexample.config.QueueProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RabbitmqexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqexampleApplication.class, args);
	}

}

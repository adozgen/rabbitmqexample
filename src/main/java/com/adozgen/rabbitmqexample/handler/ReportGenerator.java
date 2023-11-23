package com.adozgen.rabbitmqexample.handler;


import com.adozgen.rabbitmqexample.producer.RabbitProducer;
import com.adozgen.rabbitmqexample.service.PDFGeneratorQueueService;
import com.adozgen.rabbitmqexample.service.PDFGeneratorService;
import com.adozgen.rabbitmqexample.service.PdfData;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@Component
public class ReportGenerator {

    @Autowired
    private RabbitProducer producer;

    @Autowired
    PDFGeneratorQueueService pdfGeneratorQueueService;


    @Value("${default.queue.report}")
    private String queueName;


    public void publish(Object response) {
        producer.send(queueName, response);
    }

    @RabbitListener(queues = "${default.queue.report}")
    public void pull(PdfData pdfData) {
        System.out.println("Received message: " + pdfData);
        pdfGeneratorQueueService.createPdf(pdfData);
    }
}
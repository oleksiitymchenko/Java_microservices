package com.labs.logger.Messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Component
public class Subscriber {
    @RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void receivedMessage(String msg) {
        System.out.println("Received Message: " + msg);
    }
}
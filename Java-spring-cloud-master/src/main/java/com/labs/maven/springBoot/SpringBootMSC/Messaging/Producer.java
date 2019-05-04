package com.labs.maven.springBoot.SpringBootMSC.Messaging;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void produceMsg(String msg, String queueName){
        amqpTemplate.convertAndSend(queueName, msg);
        System.out.println("Send msg = " + msg);
    }
}

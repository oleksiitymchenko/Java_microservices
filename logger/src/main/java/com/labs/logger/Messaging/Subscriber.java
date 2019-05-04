package com.labs.logger.Messaging;

import com.labs.logger.DAL.Models.LoggerTable;
import com.labs.logger.DAL.Repositories.LoggerTableRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Subscriber {

    @RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void receivedMessage(String msg) {
        System.out.println("Received Message: " + msg);

        LoggerTable logRecord = new LoggerTable();
        logRecord.setMessageText(msg);
        logRecord.setMessageType("TEST");
        saveEntity(logRecord);
    }



    private LoggerTableRepository repository;

    @Autowired
    public void setBuilderRepository(LoggerTableRepository repository) {
        this.repository = repository;
    }

    public LoggerTable saveEntity(LoggerTable table) {

        if (table.getMessageText() == null ||
                table.getMessageType() == null) {
            return null;
        }
        return repository.save(table);
    }

}
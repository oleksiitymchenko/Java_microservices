package com.labs.logger.Messaging;

import com.labs.logger.DAL.Models.LoggerTable;
import com.labs.logger.DAL.Repositories.LoggerTableRepository;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Subscriber {

    @RabbitListener(queues="${jsa.rabbitmq.queue.createdtype}")
    public void receivedMessageCreated(String msg) {
        System.out.println("Received Message: " + msg);

        LoggerTable record = DeserializeLogRecord(msg);
        System.out.println(record);

        if(record == null) return;
        record.setMessageType("RECORD CREATED");
        saveEntity(record);
    }

    @RabbitListener(queues="${jsa.rabbitmq.queue.updatedtype}")
    public void receivedMessageUpdated(String msg) {
        System.out.println("Received Message: " + msg);

        LoggerTable record = DeserializeLogRecord(msg);
        System.out.println(record);

        if(record == null) return;
        record.setMessageType("RECORD UPDATED");
        saveEntity(record);
    }

    @RabbitListener(queues="${jsa.rabbitmq.queue.deletedtype}")
    public void receivedMessageDelete(String msg) {
        System.out.println("Received Message: " + msg);

        LoggerTable record = DeserializeLogRecord(msg);
        System.out.println(record);

        if(record == null) return;
        record.setMessageType("RECORD DELETED");

        saveEntity(record);
    }

    private LoggerTable DeserializeLogRecord(String logRecord)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            LoggerTable LoggerTableEntity = mapper.readValue(logRecord, LoggerTable.class);
            System.out.println(LoggerTableEntity);
            return  LoggerTableEntity;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
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
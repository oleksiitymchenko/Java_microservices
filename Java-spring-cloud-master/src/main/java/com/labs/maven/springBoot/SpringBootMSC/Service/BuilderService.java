package com.labs.maven.springBoot.SpringBootMSC.Service;

import com.labs.maven.springBoot.SpringBootMSC.Messaging.Producer;
import com.labs.maven.springBoot.SpringBootMSC.Model.Builder;
import com.labs.maven.springBoot.SpringBootMSC.Model.LoggerTable;
import com.labs.maven.springBoot.SpringBootMSC.Repositories.BuilderRepository;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.InvalidInfoException;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.ItemNotFoundException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BuilderService implements IEntityService<Builder> {

    private BuilderRepository repository;

    @Autowired
    public void setBuilderRepository(BuilderRepository repository) {
        this.repository = repository;
    }

    @Autowired
    Producer publisher;

    @Value("${jsa.rabbitmq.queue.createdtype}")
    String queueCreatedName;

    @Value("${jsa.rabbitmq.queue.updatedtype}")
    String queueUpdatedName;

    @Value("${jsa.rabbitmq.queue.deletedtype}")
    String queueDeletedName;

    @Override
    public Optional<Builder> getEntityById(Integer id) {
        Optional<Builder> doc = repository.findByIdAndIsDeletedFalse(id);
        if (!doc.isPresent())
            throw new ItemNotFoundException("There is no entity with such id");

        return doc;
    }

    @Override
    public List<Builder> getAllEntities() {
        return (List<Builder>)repository.findByIsDeletedFalse();
    }

    @Override
    public Builder saveEntity(Builder builder) {

        if (builder.getFirstName() == null ||
                builder.getLastName() == null ||
                builder.getAge() == null) {
            throw new InvalidInfoException("Not all required fields where filled in");
        }
        sendLog(builder, queueCreatedName);
        return repository.save(builder);
    }

    @Override
    public Builder updateEntity(Builder newBuilder, Integer id) {
        if (newBuilder.getFirstName() == null ||
                newBuilder.getLastName() == null ||
                newBuilder.getAge() == null) {
            throw new InvalidInfoException("Not all required fields where filled in");
        } else {
            return repository.findById(id)
                    .map(builder -> {
                        builder.setFirstName(newBuilder.getFirstName());
                        builder.setLastName(newBuilder.getLastName());
                        builder.setAge(newBuilder.getAge());
                        sendLog(builder, queueUpdatedName);
                        return repository.save(builder);
                    })
                    .orElseGet(() -> {
                        newBuilder.setId(id);
                        sendLog(newBuilder, queueUpdatedName);
                        return repository.save(newBuilder);
                    });
        }
    }

    @Override
    public void deleteEntity(Integer id) {
        Optional<Builder> builder = repository.findById(id);
        if (!builder.isPresent())
            throw new ItemNotFoundException("There is no entity with such id");
        builder.map(builderNew -> {
                builderNew.setIsDeleted(true);
                return repository.save(builderNew);
        });
        repository.save(builder.get());
        sendLog(builder.get(), queueDeletedName);
    }




    private void sendLog(Builder builder, String queueName) {
        System.out.println("*******************");
        System.out.println("Sending message");
        LoggerTable logRecord = new LoggerTable();
        ObjectMapper mapper = new ObjectMapper();


        try {
            logRecord.setMessageText(mapper.writeValueAsString(builder));
            logRecord.setEntityName(Builder.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String loggerRecordString = null;
        try {
            loggerRecordString = mapper.writeValueAsString(logRecord);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(loggerRecordString);
        publisher.produceMsg(loggerRecordString, queueName);
    }
}

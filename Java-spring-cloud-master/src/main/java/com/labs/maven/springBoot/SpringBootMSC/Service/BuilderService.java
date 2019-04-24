package com.labs.maven.springBoot.SpringBootMSC.Service;

import com.labs.maven.springBoot.SpringBootMSC.Model.Builder;
import com.labs.maven.springBoot.SpringBootMSC.Repositories.BuilderRepository;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.InvalidInfoException;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BuilderService implements IEntityService<Builder> {

    private BuilderRepository repository;

    @Autowired
    public void setBuilderRepository(BuilderRepository repository) {
        this.repository = repository;
    }

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
                        return repository.save(builder);
                    })
                    .orElseGet(() -> {
                        newBuilder.setId(id);
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
    }
}

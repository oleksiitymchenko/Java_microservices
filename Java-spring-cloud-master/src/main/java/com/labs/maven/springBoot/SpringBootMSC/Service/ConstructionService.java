package com.labs.maven.springBoot.SpringBootMSC.Service;

import com.labs.maven.springBoot.SpringBootMSC.Model.Construction;
import com.labs.maven.springBoot.SpringBootMSC.Repositories.ConstructionRepository;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.InvalidInfoException;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConstructionService implements IEntityService<Construction> {

    private ConstructionRepository repository;

    @Autowired
    public void setConstructionRepository(ConstructionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Construction> getEntityById(Integer id) {
        Optional<Construction> construction = repository.findByIdAndIsDeletedFalse(id);

        if (!construction.isPresent()) {
            throw new ItemNotFoundException("There is no entity with such id");
        }
        return construction;
    }

    @Override
    public List<Construction> getAllEntities() {
        return (List<Construction>)repository.findByIsDeletedFalse();
    }

    @Override
    public Construction saveEntity(Construction construction) {
        if (construction.getName() == null || construction.getName() == null) {
            throw new InvalidInfoException("Not all required fields where filled in");
        }
        return repository.save(construction);
    }

    @Override
    public Construction updateEntity(Construction newConstruction, Integer id) {

        if (newConstruction.getName() == null || newConstruction.getAddress() == null) {
            throw new InvalidInfoException("Not all required fields where filled in");
        }
        return repository.findById(id)
                .map(constr -> {
                    constr.setName(newConstruction.getName());
                    constr.setFloors(newConstruction.getFloors());
                    constr.setAddress(newConstruction.getAddress());
                    return repository.save(constr);
                })
                .orElseGet(() -> {
                    newConstruction.setId(id);
                    return repository.save(newConstruction);
                });
    }

    @Override
    public void deleteEntity(Integer id) {
        Optional<Construction> construction = repository.findById(id);
        if (!construction.isPresent())
            throw new ItemNotFoundException("There is no entity with such id");

        construction.map(constructionNew -> {
            constructionNew.setIsDeleted(true);
            return repository.save(constructionNew);
        });
        repository.save(construction.get());
    }
}

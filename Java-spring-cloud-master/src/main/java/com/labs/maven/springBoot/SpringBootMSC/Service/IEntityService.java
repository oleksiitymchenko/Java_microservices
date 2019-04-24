package com.labs.maven.springBoot.SpringBootMSC.Service;

import java.util.List;
import java.util.Optional;

public interface IEntityService<Entity> {
    Optional<Entity> getEntityById(Integer id);
    List<Entity> getAllEntities();
    Entity saveEntity(Entity entity);
    Entity updateEntity(Entity updatedEntity, Integer id);
    void deleteEntity(Integer id);
}

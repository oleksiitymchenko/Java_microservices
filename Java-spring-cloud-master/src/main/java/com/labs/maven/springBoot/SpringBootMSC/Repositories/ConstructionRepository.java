package com.labs.maven.springBoot.SpringBootMSC.Repositories;

import com.labs.maven.springBoot.SpringBootMSC.Model.Construction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConstructionRepository extends CrudRepository<Construction, Integer>{
    Optional<Construction> findByIdAndIsDeletedFalse(int id);
    List<Construction> findByIsDeletedFalse();
}

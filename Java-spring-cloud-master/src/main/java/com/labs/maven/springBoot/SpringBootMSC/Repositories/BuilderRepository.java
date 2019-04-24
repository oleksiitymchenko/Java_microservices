package com.labs.maven.springBoot.SpringBootMSC.Repositories;

import com.labs.maven.springBoot.SpringBootMSC.Model.Builder;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuilderRepository extends CrudRepository<Builder, Integer> {
    Optional<Builder> findByIdAndIsDeletedFalse(int id);
    List<Builder> findByIsDeletedFalse();
}

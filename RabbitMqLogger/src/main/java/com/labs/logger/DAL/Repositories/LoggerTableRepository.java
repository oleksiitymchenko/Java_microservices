package com.labs.logger.DAL.Repositories;

import com.labs.logger.DAL.Models.LoggerTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerTableRepository extends CrudRepository<LoggerTable, Integer>{
}

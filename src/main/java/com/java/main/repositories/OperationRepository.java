package com.java.main.repositories;

import com.java.main.models.Operation;
import com.java.main.models.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

// How to fetch entities multiple levels deep with Hibernate

@Repository
public interface OperationRepository extends CrudRepository<Operation, Integer> {

    Set<Operation> findByStatusIn(Status[] statuses);

    @Query("select operation from Operation operation left join fetch operation.machine ma" +
            "left join fetch operation.material m " +
            "join fetch m.measuredValue " +
            "where operation.id=:id")
    Operation findOperationById(int id);
}

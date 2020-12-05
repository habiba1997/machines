package com.java.main.repositories;

import com.java.main.models.Machine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface Machine_Repository extends CrudRepository<Machine, Integer> {

    @Override
    @Query("select machine from Machine machine left join fetch machine.operations")
    Set<Machine> findAll();
}

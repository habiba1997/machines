package com.java.main.repositories;

import com.java.main.models.Machine;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Machine_Repository extends CrudRepository<Machine, Integer> {

    @Override
    @Query("select machine from Machine machine left join fetch machine.operations")
    Iterable<Machine> findAll();
}

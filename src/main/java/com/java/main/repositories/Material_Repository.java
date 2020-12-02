package com.java.main.repositories;

import com.java.main.models.Material;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface Material_Repository extends CrudRepository<Material, Integer> {

    @Override
    @Query("SELECT m FROM Material m JOIN FETCH m.operations")
    Iterable<Material> findAll();

}

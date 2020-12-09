package com.java.main.repositories;

import com.java.main.models.Material;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface MaterialRepository extends CrudRepository<Material, Integer> {

    @Override
    @Query("SELECT m FROM Material m left join fetch m.operations left join fetch m.measuredValue")
    Set<Material> findAll();


}

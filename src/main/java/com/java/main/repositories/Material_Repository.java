package com.java.main.repositories;

import com.java.main.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Material_Repository extends JpaRepository<Material, Integer> {

    @Override
//    @Query("select material from Material material left join fetch material.operations")
    List<Material> findAll();
}

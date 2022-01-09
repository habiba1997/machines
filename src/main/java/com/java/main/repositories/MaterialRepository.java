package com.java.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.main.models.entity.MaterialEntity;

@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, Integer> {

	List<MaterialEntity> findAll();

	MaterialEntity findByName(String name);

}

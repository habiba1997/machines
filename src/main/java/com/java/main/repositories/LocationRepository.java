package com.java.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.main.models.entity.LocationEntity;

// How to fetch entities multiple levels deep with Hibernate

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {

	List<LocationEntity> findAll();

	LocationEntity findByName(String name);
}

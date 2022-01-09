package com.java.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.main.models.entity.OperationEntity;

// How to fetch entities multiple levels deep with Hibernate

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Integer> {

	List<OperationEntity> findByStatusIn(List<String> statuses);

	@Query("select operation from OperationEntity operation " +
			"join fetch operation.materialEntity material " +
			"join fetch operation.productionOrderEntity production " +
			"where production.name = ?1")
	List<OperationEntity> findByProductionOrderName(String productionOrderName);

}

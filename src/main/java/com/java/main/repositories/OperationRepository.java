package com.java.main.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.main.models.entity.OperationEntity;
import com.java.main.profile.CacheConstants;

// How to fetch entities multiple levels deep with Hibernate

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Integer> {

	List<OperationEntity> findByStatusIn(List<String> statuses);

	@Cacheable(cacheNames = CacheConstants.OPERATIONS_BY_PRODUCTION_ORDER)
	@Query("select operation from OperationEntity operation " +
			"join fetch operation.materialEntity material " +
			"join fetch operation.productionOrderEntity production " +
			"where production.name = ?1")
	List<OperationEntity> findByProductionOrderName(String productionOrderName);

	Optional<OperationEntity> findByName(String name);

}

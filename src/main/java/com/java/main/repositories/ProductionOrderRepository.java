package com.java.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.main.models.entity.ProductionOrderEntity;

@Repository
public interface ProductionOrderRepository extends JpaRepository<ProductionOrderEntity, Integer> {

	ProductionOrderEntity findByName(String name);

}

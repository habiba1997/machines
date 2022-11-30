package com.machines.main.services;

import java.util.List;

import com.machines.main.dtos.ProductionOrder;

public interface ProductionOrderService {

	ProductionOrder findByName(String name);

	List<ProductionOrder> findAll();
}

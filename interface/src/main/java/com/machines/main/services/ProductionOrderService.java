package com.machines.main.services;

import com.machines.main.dtos.ProductionOrder;

public interface ProductionOrderService {

	ProductionOrder findByName(String name);
}

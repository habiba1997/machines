package com.java.main.services;

import com.java.main.dtos.ProductionOrder;

public interface ProductionOrderService {

	ProductionOrder findByName(String name);
}

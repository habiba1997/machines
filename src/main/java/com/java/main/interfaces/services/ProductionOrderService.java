package com.java.main.interfaces.services;

import com.java.main.dtos.ProductionOrder;

public interface ProductionOrderService {

	ProductionOrder findByName(String name);
}

package com.java.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.dtos.ProductionOrder;
import com.java.main.interfaces.services.ProductionOrderService;
import com.java.main.mappers.ProductionOrderMapper;
import com.java.main.repositories.ProductionOrderRepository;

@Service
public class ProductionOrderServiceImpl implements ProductionOrderService {

	@Autowired
	private ProductionOrderRepository repository;

	@Autowired
	private ProductionOrderMapper mapper;

	@Override
	public ProductionOrder findByName(final String productionOrderName) {
		return mapper.toModel(repository.findByName(productionOrderName));
	}
}

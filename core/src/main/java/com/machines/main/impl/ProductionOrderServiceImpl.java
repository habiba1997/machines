package com.machines.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machines.main.dtos.ProductionOrder;
import com.machines.main.mappers.ProductionOrderMapper;
import com.machines.main.repositories.ProductionOrderRepository;
import com.machines.main.services.ProductionOrderService;

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

	@Override
	public List<ProductionOrder> findAll() {
		return mapper.toModels(repository.findAll());
	}
}

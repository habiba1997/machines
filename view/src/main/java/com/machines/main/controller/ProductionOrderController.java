package com.machines.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.machines.main.dtos.ProductionOrder;
import com.machines.main.services.ProductionOrderService;

@RestController
public class ProductionOrderController {

	@Autowired
	private ProductionOrderService productionOrderService;

	@GetMapping(value = "/production-order")
	public ResponseEntity<ProductionOrder> getProductionOrderByName(@RequestParam(name = "name") final String productionOrderName) {
		ProductionOrder productionOrder = this.productionOrderService.findByName(productionOrderName);
		return new ResponseEntity<>(productionOrder, HttpStatus.OK);
	}

}

package com.java.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.dtos.ProductionOrder;
import com.java.main.services.ProductionOrderService;

@RestController
public class ProductionOrderController {

	@Autowired
	private ProductionOrderService productionOrderService;

	@GetMapping(value = "/production-order")
	public ResponseEntity<ProductionOrder> getProductionOrderByName(@RequestParam(name = "name") final String productionOrderName) {
		return new ResponseEntity<>(this.productionOrderService.findByName(productionOrderName), HttpStatus.OK);
	}

}

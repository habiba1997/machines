package com.java.main.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.event.EventPublisher;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.services.MaterialService;

@RestController
public class MaterialController {

	@Autowired
	private MaterialService materialService;
	@Autowired
	private EventPublisher eventPublisher;

	@GetMapping(value = "/materials")
	public ResponseEntity<Set<MaterialDTO>> getAllMaterials() {
		ResponseEntity<Set<MaterialDTO>> responce = new ResponseEntity<>(this.materialService.getAllMaterials(), HttpStatus.OK);
		if (responce.getStatusCode() == HttpStatus.OK) {
			eventPublisher.publishCustomEvent("Get My Machines", false);
		}
		return responce;
	}
}

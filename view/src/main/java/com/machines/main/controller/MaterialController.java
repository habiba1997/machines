package com.machines.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.machines.main.dtos.Material;
import com.machines.main.services.MaterialService;

@RestController
public class MaterialController {

	@Autowired
	private MaterialService materialService;

	@GetMapping(value = "/materials")
	public ResponseEntity<List<Material>> getAllMaterials() {
		return new ResponseEntity<>(this.materialService.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/material")
	public ResponseEntity<Material> getMaterialByName(@RequestParam(name = "name") final String materialName) {
		return new ResponseEntity<>(this.materialService.findByName(materialName), HttpStatus.OK);
	}
}

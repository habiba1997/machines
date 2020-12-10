package com.java.main.controller;

import com.java.main.models.dtos.MaterialDTO;
import com.java.main.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class MaterialController {


    @Autowired
    private MaterialService materialService;

    @CrossOrigin
    @GetMapping(value ="/materials" )
    public ResponseEntity<Set<MaterialDTO>> getAllMaterials()
    {
        return new ResponseEntity<>(this.materialService.getAllMaterials(), HttpStatus.OK);
    }
}

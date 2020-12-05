package com.java.main.controller;

import com.java.main.models.dtos.MachineDTO;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.services.Machine_Service;
import com.java.main.services.Material_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class Controller {

    @Autowired
    private Machine_Service machineService;
    @Autowired
    private Material_Service materialService;

    @CrossOrigin
    @GetMapping(value ="/machines" )
    public ResponseEntity<List<MachineDTO>> getAllMachines()
    {
        return new ResponseEntity<>(this.machineService.getAllMachines(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value ="/materials" )
    public ResponseEntity<Set<MaterialDTO>> getAllMaterials()
    {
        return new ResponseEntity<>(this.materialService.getAllMaterials(), HttpStatus.OK);
    }

}

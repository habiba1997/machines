package com.java.main.controller;

import com.java.main.models.dtos.MachineDTO;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.services.MachineService;
import com.java.main.services.MaterialService;
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
    private MachineService machineService;
    @Autowired
    private MaterialService materialService;

    @CrossOrigin
    @GetMapping(value ="/machines" )
    public ResponseEntity<Set<MachineDTO>> getAllMachines()
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

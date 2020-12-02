package com.java.main.controller;

import com.java.main.models.Machine;
import com.java.main.models.Material;
import com.java.main.services.Machine_Service;
import com.java.main.services.Material_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private Machine_Service machineService;
    @Autowired
    private Material_Service materialService;

    @CrossOrigin
    @GetMapping(value ="/machines" )
    public ResponseEntity<Iterable<Machine>> getAllMachines()
    {
        return new ResponseEntity<>(this.machineService.getAllMachines(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value ="/materials" )
    public ResponseEntity<List<Material>> getAllMaterials()
    {
        return new ResponseEntity<>(this.materialService.getAllMaterials(), HttpStatus.OK);
    }


    @CrossOrigin
    @GetMapping(value ="/cache/machines" )
    public ResponseEntity<Iterable<Machine>> getAllMachinesCached()
    {
        return new ResponseEntity<>(this.machineService.getAllMachinesCached(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value ="/cache/materials" )
    public ResponseEntity<List<Material>> getAllMaterialsCached()
    {
        return new ResponseEntity<>(this.materialService.getAllMaterialsCached(), HttpStatus.OK);
    }
}

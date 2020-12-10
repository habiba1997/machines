package com.java.main.controller;

import com.java.main.models.dtos.MachineDTO;
import com.java.main.services.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class MachineController {

    @Autowired
    private MachineService machineService;


    @CrossOrigin
    @GetMapping(value ="/machines" )
    public ResponseEntity<Set<MachineDTO>> getAllMachines()
    {
        return new ResponseEntity<>(this.machineService.getAllMachines(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value ="/machines-status" )
    public ResponseEntity<Set<MachineDTO>> getAllMachinesSpecificProductionOrder()
    {
        return new ResponseEntity<>(this.machineService.getAllMachinesWithSetupAndInOverEndingProductionStatus(), HttpStatus.OK);
    }

}

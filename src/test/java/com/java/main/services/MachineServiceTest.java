package com.java.main.services;

import com.java.main.mappers.MachineMapperImpl;
import com.java.main.models.Machine;
import com.java.main.models.Operation;
import com.java.main.models.Status;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.repositories.MachineRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;


@ExtendWith(MockitoExtension.class)
class MachineServiceTest {

    @Mock
    private MachineRepository machineRepository;

    @Spy
    private MachineMapperImpl mapper;

    @InjectMocks
    private MachineService machineService;

    Set<Machine> machineSet;
    Set<Machine> machineDTOSet;


    @BeforeEach
    public void setup(){
        this.machineSet = new HashSet<>();

        Machine machine1 = new Machine(1,"machine1");
        machine1.addOperation(new Operation(1,"operation1"));
        machine1.addOperation(new Operation(2, "operation2"));

        Machine machine2 = new Machine(2,"machine2");
        machine2.addOperation(new Operation(3,"operation3"));
        machine2.addOperation(new Operation(4,"operation5"));

        this.machineSet.add(machine1);
        this.machineSet.add(machine2);

        this.machineDTOSet = new HashSet<>();

        Machine machineDTO1 = new Machine(1,"machine1");
        machineDTO1.addOperation(new Operation(1,"operation1", Status.setup));

        Machine machineDTO2 = new Machine(2,"machine2");
        machineDTO2.addOperation(new Operation(1,"operation1", Status.in_production));
        machineDTO2.addOperation(new Operation(1,"operation1", Status.over_production));


        this.machineDTOSet.add(machineDTO1);
        this.machineDTOSet.add(machineDTO2);
    }

    @Test
    @DisplayName("Testing getting machine joining operations list with dummy values")
    public void testGetMachineListFilled() {

        Mockito.when(machineRepository.findAll())
                .thenReturn(this.machineSet);

        when(mapper.mapMachineSetToMachineDtoSet(this.machineSet)).thenCallRealMethod();

        Set<MachineDTO> machineList = machineService.getAllMachines();

        assertEquals(2,machineList.size());
        assertFalse(machineList.isEmpty());
    }

    @Test
    @DisplayName("Testing getting machines With Setup And In, Over and Ending Production Status")
    public void testGetAllMachinesWithSetupAndInOverEndingProductionStatus() {

        Status[] statuses = new Status[]{Status.setup, Status.in_production, Status.over_production, Status.ending_production};

        Mockito.when(machineRepository.findMachinesWithSpecificProductionOrder(statuses))
                .thenReturn(this.machineDTOSet);

        when(mapper.mapMachineSetToMachineDtoSet(this.machineDTOSet)).thenCallRealMethod();

        Set<MachineDTO> machineList = machineService.getAllMachinesWithSetupAndInOverEndingProductionStatus();

        assertEquals(2,machineList.size());
        assertFalse(machineList.isEmpty());
    }


}
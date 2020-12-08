package com.java.main.services;

import com.java.main.mappers.MachineOperationMapperImpl;
import com.java.main.models.Machine;
import com.java.main.models.Operation;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.repositories.MachineRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;


//If you are using Junit version < 5, so you have to use @RunWith(SpringRunner.class) or @RunWith(MockitoJUnitRunner.class) etc.
//If you are using Junit version = 5, so you have to use @ExtendWith(SpringExtension.class) or @ExtendWith(MockitoExtension.class) etc.
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class MachineServiceTest {

    @MockBean
    private MachineRepository machineRepository;

    @Spy
    private MachineOperationMapperImpl mapper;

    @InjectMocks
    private MachineService machineService;

    Set<Machine> machineSet;

    public void fillSetWithDummyValues(){

        Machine machine1 = new Machine(1,"machine1");
        machine1.addOperation(new Operation(1,"operation1"));
        machine1.addOperation(new Operation(2, "operation2"));

        Machine machine2 = new Machine(2,"machine2");
        machine2.addOperation(new Operation(3,"operation3"));
        machine2.addOperation(new Operation(4,"operation5"));

        this.machineSet.add(machine1);
        this.machineSet.add(machine2);
    }

    @Test
    @DisplayName("Testing getting machine joining operations list with dummy values")
    public void testGetMachineListFilled() {

        this.machineSet = new HashSet<>();
        fillSetWithDummyValues();

        Mockito.when(machineRepository.findAll())
                .thenReturn(this.machineSet);

        when(mapper.mapMachineSetToMachineDtoSet(this.machineSet)).thenCallRealMethod();

        Set<MachineDTO> machineList = machineService.getAllMachines();

        assertEquals(2,machineList.size());
    }

    @Test
    @DisplayName("Testing getting machine joining operations list while empty")
    public void testGetMachineListEmpty() {

        this.machineSet = new HashSet<>();

        Mockito.when(machineRepository.findAll())
                .thenReturn(this.machineSet);

        when(mapper.mapMachineSetToMachineDtoSet(this.machineSet)).thenCallRealMethod();

        Set<MachineDTO> machineList = machineService.getAllMachines();

        assertTrue(machineList.isEmpty());
    }
}
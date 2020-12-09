package com.java.main.mappers;

import com.java.main.models.Machine;
import com.java.main.models.Operation;
import com.java.main.models.dtos.MachineDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MachineOperationMapperImplTest {

    @InjectMocks
    private MachineOperationMapperImpl impl;

    Set<Machine> machineSet;

    @BeforeEach
    void setup()
    {
        machineSet = new HashSet<>();

        Machine machine1 = new Machine(1,"machine1");
        machine1.addOperation(new Operation(1,"operation1"));
        machine1.addOperation(new Operation(2, "operation2"));

        Machine machine2 = new Machine(2,"machine2");
        machine2.addOperation(new Operation(3,"operation3"));
        machine2.addOperation(new Operation(4,"operation5"));

        machineSet.add(machine1);
        machineSet.add(machine2);
    }

    @Test
    void testMapMachineToDTO() {
        Machine machine = machineSet.iterator().next();

        MachineDTO machineDTO = impl.machineToDto(machine);

        assertEquals(machine.getName(), machineDTO.getName());
        assertEquals(machine.getId(), machineDTO.getId());
        assertEquals(2,machineDTO.getOperations().size());
    }
    @Test
    void testMapMachineSetToMachineDtoSet() {
        Set<MachineDTO> machineDTOSet = impl.mapMachineSetToMachineDtoSet(machineSet);
        assertEquals(machineDTOSet.size(), machineSet.size());
    }
}

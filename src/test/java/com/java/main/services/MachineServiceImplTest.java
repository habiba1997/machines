package com.java.main.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.java.main.mappers.MachineMapper;
import com.java.main.models.Machine;
import com.java.main.models.Operation;
import com.java.main.models.Status;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.models.dtos.OperationDTO;
import com.java.main.repositories.MachineRepository;

@ExtendWith(MockitoExtension.class)
class MachineServiceImplTest {

	@Mock
	private MachineRepository machineRepository;

	@Mock
	private MachineMapper mapper;

	@InjectMocks
	private MachineServiceImpl machineServiceImpl;

	Set<Machine> machineSet;
	Set<MachineDTO> machineDTOSet;

	@BeforeEach
	public void setup() {
		this.machineSet = new HashSet<>();

		Machine machine1 = Machine.builder().id(1).name("machine1").build();
		machine1.addOperation(Operation.builder().id(1).name("operation1").status(Status.SETUP).build());
		machine1.addOperation(Operation.builder().id(2).name("operation2").status(Status.ENDING_PRODUCTION).build());
		Machine machine2 = Machine.builder().id(2).name("machine2").build();
		machine2.addOperation(Operation.builder().id(3).name("operation3").status(Status.IN_PRODUCTION).build());
		machine2.addOperation(Operation.builder().id(4).name("operation4").status(Status.OVER_PRODUCTION).build());

		this.machineSet.add(machine1);
		this.machineSet.add(machine2);

		this.machineDTOSet = new HashSet<>();

		MachineDTO machineDTO1 = new MachineDTO(1, "machine1");
		machineDTO1.addOperation(new OperationDTO(1, "operation1", Status.SETUP));
		machineDTO1.addOperation(new OperationDTO(2, "operation2", Status.ENDING_PRODUCTION));

		MachineDTO machineDTO2 = new MachineDTO(2, "machine2");
		machineDTO2.addOperation(new OperationDTO(3, "operation3", Status.IN_PRODUCTION));
		machineDTO2.addOperation(new OperationDTO(4, "operation5", Status.OVER_PRODUCTION));

		this.machineDTOSet.add(machineDTO1);
		this.machineDTOSet.add(machineDTO2);
	}

	@Test
	@DisplayName("Testing getting machine joining operations list with dummy values")
	public void testGetMachineListFilled() {

		Mockito.when(machineRepository.findAll()).thenReturn(this.machineSet);

		Mockito.when(mapper.mapMachineSetToMachineDtoSet(this.machineSet)).thenReturn(machineDTOSet);

		Set<MachineDTO> machineList = machineServiceImpl.getAllMachines();

		assertEquals(2, machineList.size());
		assertFalse(machineList.isEmpty());
	}

	@Test
	@DisplayName("Testing getting machines With Setup And In, Over and Ending Production Status")
	public void testGetAllMachinesWithSetupAndInOverEndingProductionStatus() {

		Status[] statuses = new Status[] { Status.SETUP, Status.IN_PRODUCTION, Status.OVER_PRODUCTION, Status.ENDING_PRODUCTION };

		Mockito.when(machineRepository.findMachinesWithSpecificProductionOrder(statuses))
				.thenReturn(this.machineSet);

		when(mapper.mapMachineSetToMachineDtoSet(this.machineSet)).thenReturn(machineDTOSet);

		Set<MachineDTO> machineList = machineServiceImpl.getAllMachinesWithSetupAndInOverEndingProductionStatus();

		assertEquals(2, machineList.size());
		assertFalse(machineList.isEmpty());
	}

}
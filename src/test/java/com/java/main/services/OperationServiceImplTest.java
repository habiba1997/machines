package com.java.main.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.java.main.error.ConflictException;
import com.java.main.error.NotFoundException;
import com.java.main.mappers.OperationMapper;
import com.java.main.models.Material;
import com.java.main.models.MeasuredValue;
import com.java.main.models.Operation;
import com.java.main.models.Status;
import com.java.main.models.dtos.OperationDTO;
import com.java.main.models.dtos.operation.OperationDTOWithMaterialMachine;
import com.java.main.repositories.MaterialRepository;
import com.java.main.repositories.OperationRepository;

@RunWith(MockitoJUnitRunner.class)
public class OperationServiceImplTest {

	public static final String OPERATION_NAME = "store";
	@Mock
	private OperationRepository operationRepository;

	@Spy
	private OperationMapper mapper = Mappers.getMapper(OperationMapper.class);

	@InjectMocks
	private OperationServiceImpl operationServiceImpl;

	@Mock
	private MaterialRepository materialRepository;

	Status[] statuses;
	Operation operation;
	Operation operationWithSetupStatus;
	Set<Operation> operationSet;
	Set<OperationDTO> operationDTOSet;
	OperationDTOWithMaterialMachine operationDTOWithMaterialMachine;

	@Before
	public void setup() {
		statuses = new Status[] { Status.SETUP, Status.IN_PRODUCTION, Status.OVER_PRODUCTION, Status.ENDING_PRODUCTION };
		operation = Operation.builder().id(1).name(OPERATION_NAME).status(Status.PLANNED).build();

		operationWithSetupStatus = Operation.builder().id(1).name("label").status(Status.SETUP).build();
		operationWithSetupStatus.setMaterial(Material.builder().id(1).name("plastic").measuredValue(new MeasuredValue(10, "kilo")).percentageColor(false).build());

		operationSet = new HashSet<>();
		operationSet.add(Operation.builder().id(1).name(OPERATION_NAME).status(Status.ENDING_PRODUCTION).build());

//		operationDTOSet = new HashSet<>();
//		operationDTOSet.add(OperationDTO.builder().id(1).name(OPERATION_NAME).status(Status.ENDING_PRODUCTION).build());
//
//		operationDTOWithMaterialMachine = OperationDTOWithMaterialMachine.builder().name(OPERATION_NAME).build();

	}

	@Test
	public void testFindOperationByStatusIn() {
		Mockito.when(operationRepository.findByStatusIn(statuses))
				.thenReturn(this.operationSet);

		when(mapper.mapOperationsTOSetOfOperationDTOWithMaterialMachine(this.operationSet)).thenCallRealMethod();

		Set<OperationDTO> operationDTOSSet = operationServiceImpl.getAllOperationsWithSetupAndInOverEndingProductionStatus();
		assertEquals(1, operationDTOSSet.size());
		assertFalse(operationDTOSSet.isEmpty());
	}

	@Test
	public void testGetOperationById() {
		Mockito.when(operationRepository.findOperationById(1))
				.thenReturn(this.operation);

		when(mapper.operationToOperationDTOWithMaterialMachine(this.operation)).thenCallRealMethod();
//		when(mapper.operationToOperationDTOWithMaterialMachine(this.operation)).thenReturn(operationDTOWithMaterialMachine);

		OperationDTOWithMaterialMachine actualResponse = operationServiceImpl.getOperationById(1);

		assertEquals(actualResponse.getName(), OPERATION_NAME);
	}

	@Test
	public void testNotFoundExceptionThrown() {
		Mockito.when(operationRepository.findOperationById(1))
				.thenReturn(null);

		assertThatThrownBy(() -> operationServiceImpl.getOperationById(1))
				.isInstanceOf(NotFoundException.class)
				.hasMessageContaining("Operation required doesn't exist");

	}

	@Test
	public void testGetOperationIfWithSetupInOverEndingProductionOrder() {
		Mockito.when(operationRepository.findOperationById(1))
				.thenReturn(operationWithSetupStatus);

		OperationDTOWithMaterialMachine actualResponse = operationServiceImpl.getOperationIfWithSetupInOverEndingProductionOrder(1);

		assertEquals(actualResponse.getName(), "label");
	}

	@Test
	public void testGetOperationThrowsConflictException() {
		Mockito.when(operationRepository.findOperationById(1))
				.thenReturn(operation);

		assertThatThrownBy(() -> operationServiceImpl.getOperationIfWithSetupInOverEndingProductionOrder(1))
				.isInstanceOf(ConflictException.class)
				.hasMessageContaining("No production order step is running on this machine");

	}

	@Test
	public void testTogglePercentageColor() {
		Mockito.when(operationRepository.findOperationById(1))
				.thenReturn(operationWithSetupStatus);

		Mockito.when(materialRepository.save(operationWithSetupStatus.getMaterial())).thenReturn(operationWithSetupStatus.getMaterial());

		OperationDTOWithMaterialMachine actualResponse = operationServiceImpl.togglePercentageColor(1);

		assertTrue(actualResponse.getMaterial().isPercentageColor());
	}

	@Test
	public void testTogglePercentageColorWithConflict() {
		Mockito.when(operationRepository.findOperationById(1))
				.thenReturn(operation);

		assertThatThrownBy(() -> operationServiceImpl.togglePercentageColor(1))
				.isInstanceOf(ConflictException.class)
				.hasMessageContaining("No production order step is running on this machine");
	}

	@Test
	public void testArrayList() {
		ArrayList<Integer> list = spy(ArrayList.class);
		when(list.size()).thenReturn(5);
		list.add(1);
		assertEquals(list.size(), 5);
	}

}
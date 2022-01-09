//package com.java.main.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.java.main.event.EventPublisher;
//import com.java.main.models.enums.Status;
//import com.java.main.DTOS.MachineDTO;
//import com.java.main.DTOS.OperationDTO;
//import com.java.main.services.MachineServiceImpl;
//
//@RunWith(MockitoJUnitRunner.class)
//public class MachineEntityControllerTest {
//
//	@Mock
//	private MachineServiceImpl machineServiceImpl;
//
//	@InjectMocks
//	private MachineController controller;
//
//	@Mock
//	private EventPublisher eventPublisher;
//
//	Set<MachineDTO> machineDTOSet;
//
//	@Before
//	public void setUp() {
//		this.machineDTOSet = new HashSet<>();
//
//		MachineDTO machine1 = new MachineDTO(1, "machine1");
//		machine1.addOperation(new OperationDTO(1, "operation1", Status.SETUP));
//		machine1.addOperation(new OperationDTO(1, "operation1", Status.ARCHIVE));
//
//		MachineDTO machine2 = new MachineDTO(2, "machine2");
//		machine2.addOperation(new OperationDTO(1, "operation1", Status.IN_PRODUCTION));
//		machine2.addOperation(new OperationDTO(1, "operation1", Status.OVER_PRODUCTION));
//
//		this.machineDTOSet.add(machine1);
//		this.machineDTOSet.add(machine2);
//	}
//
//	@Test
//	public void testGetMachinesUrl() {
//
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//		Mockito.when(machineServiceImpl.getAllMachines()).thenReturn(this.machineDTOSet);
//
//		ResponseEntity<Set<MachineDTO>> responseEntity = controller.getAllMachines();
//
//		assertEquals(responseEntity.getStatusCodeValue(), 200);
//		assertNotNull(responseEntity.getBody());
//		assertEquals(2, responseEntity.getBody().size());
//
//	}
//
//	@Test
//	public void testGetAllMachinesSpecificProductionOrder() {
//
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//		Mockito.when(machineServiceImpl.getAllMachinesWithSetupAndInOverEndingProductionStatus()).thenReturn(this.machineDTOSet);
//
//		ResponseEntity<Set<MachineDTO>> responseEntity = controller.getAllMachinesSpecificProductionOrder();
//
//		assertEquals(responseEntity.getStatusCodeValue(), 200);
//		assertNotNull(responseEntity.getBody());
//		assertEquals(2, responseEntity.getBody().size());
//
//	}
//
//}
//package com.java.main.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
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
//import com.java.main.DTOS.OperationDTO;
//import com.java.main.DTOS.operation.OperationDTOWithMaterialMachine;
//import com.java.main.services.OperationServiceImpl;
//
//@RunWith(MockitoJUnitRunner.class)
//public class OperationEntityControllerTest {
//
//	@Mock
//	private OperationServiceImpl operationServiceImpl;
//
//	@InjectMocks
//	private OperationController controller;
//
//	Set<OperationDTO> operationDTOSet;
//	OperationDTOWithMaterialMachine operationDTOWithMaterialMachine;
//
//	@Before
//	public void setUp() {
//		operationDTOWithMaterialMachine = new OperationDTOWithMaterialMachine();
//		operationDTOSet = new HashSet<>();
//	}
//
//	@Test
//	public void testGetAllOperationsWithSetupAndInOverEndingProductionStatus() {
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//		Mockito.when(operationServiceImpl.getAllOperationsWithSetupAndInOverEndingProductionStatus())
//				.thenReturn(this.operationDTOSet);
//
//		ResponseEntity<Set<OperationDTO>> responseEntity = controller.getAllOperationsWithSetupAndInOverEndingProductionStatus();
//		assertEquals(responseEntity.getStatusCodeValue(), 200);
//	}
//
//	@Test
//	public void testGetOperationById() {
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//		Mockito.when(operationServiceImpl.getOperationById(1))
//				.thenReturn(this.operationDTOWithMaterialMachine);
//
//		ResponseEntity<OperationDTOWithMaterialMachine> responseEntity = controller.getOperationById(1);
//		assertEquals(responseEntity.getStatusCodeValue(), 200);
//	}
//
//	@Test
//	public void testTogglePercentageColor() {
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//		Mockito.when(operationServiceImpl.togglePercentageColor(1))
//				.thenReturn(this.operationDTOWithMaterialMachine);
//
//		ResponseEntity<OperationDTOWithMaterialMachine> responseEntity = controller.togglePercentageColor(1);
//		assertEquals(responseEntity.getStatusCodeValue(), 200);
//	}
//
//	@Test
//	public void testGetOperationIfSpecificProductionOrder() {
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//		Mockito.when(operationServiceImpl.getOperationIfWithSetupInOverEndingProductionOrder(1))
//				.thenReturn(this.operationDTOWithMaterialMachine);
//
//		ResponseEntity<OperationDTOWithMaterialMachine> responseEntity = controller.operationIfSpecificProductionOrder(1);
//		assertEquals(responseEntity.getStatusCodeValue(), 200);
//	}
//
//}
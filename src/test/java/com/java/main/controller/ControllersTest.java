package com.java.main.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.java.main.dtos.Location;
import com.java.main.dtos.Machine;
import com.java.main.dtos.Material;
import com.java.main.dtos.Operation;
import com.java.main.dtos.ProductionOrder;
import com.java.main.profile.SpringProfiles;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = { SpringProfiles.CACHE, SpringProfiles.HSQL, SpringProfiles.TEST })
@Slf4j
public class ControllersTest {
	@Autowired
	private MaterialController materialController;
	@Autowired
	private LocationController locationController;
	@Autowired
	private MachineController machineController;
	@Autowired
	private ProductionOrderController productionOrderController;
	@Autowired
	private OperationController operationController;

	@Before
	public void setUp() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}

	// -------------------- MATERIAL CONTROLLER TEST -------------------------------------
	@Test
	public void getAllMaterials() {
		ResponseEntity<List<Material>> responseEntity = materialController.getAllMaterials();

		assertEquals(responseEntity.getStatusCodeValue(), 200);
		assertNotNull(responseEntity.getBody());
		assertEquals(4, responseEntity.getBody().size());
	}

	@Test
	public void getMaterialByName() {
		ResponseEntity<Material> responseEntity = materialController.getMaterialByName("Plastic");

		assertEquals(responseEntity.getStatusCodeValue(), 200);
		assertNotNull(responseEntity.getBody());
		assertEquals("25 PC", responseEntity.getBody().getMeasuredValue().getText());
	}

	// -------------------- LOCATION CONTROLLER TEST -------------------------------------

	@Test
	public void getAllLocations() {
		ResponseEntity<List<Location>> responseEntity = locationController.getAllLocations();

		assertEquals(responseEntity.getStatusCodeValue(), 200);
		assertNotNull(responseEntity.getBody());
		assertEquals(4, responseEntity.getBody().size());
	}

	@Test
	public void getLocationByName() {
		ResponseEntity<Location> responseEntity = locationController.getLocationByName("mald");

		assertEquals(responseEntity.getStatusCodeValue(), 200);
		assertNotNull(responseEntity.getBody());
		assertEquals("in_door", responseEntity.getBody().getType().getValue());
	}

	// -------------------- MACHINE CONTROLLER TEST -------------------------------------

	@Test
	public void getMachinesList() {
		ResponseEntity<List<Machine>> responseEntity = machineController.getMachinesList();

		assertEquals(responseEntity.getStatusCodeValue(), 200);
		assertNotNull(responseEntity.getBody());
		assertEquals(4, responseEntity.getBody().size());
	}

	@Test
	public void getMachinesByLocationName() {
		ResponseEntity<List<Machine>> responseEntity = machineController.getMachinesByLocationName("mald");

		assertEquals(responseEntity.getStatusCodeValue(), 200);
		assertNotNull(responseEntity.getBody());
		assertEquals(1, responseEntity.getBody().size());
		assertEquals("Skid Steer Loader", responseEntity.getBody().get(0).getName());

	}

	// -------------------- OPERATION CONTROLLER TEST -------------------------------------

	@Test
	public void getOperationsInStatus() {
		ResponseEntity<List<Operation>> responseEntity = operationController.getOperationsInStatus("PLANNED");

		assertEquals(responseEntity.getStatusCodeValue(), 200);
		assertNotNull(responseEntity.getBody());
		assertEquals(3, responseEntity.getBody().size());

		responseEntity = operationController.getOperationsInStatus("in_production");

		assertEquals(responseEntity.getStatusCodeValue(), 200);
		assertNotNull(responseEntity.getBody());
		assertEquals(1, responseEntity.getBody().size());
		assertEquals("Shaping", responseEntity.getBody().get(0).getName());
	}

	@Test
	public void getOperationsByProductionOrderName() {
		ResponseEntity<List<Operation>> responseEntity = operationController.getOperationsByProductionOrderName("plastic_formation");

		assertEquals(responseEntity.getStatusCodeValue(), 200);
		assertNotNull(responseEntity.getBody());
		assertEquals(3, responseEntity.getBody().size());
	}

	// -------------------- PRODUCTION ORDER CONTROLLER TEST -------------------------------------

	@Test
	public void getProductionOrderByName() {
		ResponseEntity<ProductionOrder> responseEntity = productionOrderController.getProductionOrderByName("calibrating");

		assertEquals(responseEntity.getStatusCodeValue(), 200);
		assertNotNull(responseEntity.getBody());
		assertEquals(3, responseEntity.getBody().getOperationList().size());
		assertEquals("500 TS", responseEntity.getBody().getPlannedQuantity().getText());
	}

}
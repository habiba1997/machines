package com.java.main.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.java.main.event.EventPublisher;
import com.java.main.models.dtos.MachineDTO;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.models.dtos.MeasuredValueDTO;
import com.java.main.services.MaterialServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MaterialControllerTest {

	@Mock
	private MaterialServiceImpl materialServiceImpl;

	@InjectMocks
	private MaterialController controller;

	@Mock
	private EventPublisher eventPublisher;

	Set<MachineDTO> machineDTOSet;
	Set<MaterialDTO> materialDTOSet;

	@Before
	public void setUp() {
		this.machineDTOSet = new HashSet<>();

		MachineDTO machine1 = new MachineDTO(1, "machine1");
		MachineDTO machine2 = new MachineDTO(2, "machine2");

		this.machineDTOSet.add(machine1);
		this.machineDTOSet.add(machine2);

		this.materialDTOSet = new HashSet<>();

		MaterialDTO material1 = new MaterialDTO(1, "Plastic");
		material1.setMeasuredValue(new MeasuredValueDTO(10, "kilo"));
		materialDTOSet.add(material1);

	}

	@Test
	public void testGetMaterialsUrl() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Mockito.when(materialServiceImpl.getAllMaterials())
				.thenReturn(this.materialDTOSet);

		ResponseEntity<Set<MaterialDTO>> responseEntity = controller.getAllMaterials();

		assertEquals(responseEntity.getStatusCodeValue(), 200);
		assertNotNull(responseEntity.getBody());
		assertEquals(1, responseEntity.getBody().size());

	}

}
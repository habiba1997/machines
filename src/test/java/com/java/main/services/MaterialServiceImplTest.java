package com.java.main.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.java.main.mappers.MaterialMapper;
import com.java.main.models.Material;
import com.java.main.models.MeasuredValue;
import com.java.main.models.Operation;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.models.dtos.MeasuredValueDTO;
import com.java.main.repositories.MaterialRepository;

@RunWith(MockitoJUnitRunner.class)
public class MaterialServiceImplTest {

	@Mock
	private MaterialRepository materialRepository;

	@Mock
	private MaterialMapper mapper;

	@InjectMocks
	private MaterialServiceImpl materialServiceImpl;

	Set<Material> materialSet;
	Set<MaterialDTO> materialDTOSet;

	Material material1;

	@Before
	public void setUp() {
		this.materialSet = new HashSet<>();
		materialDTOSet = new HashSet<>();

		material1 = Material.builder().id(1).name("Plastic").build();
		material1.addOperation(Operation.builder().id(1).name("operation1").build());
		material1.addOperation(Operation.builder().id(2).name("operation2").build());
		material1.setMeasuredValue(new MeasuredValue(10, "kilo"));

		materialSet.add(material1);
		materialDTOSet.add(MaterialDTO.builder().id(1).name("Plastic").measuredValue(new MeasuredValueDTO(10, "kilo")).build());

	}

	@Test
	@DisplayName("Testing getting machine joining operations list with dummy values")
	public void testGetMachineListFilled() {
		Mockito.when(materialRepository.findAll())
				.thenReturn(this.materialSet);

		when(mapper.mapMaterialSetToMaterialDtoSet(this.materialSet)).thenReturn(materialDTOSet);

		materialDTOSet = materialServiceImpl.getAllMaterials();

		assertEquals(1, materialDTOSet.size());
	}

}
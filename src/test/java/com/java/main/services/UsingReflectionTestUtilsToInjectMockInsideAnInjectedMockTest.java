package com.java.main.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.java.main.mappers.MaterialMapper;
import com.java.main.models.Material;
import com.java.main.models.MeasuredValue;
import com.java.main.models.Operation;
import com.java.main.models.dtos.MaterialDTO;
import com.java.main.repositories.MaterialRepository;

@RunWith(MockitoJUnitRunner.class)
public class UsingReflectionTestUtilsToInjectMockInsideAnInjectedMockTest {

	@Mock
	private MaterialRepository materialRepository;

	// Mappers: Factory for obtaining mapper instances
	// When using this factory, mapper types - and any mappers they use - are instantiated by invoking their public no-args constructor.
	// public static <T> T getMapper(Class<T> clazz) Returns an instance of the given mapper type.
	@Spy
	private MaterialMapper materialMapper = Mappers.getMapper(MaterialMapper.class);

	@InjectMocks
	private MaterialServiceImpl materialServiceImpl;

	Set<Material> materialSet;

	Material material1;

	Set<Operation> set;

	@Before
	public void setUp() {
		this.materialSet = new HashSet<>();

		set = new HashSet<>();
		set.add(Operation.builder().id(1).name("operation1").build());
		set.add(Operation.builder().id(2).name("operation2").build());

		material1 = Material.builder()
				.id(1)
				.name("Plastic")
				.measuredValue(
						MeasuredValue.builder()
								.value(10)
								.unit("kilo")
								.build())
				.operations(set)
				.build();

		materialSet.add(material1);

	}

	@Test
	@DisplayName("Testing getting machine joining operations list with dummy values")
	public void testGetMachineListFilled() {
		Mockito.when(materialRepository.findAll())
				.thenReturn(this.materialSet);

		ReflectionTestUtils.setField(materialServiceImpl, "mapper", materialMapper);

		Set<MaterialDTO> materialDTOSet = materialServiceImpl.getAllMaterials();

		assertEquals(1, materialDTOSet.size());

		materialDTOSet.forEach(m -> {
			assertEquals("Plastic", m.getName());
			assertEquals("kilo", m.getMeasuredValue().getUnit());
			assertEquals(10, m.getMeasuredValue().getValue());
		});
	}

}
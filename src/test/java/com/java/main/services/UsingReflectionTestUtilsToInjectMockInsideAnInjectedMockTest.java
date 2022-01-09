//package com.java.main.services;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import com.java.main.models.entity.MaterialEntity;
//import com.java.main.models.entity.OperationEntity;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.runner.RunWith;
//import org.mapstruct.factory.Mappers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import com.java.main.mappers.MaterialMapper;
//import com.java.main.models.helpers.MeasuredValue;
//import com.java.main.DTOS.MaterialDTO;
//import com.java.main.repositories.MaterialRepository;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UsingReflectionTestUtilsToInjectMockInsideAnInjectedMockTest {
//
//	@Mock
//	private MaterialRepository materialRepository;
//
//	// Mappers: Factory for obtaining mapper instances
//	// When using this factory, mapper types - and any mappers they use - are instantiated by invoking their public no-args constructor.
//	// public static <T> T getMapper(Class<T> clazz) Returns an instance of the given mapper type.
//	@Spy
//	private MaterialMapper materialMapper = Mappers.getMapper(MaterialMapper.class);
//
//	@InjectMocks
//	private MaterialServiceImpl materialServiceImpl;
//
//	Set<MaterialEntity> materialEntitySet;
//
//	MaterialEntity materialEntity1;
//
//	Set<OperationEntity> set;
//
//	@Before
//	public void setUp() {
//		this.materialEntitySet = new HashSet<>();
//
//		set = new HashSet<>();
//		set.add(OperationEntity.builder().id(1).name("operation1").build());
//		set.add(OperationEntity.builder().id(2).name("operation2").build());
//
//		materialEntity1 = MaterialEntity.builder()
//				.id(1)
//				.name("Plastic")
//				.measuredValue(
//						MeasuredValue.builder()
//								.value(10)
//								.unit("kilo")
//								.build())
//				.operations(set)
//				.build();
//
//		materialEntitySet.add(materialEntity1);
//
//	}
//
//	@Test
//	@DisplayName("Testing getting machineEntity joining operationEntities list with dummy values")
//	public void testGetMachineListFilled() {
//		Mockito.when(materialRepository.findAll())
//				.thenReturn(this.materialEntitySet);
//
//		ReflectionTestUtils.setField(materialServiceImpl, "mapper", materialMapper);
//
//		Set<MaterialDTO> materialDTOSet = materialServiceImpl.getAllMaterials();
//
//		assertEquals(1, materialDTOSet.size());
//
//		materialDTOSet.forEach(m -> {
//			assertEquals("Plastic", m.getName());
//			assertEquals("kilo", m.getMeasuredValue().getUnit());
//			assertEquals(10, m.getMeasuredValue().getValue());
//		});
//	}
//
//}
//package com.java.main.services;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import com.java.main.models.entity.MaterialEntity;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import com.java.main.mappers.MaterialMapper;
//import com.java.main.models.helpers.MeasuredValue;
//import com.java.main.models.entity.OperationEntity;
//import com.java.main.DTOS.MaterialDTO;
//import com.java.main.DTOS.MeasuredValueDTO;
//import com.java.main.repositories.MaterialRepository;
//
//@RunWith(MockitoJUnitRunner.class)
//public class MaterialEntityServiceImplTest {
//
//	@Mock
//	private MaterialRepository materialRepository;
//
//	@Mock
//	private MaterialMapper mapper;
//
//	@InjectMocks
//	private MaterialServiceImpl materialServiceImpl;
//
//	Set<MaterialEntity> materialEntitySet;
//	Set<MaterialDTO> materialDTOSet;
//
//	MaterialEntity materialEntity1;
//
//	@Before
//	public void setUp() {
//		this.materialEntitySet = new HashSet<>();
//		materialDTOSet = new HashSet<>();
//
//		materialEntity1 = MaterialEntity.builder().id(1).name("Plastic").build();
//		materialEntity1.addOperation(OperationEntity.builder().id(1).name("operation1").build());
//		materialEntity1.addOperation(OperationEntity.builder().id(2).name("operation2").build());
//		materialEntity1.setMeasuredValue(new MeasuredValue(10, "kilo"));
//
//		materialEntitySet.add(materialEntity1);
//		materialDTOSet.add(MaterialDTO.builder().id(1).name("Plastic").measuredValue(new MeasuredValueDTO(10, "kilo")).build());
//
//	}
//
//	@Test
//	@DisplayName("Testing getting machineEntity joining operationEntities list with dummy values")
//	public void testGetMachineListFilled() {
//		Mockito.when(materialRepository.findAll())
//				.thenReturn(this.materialEntitySet);
//
//		when(mapper.mapMaterialSetToMaterialDtoSet(this.materialEntitySet)).thenReturn(materialDTOSet);
//
//		materialDTOSet = materialServiceImpl.getAllMaterials();
//
//		assertEquals(1, materialDTOSet.size());
//	}
//
//}
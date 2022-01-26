package com.java.main.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.java.main.dtos.Machine;
import com.java.main.impl.MachineServiceImpl;
import com.java.main.mappers.MachineMapper;
import com.java.main.repositories.MachineRepository;
import com.java.main.services.testdata.MachineTestData;

@RunWith(MockitoJUnitRunner.class)
public class MachineServiceTest {

	public static final String LOCATION_NAME = "mald";
	@Mock
	private MachineRepository machineRepository;

	@Mock
	private LocationService locationService;

	@Spy
	private MachineMapper mapper = Mappers.getMapper(MachineMapper.class);

	@InjectMocks
	private MachineService machineService = new MachineServiceImpl();

	@Before
	public void setup() {
		when(machineRepository.findAll()).thenReturn(List.of(MachineTestData.generateMachineEntity()));
		when(machineRepository.findMachinesByLocationName(LOCATION_NAME)).thenReturn(List.of(MachineTestData.generateMachineEntity()));
		ReflectionTestUtils.setField(mapper, "locationService", locationService);
	}

	@Test
	@DisplayName("Testing Finding all machines and its mapping")
	public void testFindAll() {
		List<Machine> machineList = machineService.findAll();

		assertEquals(1, machineList.size());
		assertNotNull(machineList.get(0));
	}

	@Test
	@DisplayName("Testing finding machines by Locations")
	public void testFindByLocationName() {
		List<Machine> machineList = machineService.findByLocationName(LOCATION_NAME);

		assertEquals(1, machineList.size());
		assertNotNull(machineList.get(0));
	}

}
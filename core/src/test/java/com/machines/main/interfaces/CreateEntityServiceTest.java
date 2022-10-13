package com.machines.main.interfaces;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.machines.main.impl.CreateEntityService;
import com.machines.main.logic.CreateEntityLogic;
import com.machines.main.request.LocationRequest;
import com.machines.main.services.LocationService;
import com.machines.main.services.MaterialService;
import com.machines.main.services.ProductionOrderService;
import com.machines.main.trigger.EventPublisher;

@RunWith(MockitoJUnitRunner.class)
public class CreateEntityServiceTest {

	@Mock
	private EventPublisher eventPublisher;

	@InjectMocks
	private CreateEntityService saveService;

	@Mock
	private LocationService locationService;

	@Spy
	private CreateEntityLogic createEntityLogic = new CreateEntityLogic();

	@Mock
	private MaterialService materialService;

	@Mock
	private ProductionOrderService productionOrderService;
	@Test
	public void saveLocation() {
		saveService.saveLocation(LocationRequest.builder().name("loc1").temp(true).type("on_door").build());
		verify(eventPublisher).publishEvents(anyList());
	}
}
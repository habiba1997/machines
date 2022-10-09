package com.java.main.interfaces;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.java.main.controller.request.LocationRequest;
import com.java.main.impl.CreateEntityService;
import com.java.main.interfaces.services.LocationService;
import com.java.main.interfaces.services.MaterialService;
import com.java.main.interfaces.services.ProductionOrderService;
import com.java.main.logic.CreateEntityLogic;
import com.java.main.trigger.EventPublisher;

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
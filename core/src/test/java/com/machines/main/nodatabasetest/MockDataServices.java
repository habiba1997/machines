package com.machines.main.nodatabasetest;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.machines.main.profile.SpringProfiles;
import com.machines.main.services.LocationService;
import com.machines.main.services.MachineService;
import com.machines.main.services.MaterialService;
import com.machines.main.services.OperationService;
import com.machines.main.services.ProductionOrderService;

@Component
@Slf4j
@Profile({ SpringProfiles.NO_DATABASE })
public class MockDataServices {

	@MockBean
	private MachineService machineService;

	@MockBean
	private MaterialService materialService;

	@MockBean
	private OperationService operationService;

	@MockBean
	private ProductionOrderService productionOrderService;

	@MockBean
	private LocationService locationService;

	/**
	 * We define some useful response.
	 */
	@PostConstruct
	public void before() {
		log.debug("mock dataService layer");
	}

	@PostConstruct
	public void init() {
		log.debug("Mock all service exposed by data-access module");
	}
}

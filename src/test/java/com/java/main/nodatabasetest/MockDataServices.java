package com.java.main.nodatabasetest;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.java.main.interfaces.services.LocationService;
import com.java.main.interfaces.services.MachineService;
import com.java.main.interfaces.services.MaterialService;
import com.java.main.interfaces.services.OperationService;
import com.java.main.interfaces.services.ProductionOrderService;
import com.java.main.profile.SpringProfiles;

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

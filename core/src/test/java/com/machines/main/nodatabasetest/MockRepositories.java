package com.machines.main.nodatabasetest;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.machines.main.profile.SpringProfiles;
import com.machines.main.repositories.LocationRepository;
import com.machines.main.repositories.MachineRepository;
import com.machines.main.repositories.MaterialRepository;
import com.machines.main.repositories.OperationRepository;
import com.machines.main.repositories.ProductionOrderRepository;

@Component
@Slf4j
@Profile({ SpringProfiles.NO_DATABASE })
public class MockRepositories {
	@MockBean
	private MachineRepository machineRepository;
	@MockBean
	private MaterialRepository materialRepository;
	@MockBean
	private OperationRepository operationRepository;
	@MockBean
	private LocationRepository locationRepository;
	@MockBean
	private ProductionOrderRepository productionOrderRepository;

	/**
	 * We define some useful response.
	 */
	@PostConstruct
	public void init() {
		log.debug("Mock All Repositories");
	}

}

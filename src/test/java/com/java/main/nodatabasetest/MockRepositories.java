package com.java.main.nodatabasetest;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.java.main.profile.SpringProfiles;
import com.java.main.repositories.LocationRepository;
import com.java.main.repositories.MachineRepository;
import com.java.main.repositories.MaterialRepository;
import com.java.main.repositories.OperationRepository;
import com.java.main.repositories.ProductionOrderRepository;

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
		log.info("Mock All Repositories");
	}

}

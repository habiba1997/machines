package com.machines.main.cache;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.machines.main.impl.MachineOperationServiceImpl;
import com.machines.main.impl.MaterialServiceImpl;
import com.machines.main.impl.cache.LocationServiceImpl;
import com.machines.main.impl.cache.MachineServiceImpl;
import com.machines.main.impl.cache.OperationServiceImpl;
import com.machines.main.profile.SpringProfiles;

/**
 * Preload objects in cache on startup.
 */
@Component
@Slf4j
@Profile({ SpringProfiles.NO_TEST })
@ConditionalOnProperty(name = "load.cache.on.startup", havingValue = "true", matchIfMissing = true)
public class PreLoadObjectInCache {

	@Autowired
	private LocationServiceImpl locationService;
	@Autowired
	private MachineServiceImpl machineService;
	@Autowired
	private OperationServiceImpl operationService;
	@Autowired
	private MaterialServiceImpl materialService;
	@Autowired
	private MachineOperationServiceImpl machineOperationService;

	@PostConstruct
	public void init() {
		loadMachineOperationCache();
		loadLocationCache();
		loadMachineCache();
		loadMaterialCache();
		loadOperationCache();
	}

	private void loadMaterialCache() {
		if (!materialService.isCachePopulated()) {
			materialService.forceUpdateCacheFromDb();
		}
	}

	private void loadMachineOperationCache() {
		if (!machineOperationService.isCachePopulated()) {
			machineOperationService.forceUpdateCacheFromDb();
		}
	}

	private void loadOperationCache() {
		if (!operationService.isCachePopulated()) {
			operationService.forceUpdateCacheFromDb();
		}
	}

	private void loadMachineCache() {
		if (!machineService.isCachePopulated()) {
			machineService.forceUpdateCacheFromDb();
		}
	}

	@Transactional
	public void loadLocationCache() {
		if (!locationService.isCachePopulated()) {
			locationService.forceUpdateCacheFromDb();
		}
	}
}

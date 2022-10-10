package com.machines.main.trigger.listeners;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.machines.main.mappers.OperationMapper;
import com.machines.main.models.entity.MaterialEntity;
import com.machines.main.models.entity.OperationEntity;
import com.machines.main.models.entity.ProductionOrderEntity;
import com.machines.main.repositories.MaterialRepository;
import com.machines.main.repositories.OperationRepository;
import com.machines.main.repositories.ProductionOrderRepository;
import com.machines.main.trigger.events.OperationCreateEvent;

@Slf4j
@Component
public class OperationCreateEventListener {

	@Autowired
	private OperationRepository operationRepository;

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private OperationMapper operationMapper;

	@Autowired
	private ProductionOrderRepository productionOrderRepository;

	@Autowired
	private MaterialRepository materialRepository;

	@EventListener
	public void onApplicationEvent(final OperationCreateEvent event) {
		log.debug("Event OperationCreateEvent ......");
		OperationEntity operationEntity = operationMapper.toEntity(event.getOperation());
		MaterialEntity materialEntity = materialRepository.findByName(event.getOperation().getMaterial().getName());
		operationEntity.setMaterialEntity(materialEntity);
		ProductionOrderEntity productionOrderEntity = productionOrderRepository.findByName(event.getOperation().getProductionOrder().getName());
		operationEntity.setProductionOrderEntity(productionOrderEntity);

		operationRepository.save(operationEntity);
	}
}

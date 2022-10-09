package com.java.main.trigger.listeners;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.java.main.mappers.OperationMapper;
import com.java.main.models.entity.MaterialEntity;
import com.java.main.models.entity.OperationEntity;
import com.java.main.models.entity.ProductionOrderEntity;
import com.java.main.repositories.MaterialRepository;
import com.java.main.repositories.OperationRepository;
import com.java.main.repositories.ProductionOrderRepository;
import com.java.main.trigger.events.OperationCreateEvent;

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

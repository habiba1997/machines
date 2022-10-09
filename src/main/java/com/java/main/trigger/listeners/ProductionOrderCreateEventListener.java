package com.java.main.trigger.listeners;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.java.main.mappers.ProductionOrderMapper;
import com.java.main.models.entity.MaterialEntity;
import com.java.main.models.entity.ProductionOrderEntity;
import com.java.main.repositories.MaterialRepository;
import com.java.main.repositories.ProductionOrderRepository;
import com.java.main.trigger.events.ProductionOrderCreateEvent;

@Slf4j
@Component
public class ProductionOrderCreateEventListener {

	@Autowired
	private ProductionOrderRepository productionOrderRepository;

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private ProductionOrderMapper productionOrderMapper;

	@Autowired
	private MaterialRepository materialRepository;

	@EventListener
	public void onApplicationEvent(final ProductionOrderCreateEvent event) {
		log.debug("Event ProductionOrderCreateEvent ......");
		ProductionOrderEntity entity = productionOrderMapper.toEntity(event.getProductionOrder());
		MaterialEntity materialEntity = materialRepository.findByName(event.getProductionOrder().getMaterial().getName());
		entity.setMaterialEntity(materialEntity);
		productionOrderRepository.save(entity);
	}
}

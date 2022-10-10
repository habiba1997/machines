package com.machines.main.testdata;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import com.machines.main.dtos.ProductionOrder;
import com.machines.main.models.entity.ProductionOrderEntity;
import com.machines.main.models.helpers.MeasuredValue;
import com.machines.main.models.helpers.MesUnit;

public class ProductionOrderTestData {

	public static final String PRODUCTION_ORDER_NAME = "ProductionOrder1";
	public static final long PRODUCTION_ORDER_KEY = 34012480L;
	public static final MesUnit MES_UNIT = MesUnit.KILO_GRAM;
	public static final MeasuredValue MEASURED_VALUE = new MeasuredValue(BigDecimal.TEN, MES_UNIT);

	public static ProductionOrder generateProductionOrder() {
		return ProductionOrder.builder()
				.name(PRODUCTION_ORDER_NAME)
				.material(MaterialTestData.generateMaterial())
				.plannedQuantity(MEASURED_VALUE)
				.operationList(List.of(OperationTestData.generateOperation()))
				.startDate(ZonedDateTime.now())
				.build();
	}

	public static ProductionOrderEntity generateProductionOrderEntity() {
		return ProductionOrderEntity.builder()
				.key(PRODUCTION_ORDER_KEY)
				.name(PRODUCTION_ORDER_NAME)
				.materialEntity(MaterialTestData.generateMaterialEntity())
				.plannedQuantity(MEASURED_VALUE)
				.operationEntityList(List.of(OperationTestData.generateOperationEntity()))
				.startDate(LocalDateTime.now())
				.build();

	}
}

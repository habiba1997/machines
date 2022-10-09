package com.java.main.interfaces.testdata;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import com.java.main.dtos.Operation;
import com.java.main.dtos.Order;
import com.java.main.models.entity.OperationEntity;
import com.java.main.models.entity.ProductionOrderEntity;
import com.java.main.models.enums.Status;
import com.java.main.models.helpers.MeasuredValue;
import com.java.main.models.helpers.MesUnit;

public class OperationTestData {

	public static final String OPERATION_NAME = "OP1";
	public static final long OPERATION_ID = 1248032L;
	public static final MesUnit MES_UNIT = MesUnit.KILO_GRAM;
	public static final MeasuredValue MEASURED_VALUE = new MeasuredValue(BigDecimal.TEN, MES_UNIT);
	public static final String PRODUCTION_ORDER_NAME = "production1";

	public static Operation generateOperation() {
		Order productionOrder = Order.builder()
				.material(MaterialTestData.generateMaterial())
				.name(PRODUCTION_ORDER_NAME)
				.plannedQuantity(MEASURED_VALUE)
				.startDate(ZonedDateTime.now())
				.build();

		return Operation.builder()
				.name(OPERATION_NAME)
//				.key(OPERATION_ID)
				.material(MaterialTestData.generateMaterial())
				.status(Status.PLANNED)
				.productionOrder(productionOrder)
				.build();
	}

	public static OperationEntity generateOperationEntity() {
		ProductionOrderEntity productionOrderEntity = ProductionOrderEntity.builder()
				.key(ProductionOrderTestData.PRODUCTION_ORDER_KEY)
				.name(ProductionOrderTestData.PRODUCTION_ORDER_NAME)
				.materialEntity(MaterialTestData.generateMaterialEntity())
				.plannedQuantity(MEASURED_VALUE)
				.startDate(LocalDateTime.now())
				.build();

		return OperationEntity.builder()
				.name(OPERATION_NAME)
				.key(OPERATION_ID)
				.materialEntity(MaterialTestData.generateMaterialEntity())
				.status("suspended")
				.productionOrderEntity(productionOrderEntity)
				.build();

	}
}

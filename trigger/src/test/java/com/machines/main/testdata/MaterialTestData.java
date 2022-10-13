package com.machines.main.testdata;

import java.math.BigDecimal;

import com.machines.main.dtos.Material;
import com.machines.main.models.entity.MaterialEntity;
import com.machines.main.models.helpers.MeasuredValue;
import com.machines.main.models.helpers.MesUnit;

public class MaterialTestData {

	public static final String MATERIAL_NAME = "material1";
	public static final long MATERIAL_KEY = 34012480L;
	public static final MesUnit MES_UNIT = MesUnit.KILO_GRAM;
	public static final MeasuredValue MEASURED_VALUE = new MeasuredValue(BigDecimal.TEN, MES_UNIT);

	public static Material generateMaterial() {
		return Material.builder()
				.name(MATERIAL_NAME)
				.measuredValue(MEASURED_VALUE)
				.baseUnit(MES_UNIT)
				.build();
	}

	public static MaterialEntity generateMaterialEntity() {
		return MaterialEntity.builder()
				.key(MATERIAL_KEY)
				.name(MATERIAL_NAME)
				.measuredValue(MEASURED_VALUE)
				.baseUnit(MES_UNIT)
				.build();

	}
}

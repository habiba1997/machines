package com.java.main.models.helpers;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeasuredValue implements Serializable {

	private BigDecimal value;
	private BigDecimal baseUnitValue;
	private MesUnit unit;

	public MeasuredValue(final BigDecimal value, final MesUnit unit) {
		this.value = value;
		this.unit = unit;
	}

	public String getText() {
		return this.value + " " + this.getUnit().getUnit();
	}
}

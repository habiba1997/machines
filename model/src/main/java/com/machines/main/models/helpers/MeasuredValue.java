package com.machines.main.models.helpers;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class MeasuredValue implements Serializable {

	private BigDecimal value;
	private BigDecimal baseUnitValue;
	private MesUnit unit;

	public MeasuredValue(final BigDecimal value, final MesUnit unit) {
		this.value = value;
		this.unit = unit;
	}

	public MeasuredValue(final String string) {
		final String[] values = string.split(" ");
		this.value = BigDecimal.valueOf(Long.parseLong(values[0]));
		this.unit = MesUnit.valueOf(values[1]);
	}

	public String getText() {
		return this.value + " " + this.getUnit().getUnit();
	}
}

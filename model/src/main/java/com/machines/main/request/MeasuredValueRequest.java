package com.machines.main.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeasuredValueRequest {
	private @NotNull String unit;
	private @NotNull BigDecimal value;
}

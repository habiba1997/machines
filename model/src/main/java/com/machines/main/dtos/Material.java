package com.machines.main.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.machines.main.cache.CacheableElement;
import com.machines.main.models.helpers.MeasuredValue;
import com.machines.main.models.helpers.MesUnit;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Material implements CacheableElement<String> {

	private String name;
	private MeasuredValue measuredValue;
	private MesUnit baseUnit;

	@Override
	public String getCacheKeyElement() {
		return this.name;
	}
}

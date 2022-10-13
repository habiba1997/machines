package com.machines.main.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.machines.main.cache.CacheableElement;
import com.machines.main.models.enums.Status;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Operation implements CacheableElement<String> {
	private String name;
	private Status status;
	private Material material;
	private Order productionOrder;

	@Override
	public String getCacheKeyElement() {
		return this.name;
	}
}

package com.machines.main.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.machines.main.cache.CacheableElement;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachineOperation implements CacheableElement<String> {
	private Machine machine;
	private Operation operation;

	@Override
	public String getCacheKeyElement() {
		return this.machine.getName() + '-' + this.operation.getName();
	}
}

package com.machines.main.mappers;

import java.util.List;

public interface ModelMapper<E, M> {
	M toModel(E entity);

	List<M> toModels(List<E> entities);
}

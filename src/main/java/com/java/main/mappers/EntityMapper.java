package com.java.main.mappers;

import java.util.List;

public interface EntityMapper<E, M> {
	E toEntity(M model);

	List<E> toEntities(List<M> models);
}

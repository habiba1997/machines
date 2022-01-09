package com.java.main.converters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This converter allow a quick to convert database string/enum to un understandable String/Enum/Class.
 *
 * @param <M> Model class
 * @param <E> Entity class
 */
public class ModelEntityConverter<M, E> {

	private Map<M, E> mapModelToEntity = new HashMap<>();
	private Map<E, M> mapEntityToModel = new HashMap<>();
	private boolean caseSensitive = true;
	private E defaultEntity = null;
	private M defaultModel = null;

	public static <D, J> ModelEntityConverter<D, J> build() {
		return new ModelEntityConverter<>();
	}

	public ModelEntityConverter<M, E> and(final M m, final E e) {
		mapModelToEntity.put(m, e);
		mapEntityToModel.put(e, m);
		return this;
	}

	public ModelEntityConverter<M, E> defaultEntity(final E e) {
		this.defaultEntity = e;
		return this;
	}

	public ModelEntityConverter<M, E> defaultDomain(final M e) {
		this.defaultModel = e;
		return this;
	}

	public M toModel(final E entity) {
		M m = mapEntityToModel.get(entity);
		if (!caseSensitive) {
			Map.Entry<E, M> entryModel = mapEntityToModel.entrySet().stream().filter(domain -> domain.getKey().toString().equalsIgnoreCase(entity.toString())).findAny().orElse(null);
			m = (entryModel != null) ? entryModel.getValue() : null;
		}
		if (m == null) {
			m = defaultModel;
		}
		return m;
	}

	public List<M> toModels(final List<E> entities) {
		return entities != null ? entities.stream()
				.map(this::toModel)
				.collect(Collectors.toList()) : null;
	}

	public E toEntity(final M domain) {
		E e = mapModelToEntity.get(domain);
		if (!caseSensitive) {
			Map.Entry<M, E> entryEntity = mapModelToEntity.entrySet().stream().filter(jpa -> jpa.getKey().toString().equalsIgnoreCase(domain.toString())).findAny().orElse(null);
			e = (entryEntity != null) ? entryEntity.getValue() : null;
		}
		if (e == null) {
			e = defaultEntity;
		}
		return e;
	}

	public List<E> toEntities(final List<M> domains) {
		return domains != null ? domains.stream()
				.map(this::toEntity)
				.collect(Collectors.toList()) : null;
	}

	public ModelEntityConverter<M, E> ignoreCase() {
		this.caseSensitive = false;
		return this;
	}

}

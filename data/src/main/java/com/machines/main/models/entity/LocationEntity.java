package com.machines.main.models.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;

import com.machines.main.converters.BooleanConverter;
import com.machines.main.converters.LocationTypeEnumConverter;
import com.machines.main.models.enums.LocationType;

// Caching Regions are specific region into the cache provider that might store entities, collection or queries.
// Each cache region resides in a specific cache namespace and has its own lifetime configuration.
@Entity
@Builder
@Table(name = "location")
// For each entity class, Hibernate will use a separate cache region to store state of instances for that class.
// The region name is the fully qualified class name.
@Cacheable
// read and write to normally read ad cache element on read not specific transaction
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long key;

//	 natural id annotation produce the sql logs of alter ( don't know the reason)
	@NaturalId
	@Column(name = "name")
	private String name;

	@Column(name = "temporary")
	@Convert(converter = BooleanConverter.class)
	private boolean temp;

	@Column(name = "type")
	@Convert(converter = LocationTypeEnumConverter.class)
	private LocationType type;

}

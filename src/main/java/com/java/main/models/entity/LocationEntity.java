package com.java.main.models.entity;

import java.io.Serializable;

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

import com.java.main.converters.BooleanConverter;
import com.java.main.converters.LocationTypeEnumConverter;
import com.java.main.models.enums.LocationType;

@Entity
@Builder
@Table(name = "location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int key;

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

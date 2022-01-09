package com.java.main.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.java.main.converters.BooleanConverter;

@Entity
@Builder
@Table(name = "machine")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MachineEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "assembly")
	@Convert(converter = BooleanConverter.class)
	private boolean assembly;

	@Column(name = "press")
	@Convert(converter = BooleanConverter.class)
	private boolean press;

	@JoinColumn(name = "location_key")
	@OneToOne(fetch = FetchType.EAGER)
	private LocationEntity locationEntity;
}

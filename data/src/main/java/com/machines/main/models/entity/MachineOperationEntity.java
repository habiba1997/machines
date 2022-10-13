package com.machines.main.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.machines.main.converters.BooleanConverter;

@Entity
@Subselect("SELECT m.name as machine_name," +
		" m.assembly as assembly," +
		" m.press as press," +
		" m.location_key as location_key," +
		" m.id as machine_key" +
		" FROM machine m")
@Immutable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = "machineKey")
public class MachineOperationEntity implements Serializable {

	@Id
	@Column(name = "machine_key")
	private Long machineKey;

	@Column(name = "machine_name")
	private String machineName;

	@Column(name = "assembly")
	@Convert(converter = BooleanConverter.class)
	private boolean assembly;

	@Column(name = "press")
	@Convert(converter = BooleanConverter.class)
	private boolean press;

	@OneToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "location_key", nullable = false, unique = true)
	private LocationEntity locationEntity;

	@OneToOne(mappedBy = "machineEntity")
	@Fetch(FetchMode.JOIN)
	private OperationEntity operationEntity;

}

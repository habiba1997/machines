package com.java.main.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "material")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "material")
	private Set<Operation> operations = new HashSet<>();

	@OneToOne(mappedBy = "material", fetch = FetchType.LAZY)
	private MeasuredValue measuredValue;

	@Column(name = "percentage_color")
	private boolean percentageColor;

	public void addOperation(final Operation operation) {
		this.operations.add(operation);
	}
}

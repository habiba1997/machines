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
@Table(name = "machine")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Machine implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "machine")
	private Set<Operation> operations = new HashSet<>();

	// used to test GlobalRepositoryJPATest
	/*
	 * @Column(name="ay7agawlsalam") private Long yalaAy7aga;
	 * 
	 * public Long getYalaAy7aga() { return yalaAy7aga; }
	 * 
	 * public void setYalaAy7aga(Long yalaAy7aga) { this.yalaAy7aga = yalaAy7aga; }
	 */

	public void addOperation(final Operation operation) {
		this.operations.add(operation);
	}
}

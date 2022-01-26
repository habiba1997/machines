package com.java.main.models.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
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

/* Cache Concurrency Strategies
	READ_WRITE:
 		This strategy guarantees strong consistency which it achieves by using ‘soft' locks: When a cached entity is updated,
 		a soft lock is stored in the cache for that entity as well, which is released after the transaction is committed.
 		All concurrent transactions that access soft-locked entries will fetch the corresponding data directly from database
	TRANSACTIONAL:
 		Cache changes are done in distributed XA transactions.
 		A change in a cached entity is either committed or rolled back in both database and cache in the same XA transaction 		
*/
@Entity
@Builder
@Table(name = "machine")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MachineEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long key;

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

package com.machines.main.repositories;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.machines.main.models.entity.DataEntity;

@Repository
public interface DataRepository extends JpaRepository<DataEntity, Long> {

	List<DataEntity> findAll(); // view

	@Query(nativeQuery = true, value = "call machines.selectAllMachineOperations()")
	List<MachineOperationNames> findMachineOperationLinked(); // stored procedure

	@Query(nativeQuery = true, value = "select machines.getMaterialMeasureValue(?1)")
	Long findMeasureValueOfMaterial(String materialName); // user defined function


	interface MachineOperationNames {
		String getMachine();
		String getOperation();
	}
}

package com.java.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.main.models.entity.MachineEntity;

@Repository
public interface MachineRepository extends JpaRepository<MachineEntity, Integer> {

	List<MachineEntity> findAll();

	@Query("select machineEntity from MachineEntity machineEntity " +
			"left join fetch machineEntity.locationEntity l where  l.name = ?1")
	List<MachineEntity> findMachinesByLocationName(String locationName);

}

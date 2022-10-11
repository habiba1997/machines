package com.machines.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.machines.main.models.entity.MachineOperationEntity;

@Repository
public interface MachineOperationRepository extends JpaRepository<MachineOperationEntity, Long> {

	List<MachineOperationEntity> findAll();

	MachineOperationEntity findByMachineName(String machineName);

}

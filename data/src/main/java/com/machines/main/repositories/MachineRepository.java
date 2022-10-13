package com.machines.main.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.machines.main.models.entity.MachineEntity;
import com.machines.main.profile.CacheConstants;

@Repository
public interface MachineRepository extends JpaRepository<MachineEntity, Integer> {

	@Cacheable(cacheNames = CacheConstants.MACHINES)
	List<MachineEntity> findAll();

	@Query("select machineEntity from MachineEntity machineEntity left join LocationEntity l on machineEntity.locationKey = l.key  where l.name = ?1")
	List<MachineEntity> findMachinesByLocationName(String locationName);

	Optional<MachineEntity> findByName(String name);

}

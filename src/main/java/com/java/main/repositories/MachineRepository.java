package com.java.main.repositories;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.main.models.entity.MachineEntity;
import com.java.main.profile.CacheConstants;

@Repository
public interface MachineRepository extends JpaRepository<MachineEntity, Integer> {

	@Cacheable(cacheNames = CacheConstants.MACHINES)
	List<MachineEntity> findAll();


	@Query("select machineEntity from MachineEntity machineEntity " +
			"left join fetch machineEntity.locationEntity l where  l.name = ?1")
	List<MachineEntity> findMachinesByLocationName(String locationName);

}

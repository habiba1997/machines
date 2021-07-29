package com.java.main.services;

import java.util.Set;

import com.java.main.models.dtos.MachineDTO;

public interface MachineService {

	Set<MachineDTO> getAllMachines();

	Set<MachineDTO> getAllMachinesWithSetupAndInOverEndingProductionStatus();

}

package com.machines.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machines.main.models.entity.DataEntity;
import com.machines.main.repositories.DataRepository;
import com.machines.main.response.Response;

@Service
public class DataService {

	@Autowired
	private DataRepository dataRepository;

	public Response<List<DataEntity>> getData() {
		return new Response(dataRepository.findAll());
	}

	public Response<List<DataRepository.MachineOperationNames>> getMachineOperationsLinkedNames() {
		return new Response(dataRepository.findMachineOperationLinked());
	}

	public Response<Long> getMaterialMeasureValue(final String materialName) {
		return new Response(dataRepository.findMeasureValueOfMaterial(materialName));
	}

}

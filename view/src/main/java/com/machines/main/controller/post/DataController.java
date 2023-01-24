package com.machines.main.controller.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.machines.main.impl.DataService;
import com.machines.main.models.entity.DataEntity;
import com.machines.main.repositories.DataRepository;
import com.machines.main.response.Response;

@RestController
@RequestMapping("/data")
public class DataController {

	@Autowired
	private DataService dataService;

	@GetMapping(value = "/machine-operation-names")
	public ResponseEntity<Response<List<DataRepository.MachineOperationNames>>> List() {
		Response<List<DataRepository.MachineOperationNames>> response = this.dataService.getMachineOperationsLinkedNames();
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping(value = "")
	public ResponseEntity<Response<List<DataEntity>>> getData() {
		Response<List<DataEntity>> response = this.dataService.getData();
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping(value = "/material-measure-value")
	public ResponseEntity<Response<Long>> postMachine(@RequestParam(name = "materialName") final String materialName) {
		Response<Long> response = this.dataService.getMaterialMeasureValue(materialName);
		return new ResponseEntity(response, HttpStatus.OK);
	}

}

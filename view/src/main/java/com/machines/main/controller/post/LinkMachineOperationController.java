package com.machines.main.controller.post;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.machines.main.impl.LinkMachineOperationService;
import com.machines.main.request.LinkMachineOperationRequest;
import com.machines.main.response.Response;

@RestController
public class LinkMachineOperationController {

	@Autowired
	private LinkMachineOperationService service;

	@PostMapping(value = "/link-machine-operation")
	public ResponseEntity<Response<Void>> linkMachineOperation(@Valid @RequestBody final LinkMachineOperationRequest request) {
		Response<Void> response = this.service.linkMachineOperation(request.getOperationName(), request.getMachineName());
		return new ResponseEntity(response, HttpStatus.OK);
	}

}

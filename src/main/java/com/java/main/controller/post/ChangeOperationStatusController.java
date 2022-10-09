package com.java.main.controller.post;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.controller.request.ChangeOperationStatusRequest;
import com.java.main.dtos.Operation;
import com.java.main.impl.cache.ChangeOperationStatusService;
import com.java.main.response.Response;

@RestController
@Validated
public class ChangeOperationStatusController {

	@Autowired
	private ChangeOperationStatusService service;

	@PostMapping(value = "/change-status")
	public ResponseEntity<Response<Operation>> changeOperationStatus(@Valid @RequestBody final ChangeOperationStatusRequest request) {
		Response<Operation> response = this.service.changeOperationStatus(request.getOperationName(), request.getOperationNewStatus());
		return new ResponseEntity(response, HttpStatus.OK);
	}

}

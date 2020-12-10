package com.java.main.error;

import org.springframework.http.HttpStatus;


public class NotFoundException extends APIExceptionBase{

	public NotFoundException(String message) {
	super(message);
	}
	
	@Override
	public HttpStatus getStatusCode()
	{
		return HttpStatus.NOT_FOUND;
	}
	
}
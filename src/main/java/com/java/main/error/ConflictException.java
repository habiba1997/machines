package com.java.main.error;

import org.springframework.http.HttpStatus;

public class ConflictException extends APIExceptionBase {

	public ConflictException(final String message) {
		super(message);
	}

	@Override
	public HttpStatus getStatusCode() {
		return HttpStatus.CONFLICT;
	}

}

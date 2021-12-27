package com.java.main.error;

import org.springframework.http.HttpStatus;

public abstract class APIExceptionBase extends RuntimeException {

	public APIExceptionBase(final String message) {
		super(message);
	}

	public abstract HttpStatus getStatusCode();

}

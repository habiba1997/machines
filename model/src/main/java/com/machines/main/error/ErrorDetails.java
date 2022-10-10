
package com.machines.main.error;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

	private String message;

	private String uri;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;

	public ErrorDetails(final String message, final String uri) {
		this();
		this.message = message;
		this.uri = uri;
	}
}

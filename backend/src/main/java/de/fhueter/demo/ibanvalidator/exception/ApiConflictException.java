package de.fhueter.demo.ibanvalidator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ApiConflictException extends RuntimeException {

	public ApiConflictException(String message) {
		super(message);
	}
}

package com.htisec.exception;

import org.springframework.http.HttpStatus;

public class AccountNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// company requirement Message for error
	private String message;
	private final HttpStatus httpStatus;

	public AccountNotExistException(String message) {
		this.message = message;
		this.httpStatus = null;
	}

	public AccountNotExistException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}

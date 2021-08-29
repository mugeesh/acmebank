package com.acmebank.exception;

public class CustomException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String message;

	public String getMessage() {
		return message;
	}

	public CustomException(String message) {
		this.message = message;
	}
}

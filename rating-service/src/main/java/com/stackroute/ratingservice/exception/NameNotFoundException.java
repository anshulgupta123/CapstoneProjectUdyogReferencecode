package com.stackroute.ratingservice.exception;

public class NameNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String message;

	public NameNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
	
}

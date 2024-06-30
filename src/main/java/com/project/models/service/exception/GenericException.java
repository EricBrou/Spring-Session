package com.project.models.service.exception;

public class GenericException extends RuntimeException{

private static final long serialVersionUID = -7531849615388769695L;
	
	public GenericException(String message) {
		super(message);
	}
	
	public GenericException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

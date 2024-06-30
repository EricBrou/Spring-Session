package com.project.models.service.exception;

public class EntityNotFoundException extends GenericException {

	private static final long serialVersionUID = 8846137543301642115L;

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

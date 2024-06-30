package com.project.models.service.exception;

public class RoleExistsException extends GenericException {

	private static final long serialVersionUID = -5580751204833151051L;
	
	public RoleExistsException(String message) {
		super(message);
	}

	public RoleExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}

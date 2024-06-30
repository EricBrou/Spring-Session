package com.project.models.service.exception;

public class EmailExistsException extends GenericException {
	
	private static final long serialVersionUID = 8143838100723652758L;

	public EmailExistsException(String message) {
		super(message);
	}
	
	public EmailExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}

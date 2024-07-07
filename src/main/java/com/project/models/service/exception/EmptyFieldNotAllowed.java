package com.project.models.service.exception;

public class EmptyFieldNotAllowed extends RuntimeException {

	private static final long serialVersionUID = 81374343863322880L;

	public EmptyFieldNotAllowed(String message) {
		super(message);
	}
	
}

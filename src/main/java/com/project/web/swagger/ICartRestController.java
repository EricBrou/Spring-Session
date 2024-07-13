package com.project.web.swagger;

import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpSession;

public interface ICartRestController {
	
	public ResponseEntity<?> list(HttpSession session);
	public ResponseEntity<?> add(HttpSession session, Long id);
	public ResponseEntity<?> delete(HttpSession session, Long id);
	
}

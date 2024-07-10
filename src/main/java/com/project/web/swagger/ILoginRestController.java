package com.project.web.swagger;

import org.springframework.http.ResponseEntity;

import com.project.models.model.Login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public interface ILoginRestController {

	public ResponseEntity<?> login(Login login, HttpServletRequest request, HttpServletResponse response, HttpSession session);
	
}

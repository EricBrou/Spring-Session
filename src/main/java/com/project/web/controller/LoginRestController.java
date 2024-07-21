package com.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.models.model.Login;
import com.project.models.service.impl.LoginService;
import com.project.web.response.SystemMessage;
import com.project.web.swagger.ILoginRestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest")
public class LoginRestController implements ILoginRestController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private SystemMessage<String> systemMessage;
	
	@Override
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody Login login, HttpServletRequest request, HttpServletResponse response, HttpSession session) {		
		String sessionToken = loginService.login(login, request, response);
		
		systemMessage.showMessage("Login realizado com sucesso!", HttpStatus.OK.value(), sessionToken);
		return ResponseEntity.ok().body(systemMessage);
	}

}

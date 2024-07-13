package com.project.models.service;

import com.project.models.model.Login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ILoginService {

	public String login(Login login, HttpServletRequest request, HttpServletResponse response);
	
}

package com.project.models.service.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class CryptEncoder {

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public PasswordEncoder getEncoder() {
		return passwordEncoder;
	}
	
	public String encryptPass(String pass) {
		return this.getEncoder().encode(pass);
	}
	
}
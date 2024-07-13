package com.project.models.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.models.model.User;
import com.project.models.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userDb = userRepository.findByEmail(email);
		
		if(userDb.isEmpty()) {
			throw new EntityNotFoundException("O usuário com o email especificado não existe.");
		}
		
		userDb.get().getAuthorities();
		
		return userDb.get();
	}

}

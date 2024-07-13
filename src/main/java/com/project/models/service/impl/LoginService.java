package com.project.models.service.impl;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.project.models.model.Login;
import com.project.models.model.User;
import com.project.models.repository.UserRepository;
import com.project.models.service.ILoginService;
import com.project.models.service.exception.EntityNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginService implements ILoginService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String login(Login login, HttpServletRequest request, HttpServletResponse response) {
		Optional<User> userDb = userRepository.findByEmail(login.getEmail());
		HttpSession session = request.getSession(true);
		
		if(userDb.isEmpty() || 
			login.getPassword().equals("") ||
			!BCrypt.checkpw(login.getPassword(), userDb.get().getPassword())) {
				request.getSession().invalidate();
				throw new EntityNotFoundException("Verifique se a senha e o Email estão corretos!");
		}
		
		session.setAttribute("AUTH", new UsernamePasswordAuthenticationToken(userDb.get(), userDb.get().getPassword(), userDb.get().getAuthorities()));
		session.setAttribute("CART", null);
		
		return Base64.getEncoder().encodeToString(session.getId().getBytes());
	}

}

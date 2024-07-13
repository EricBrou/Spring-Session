package com.project.models.service.impl;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private RedisService redisService;
	
	@Override
	public String login(Login login, HttpServletRequest request, HttpServletResponse response) {
		Optional<User> userData = userRepository.findByEmail(login.getEmail());
		String sessionToken;
		
		if(userData.isEmpty() || 
			login.getPassword().equals("") ||
			!BCrypt.checkpw(login.getPassword(), userData.get().getPassword())) {
				request.getSession().invalidate();
				throw new EntityNotFoundException("Verifique se a senha e o Email estão corretos!");
		}
		
		sessionToken = setupSession(userData.get(), request, response);
		
		return sessionToken;
	}
	
	private String setupSession(User user, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session;
		String sessionToken = (String)redisService.get(user.getId().toString()).get(user.getId().toString());
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(sessionToken == null) { //TODO: Tem os casos em que a sessão é inválida
			request.getSession().setAttribute("userId", user.getId());
			session = request.getSession(true);
			sessionToken = Base64.getEncoder().encodeToString(session.getId().getBytes());
			
			map.put(user.getId().toString(), sessionToken);			
			redisService.save(user.getId().toString(), map);
		}
		
		return sessionToken;
	}

}

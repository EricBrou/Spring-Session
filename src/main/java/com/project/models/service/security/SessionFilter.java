package com.project.models.service.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.models.service.impl.RedisService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionFilter extends OncePerRequestFilter {
	
	@Autowired
	private RedisService redisService;
	
	public SessionFilter() {
		
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Long userId;
		String sessionToken;
		Cookie cookie;
		
		HttpSession session = request.getSession(false);
		cookie = new Cookie("SESSION", "A");
		cookie.setMaxAge(3600);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		response.addCookie(cookie);
		
		filterChain.doFilter(request, response);
		
		/*if(session == null || session.getAttribute("userId") == null) {
			//Usuário deve se logar
		}else {
			userId = (Long)session.getAttribute("userId");
			
			sessionToken = (String)redisService.get(userId.toString()).get(userId.toString());
			
			cookie = new Cookie("SESSION", sessionToken);
			cookie.setMaxAge(3600);
			cookie.setPath("/");
			response.addCookie(cookie);
			
			filterChain.doFilter(request, response);
		}*/
		
	}

}

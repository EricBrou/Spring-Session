package com.project.models.config;

import java.io.IOException;
import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		setupAuth(request.getSession());
		filterChain.doFilter(request, response);

	}
	
	private void setupAuth(HttpSession session) 
	{
		if(SecurityContextHolder.getContext().getAuthentication() == null) {
			Authentication auth = (Authentication)session.getAttribute("AUTH");
			if(!Objects.isNull(auth)) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
	}

}

package com.project.models.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import jakarta.servlet.http.Cookie;

@ControllerAdvice
public class CookieAdvice implements ResponseBodyAdvice<Object>  {

	@Override
	public boolean supports(MethodParameter returnType,
	        Class<? extends HttpMessageConverter<?>> converterType) {
	    return true;
	}
	
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType,
	        MediaType selectedContentType,
	        Class<? extends HttpMessageConverter<?>> selectedConverterType,
	        ServerHttpRequest request, ServerHttpResponse response) {
	    Cookie cookie = new Cookie("SESSION", "AAAA");
	    cookie.setPath("/");
	    ServletServerHttpResponse resp = (ServletServerHttpResponse)response;
	    resp.getServletResponse().addCookie(cookie);
	    return body;
	}
	
}
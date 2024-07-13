package com.project.models.service;

import java.util.List;

import com.project.models.model.Product;

import jakarta.servlet.http.HttpSession;

public interface ICartService {

	public List<Product> list(HttpSession session);
	public Product add(HttpSession session, Long id);
	public void delete(HttpSession session, Long id);
	
}

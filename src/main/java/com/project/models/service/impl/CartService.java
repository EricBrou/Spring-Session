package com.project.models.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.model.Product;
import com.project.models.repository.ProductRepository;
import com.project.models.service.ICartService;
import com.project.models.service.exception.EntityNotFoundException;
import com.project.models.sessionModel.CartSession;

import jakarta.servlet.http.HttpSession;

@Service
public class CartService implements ICartService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> list(HttpSession session) {
		CartSession cart = (CartSession)session.getAttribute("CART");
		List<Product> userProducts = new ArrayList<Product>();
		List<Product> products;
		
		if(cart == null || cart.getProducts() == null) {
			cart = new CartSession();
			cart.setProducts(new ArrayList<Long>());
		}
		
		products = productRepository.findListById(cart.getProducts());
		
		for(Long productId : cart.getProducts()) {
			for(Product product : products) {
				if(product.getId().equals(productId)) {
					userProducts.add(product);
				}
			}
		}
		
		return userProducts;
	}

	@Override
	public Product add(HttpSession session, Long id) {
		Product productDb = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("O produto especificado não foi encontrado."));
		CartSession cart = (CartSession)session.getAttribute("CART");
		
		if(cart == null || cart.getProducts() == null) {
			cart = new CartSession();
			cart.setProducts(new ArrayList<Long>());
		}
		
		cart.getProducts().add(id);
		session.setAttribute("CART", cart);
		return productDb;
	}

	@Override
	public void delete(HttpSession session, Long id) {
		CartSession cart = (CartSession)session.getAttribute("CART");
		
		if(cart != null && cart.getProducts() != null) {
			cart.getProducts().remove(Long.valueOf(id));
			session.setAttribute("CART", cart);
		}
	}

}

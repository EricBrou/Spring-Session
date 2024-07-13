package com.project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.models.model.Product;
import com.project.models.service.impl.CartService;
import com.project.web.response.SystemMessage;
import com.project.web.swagger.ICartRestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping(value="/rest/cart")
public class CartRestController implements ICartRestController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private SystemMessage<Product> systemMessage;
	
	@Override
	@GetMapping(value="/list")
	public ResponseEntity<?> list(HttpSession session) {
		List<Product> products = cartService.list(session);
		
		return ResponseEntity.ok().body(products);
	}

	@Override
	@PostMapping(value="/add/{id}")
	public ResponseEntity<?> add(HttpSession session, @PathVariable("id") Long id) {
		Product product = cartService.add(session, id);
		systemMessage.showMessage("Produto adicionado ao carrinho.", HttpStatus.OK.value(), product);
		
		return ResponseEntity.ok().body(systemMessage);
	}

	@Override
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<?> delete(HttpSession session, @PathVariable("id") Long id) {
		cartService.delete(session, id);
		return ResponseEntity.ok().body("Produto excluído do carrinho com sucesso.");
	}

}

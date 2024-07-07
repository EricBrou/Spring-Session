package com.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.models.model.Product;
import com.project.models.service.impl.ProductService;
import com.project.web.response.SystemMessage;
import com.project.web.swagger.IProductRestController;

@RestController
@RequestMapping(value="/rest/product")
public class ProductRestController implements IProductRestController{
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SystemMessage<Product> systemMessage;

	@Override
	@GetMapping(value="/list")
	public ResponseEntity<?> list() {
		return ResponseEntity.ok().body(productService.list());
	}

	@Override
	@GetMapping(value="/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Product productResponse = productService.get(id);
		systemMessage.showMessage("Produto encontrado com sucesso!", HttpStatus.OK.value(), productResponse);
		return ResponseEntity.ok().body(systemMessage);
	}

	@Override
	@PostMapping(value="/save")
	public ResponseEntity<?> save(@RequestBody Product entity) {
		Product productResponse = productService.save(entity);
		systemMessage.showMessage("Produto cadastrado com sucesso!", HttpStatus.OK.value(), productResponse);
		return ResponseEntity.ok().body(systemMessage);
	}

	@Override
	@PutMapping(value="/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Product entity) {
		Product productResponse = productService.update(id, entity);
		systemMessage.showMessage("Produto atualizado com sucesso!", HttpStatus.OK.value(), productResponse);
		return ResponseEntity.ok().body(systemMessage);
	}

	@Override
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		productService.delete(id);
		systemMessage.showMessage("Produto excluído com sucesso!", HttpStatus.OK.value(), null);
		return ResponseEntity.ok().body(systemMessage);
	}
	
}

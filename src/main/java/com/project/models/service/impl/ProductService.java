package com.project.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.model.Product;
import com.project.models.repository.ProductRepository;
import com.project.models.service.IProductService;
import com.project.models.service.exception.EntityNotFoundException;

@Service
public class ProductService implements IProductService{
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public Product save(Product entity) {
		Product productResponse = productRepository.save(entity);
		return productResponse;
	}

	@Override
	public Product update(Long id, Product entity) {
		Product productData = productRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Entidade não localizada!"));
		
		Product productResponse;
		
		productData.setDescription(entity.getDescription());
		productData.setPrice(entity.getPrice());
		productData.setCategory(entity.getCategory());
		productData.setUser(entity.getUser());
		
		productResponse = productRepository.saveAndFlush(productData);
		return productResponse;
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public Product get(Long id) {
		Product productData = productRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Entidade não localizada!"));
		
		return productData;
	}

	@Override
	public List<Product> list() {
		List<Product> productData = productRepository.findAll();
		
		return productData;
	}

}

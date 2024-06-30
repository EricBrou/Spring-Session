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
		var productResponse = productRepository.save(entity);
		return productResponse;
	}

	@Override
	public Product update(Long id, Product entity) {
		var productData = productRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Entidade não localizada!"));
		
		productData.setDescription(entity.getDescription());
		productData.setPrice(entity.getPrice());
		productData.setCategory(entity.getCategory());
		
		var productResponse = productRepository.saveAndFlush(productData);
		return productResponse;
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public Product get(Long id) {
		var productData = productRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Entidade não localizada!"));
		return productData;
	}

	@Override
	public List<Product> list() {
		var productData = productRepository.findAll();
		return productData;
	}

}

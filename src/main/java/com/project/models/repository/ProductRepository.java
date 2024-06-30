package com.project.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.models.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	

}

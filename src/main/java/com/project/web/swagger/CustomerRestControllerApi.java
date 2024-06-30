package com.project.web.swagger;

import org.springframework.http.ResponseEntity;

import com.project.models.model.Customer;

public interface CustomerRestControllerApi {
	

	public ResponseEntity<?> listCustomer();
	
	public ResponseEntity<?> getCustomer(Long id);
	
	public ResponseEntity<?> saveCustomer(Customer customer);
	
	public ResponseEntity<?> updateCustomer(Long id, Customer customer);
	
	public ResponseEntity<?> deleteCustomer(Long id);

}

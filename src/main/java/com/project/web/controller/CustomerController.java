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

import com.project.models.model.Customer;
import com.project.models.service.impl.CustomerService;
import com.project.web.response.SystemMessage;
import com.project.web.swagger.CustomerRestControllerApi;


@RestController
@RequestMapping("/api/rest")
public class CustomerController implements CustomerRestControllerApi{
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SystemMessage<Customer> systemMessage;

	@Override
	@GetMapping("/list")
	public ResponseEntity<?> listCustomer() {
		return ResponseEntity.ok().body(customerService.list());
	}

	@Override
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable("id") Long id) {
		var customerResponse = customerService.get(id);
		systemMessage.showMessage("Cliente encontrado com sucesso!", HttpStatus.OK.value(), customerResponse);
		return ResponseEntity.ok().body(systemMessage);
	}

	@Override
	@PostMapping("/save")
	public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
		var customerResponse = customerService.save(customer);
		systemMessage.showMessage("Cliente cadastrado com sucesso!", HttpStatus.OK.value(), customerResponse);
		return ResponseEntity.ok().body(systemMessage);
	}

	@Override
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id,@RequestBody Customer customer) {
		var customerResponse = customerService.update(id, customer);
		systemMessage.showMessage("Cliente atualizado com sucesso!", HttpStatus.OK.value(), customerResponse);
		return ResponseEntity.ok().body(systemMessage);
	}

	@Override
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
		customerService.delete(id);
		systemMessage.showMessage("Cliente excluído com sucesso!", HttpStatus.OK.value(), null);
		return ResponseEntity.ok().body(systemMessage);
	}

	
}

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

import com.project.models.model.User;
import com.project.models.service.impl.UserService;
import com.project.web.response.SystemMessage;
import com.project.web.swagger.IUserRestController;

@RestController
@RequestMapping(value="/rest/user")
public class UserRestController implements IUserRestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SystemMessage<User> systemMessage;
	
	@Override
	@GetMapping(value="/list")
	public ResponseEntity<?> list() {
		return ResponseEntity.ok().body(userService.list());
	}

	@Override
	@GetMapping(value="/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		User userResponse = userService.get(id);
		systemMessage.showMessage("Usuário encontrado com sucesso!", HttpStatus.OK.value(), userResponse);
		return ResponseEntity.ok().body(systemMessage);
	}

	@Override
	@PostMapping(value="/save")
	public ResponseEntity<?> save(@RequestBody User entity) {
		User userResponse = userService.save(entity);
		systemMessage.showMessage("Usuário cadastrado com sucesso!", HttpStatus.OK.value(), userResponse);
		return ResponseEntity.ok().body(systemMessage);
	}

	@Override
	@PutMapping(value="/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody User entity) {
		User userResponse = userService.update(id, entity);
		systemMessage.showMessage("Usuário atualizado com sucesso!", HttpStatus.OK.value(), userResponse);
		return ResponseEntity.ok().body(systemMessage);
	}

	@Override
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		userService.delete(id);
		systemMessage.showMessage("Usuário excluído com sucesso!", HttpStatus.OK.value(), null);
		return ResponseEntity.ok().body(systemMessage);
	}

}

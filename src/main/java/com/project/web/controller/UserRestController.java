package com.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.models.model.User;
import com.project.models.service.impl.UserService;
import com.project.web.response.SystemMessage;
import com.project.web.swagger.IGenericRestController;

@RestController
@RequestMapping("/rest/user")
public class UserRestController implements IGenericRestController<User, Long> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SystemMessage<User> systemMessage;
	
	@Override
	@GetMapping("/list")
	public ResponseEntity<?> list() {
		return ResponseEntity.ok().body(userService.list());
	}

	@Override
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(Long id) {
		var userResponse = userService.get(id);
		systemMessage.showMessage("Usuário encontrado com sucesso!", HttpStatus.OK.value(), userResponse);
		return ResponseEntity.ok().body(systemMessage);
	}

	@Override
	@PostMapping("/save")
	public ResponseEntity<?> save(User entity) {
		var userResponse = userService.save(entity);
		systemMessage.showMessage("Usuário cadastrado com sucesso!", HttpStatus.OK.value(), userResponse);
		return ResponseEntity.ok().body(systemMessage);
	}

	@Override
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(Long id, User entity) {
		var userResponse = userService.update(id, entity);
		systemMessage.showMessage("Usuário atualizado com sucesso!", HttpStatus.OK.value(), userResponse);
		return ResponseEntity.ok().body(systemMessage);
	}

	@Override
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(Long id) {
		userService.delete(id);
		systemMessage.showMessage("Usuário excluído com sucesso!", HttpStatus.OK.value(), null);
		return ResponseEntity.ok().body(systemMessage);
	}

}

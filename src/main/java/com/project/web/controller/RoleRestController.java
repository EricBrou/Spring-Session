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

import com.project.models.model.Role;
import com.project.models.service.impl.RoleService;
import com.project.web.response.SystemMessage;
import com.project.web.swagger.IRoleRestController;

@RestController
@RequestMapping(value="/rest/role")
public class RoleRestController implements IRoleRestController{
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private SystemMessage<Role> systemMessage;

	@Override
	@GetMapping(value="/list")
	public ResponseEntity<?> list() {
		return ResponseEntity.ok().body(roleService.list());
	}

	@Override
	@GetMapping(value="/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		var roleResponse = roleService.get(id);
		systemMessage.showMessage("Perfil de acesso encontrado com sucesso!", HttpStatus.OK.value(), roleResponse);
		return ResponseEntity.ok().body(systemMessage);
	}

	@Override
	@PostMapping(value="/save")
	public ResponseEntity<?> save(@RequestBody Role entity) {
		var roleResponse = roleService.save(entity);
		systemMessage.showMessage("Perfil de acesso cadastrado com sucesso!", HttpStatus.OK.value(), roleResponse);
		return ResponseEntity.ok().body(systemMessage);
	}

	@Override
	@PutMapping(value="/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Role entity) {
		var roleResponse = roleService.update(id, entity);
		systemMessage.showMessage("Perfil de acesso atualizado com sucesso!", HttpStatus.OK.value(), roleResponse);
		return ResponseEntity.ok().body(systemMessage);
	}

	@Override
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		roleService.delete(id);
		systemMessage.showMessage("Perfil de acesso excluído com sucesso!", HttpStatus.OK.value(), null);
		return ResponseEntity.ok().body(systemMessage);
	}

}

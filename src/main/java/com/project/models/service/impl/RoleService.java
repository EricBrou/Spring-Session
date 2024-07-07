package com.project.models.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.model.Role;
import com.project.models.repository.RoleRepository;
import com.project.models.service.IRoleService;
import com.project.models.service.exception.EmptyFieldNotAllowed;
import com.project.models.service.exception.EntityNotFoundException;
import com.project.models.service.exception.RoleExistsException;

@Service
public class RoleService implements IRoleService{
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role save(Role entity) {
		Optional<Role> findRole = roleRepository.findByRole(entity.getRole());
		Role roleResponse;
		
		if(findRole.isPresent()) {
			throw new RoleExistsException("Esse Perfil de acesso já existe!");
		}
		
		roleResponse = roleRepository.save(entity);
		return roleResponse;
	}

	@Override
	public Role update(Long id, Role entity) {
		Role roleData = roleRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Entidade não localizada!"));
		
		Optional<Role> otherRole = roleRepository.findByRole(entity.getRole());		
		Role roleResponse;
		
		if(entity.getRole().equals("")) { 
			throw new EmptyFieldNotAllowed("O campo role não pode estar vazio"); 
		}

		if(otherRole.isPresent() && !(otherRole.get().getId().equals(id))) {
			throw new RoleExistsException("Esse Perfil de acesso já existe!");
		}
		
		roleData.setDescription(entity.getDescription());
		roleData.setRole(entity.getRole());
		
		roleResponse = roleRepository.saveAndFlush(roleData);
		return roleResponse;
	}

	@Override
	public void delete(Long id) {
		roleRepository.deleteById(id);
	}

	@Override
	public Role get(Long id) {
		Role roleData = roleRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Entidade não localizada!"));
		
		return roleData;
	}

	@Override
	public List<Role> list() {
		List<Role> roleData = roleRepository.findAll();
		return roleData;
	}

}

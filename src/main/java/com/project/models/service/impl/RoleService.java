package com.project.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.model.Role;
import com.project.models.repository.RoleRepository;
import com.project.models.service.IRoleService;
import com.project.models.service.exception.EntityNotFoundException;
import com.project.models.service.exception.RoleExistsException;

@Service
public class RoleService implements IRoleService{
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role save(Role entity) {
		var findRole = roleRepository.findByRole(entity.getRole());
		if(findRole.isPresent()) {
			throw new RoleExistsException("Esse Perfil de acesso já existe!");
		}
		var roleResponse = roleRepository.save(entity);
		return roleResponse;
	}

	@Override
	public Role update(Long id, Role entity) {
		var roleData = roleRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Entidade não localizada!"));
		
		if(roleData.getRole().equals(entity.getRole())) {
			var findRole = roleRepository.findByRole(entity.getRole());
			if(findRole.isPresent()) {
				throw new RoleExistsException("Esse Perfil de acesso já existe!");
			}
		}
		
		roleData.setDescription(entity.getDescription());
		roleData.setRole(entity.getRole());
		
		var roleResponse = roleRepository.saveAndFlush(roleData);
		return roleResponse;
	}

	@Override
	public void delete(Long id) {
		roleRepository.deleteById(id);
		
	}

	@Override
	public Role get(Long id) {
		var roleData = roleRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Entidade não localizada!"));
		return roleData;
	}

	@Override
	public List<Role> list() {
		var roleData = roleRepository.findAll();
		return roleData;
	}

}

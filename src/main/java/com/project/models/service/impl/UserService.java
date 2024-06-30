package com.project.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.service.exception.EntityNotFoundException;
import com.project.models.model.User;
import com.project.models.repository.UserRepository;
import com.project.models.service.IGenericService;
import com.project.models.service.exception.EmailExistsException;

@Service
public class UserService implements IGenericService<User, User, Long> {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User save(User entity) {
		var userEmail = userRepository.findByEmail(entity.getEmail());
		if(userEmail.isPresent() && userEmail.get().getEmail().equals(entity.getEmail())) {
			throw new EmailExistsException("Este e-mail já está em uso!");
		}
		var userResponse = userRepository.save(entity);
		return userResponse;
	}

	@Override
	public User update(Long id, User entity) {
		var userData = userRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Entidade não localizada!"));
		
		if(!userData.getEmail().equals(entity.getEmail())) {
			var userEmail = userRepository.findByEmail(entity.getEmail());
			if(userEmail.isPresent() && userEmail.get().getEmail().equals(entity.getEmail())) {
				throw new EmailExistsException("Este e-mail já está em uso!");
			}
		}
		
		userData.setUsername(entity.getUsername());
		userData.setEmail(entity.getEmail());
		userData.setPassword(entity.getPassword());
		
		var userResponse = userRepository.saveAndFlush(userData);
		return userResponse;
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return null;
	}

}

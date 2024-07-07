package com.project.models.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.model.User;
import com.project.models.repository.UserRepository;
import com.project.models.service.IUserService;
import com.project.models.service.exception.EmailExistsException;
import com.project.models.service.exception.EntityNotFoundException;

@Service
public class UserService implements IUserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User save(User entity) {
		Optional<User> userEmail = userRepository.findByEmail(entity.getEmail());
		User userResponse;
		
		if(userEmail.isPresent() && userEmail.get().getEmail().equals(entity.getEmail())) {
			throw new EmailExistsException("Este e-mail já está em uso!");
		}
		
		userResponse = userRepository.save(entity);
		return userResponse;
	}

	@Override
	public User update(Long id, User entity) {
		User userData = userRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Entidade não localizada!"));
		
		Optional<User> userEmail = userRepository.findByEmail(entity.getEmail());
		User userResponse;
		
		if(!userData.getEmail().equals(entity.getEmail())) {
			if(userEmail.isPresent() && userEmail.get().getEmail().equals(entity.getEmail())) {
				throw new EmailExistsException("Este e-mail já está em uso!");
			}
		}
		
		userData.setUsername(entity.getUsername());
		userData.setEmail(entity.getEmail());
		userData.setPassword(entity.getPassword());
		
		userResponse = userRepository.saveAndFlush(userData);
		return userResponse;
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User get(Long id) {
		User userData = userRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Entidade não localizada!"));
		
		return userData;
	}

	@Override
	public List<User> list() {
		List<User> userData = userRepository.findAll();

		return userData;
	}

}

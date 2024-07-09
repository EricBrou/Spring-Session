package com.project.models.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.model.Role;
import com.project.models.model.User;
import com.project.models.repository.RoleRepository;
import com.project.models.repository.UserRepository;
import com.project.models.service.IUserService;
import com.project.models.service.exception.EmailExistsException;
import com.project.models.service.exception.EntityNotFoundException;
import com.project.models.service.exception.UserRoleNotFound;
import com.project.models.service.security.CryptEncoder;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CryptEncoder pwdEncoder;

	@Override
	public User save(User entity) {
		Optional<User> userEmail = userRepository.findByEmail(entity.getEmail());
		Optional<Role> userRole = roleRepository.findByRole("USER");
		
		User userResponse;
		
		if(userEmail.isPresent() && userEmail.get().getEmail().equals(entity.getEmail())) {
			throw new EmailExistsException("Este e-mail já está em uso!");
		}
		
		if(userRole.isEmpty()) {
			throw new UserRoleNotFound("O cargo 'USER' não foi encontrado! Crie o cargo 'USER' na tabela 'TB_ROLE' para conseguir criar um novo usuário");
		}
		
		entity.getRoles().add(userRole.get());
		entity.setPassword(pwdEncoder.encryptPass(entity.getPassword()));
		
		userResponse = userRepository.save(entity);
	
		setupUserResponse(entity, userResponse);
		
		return userResponse;
	}
	
	private void setupUserResponse(User entity, User userResponse) {
		//A busca do banco é LAZY, então, ele não vai buscar outros objetos que estiverem no usuário ao salvar no banco;
		userResponse.setRoles(entity.getRoles());
		userResponse.setProducts(entity.getProducts());
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
		userData.setPassword(pwdEncoder.encryptPass(entity.getPassword()));
		userData.setRoles(entity.getRoles());
		userData.setProducts(entity.getProducts());
		
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

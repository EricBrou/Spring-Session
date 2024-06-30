package com.project.models.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.models.model.User;
import com.project.models.service.IGenericService;

@Service
public class UserService implements IGenericService<User, User, Long> {

	@Override
	public User save(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(Long id, User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
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

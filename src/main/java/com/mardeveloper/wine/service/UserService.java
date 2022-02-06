package com.mardeveloper.wine.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mardeveloper.wine.entity.User;
import com.mardeveloper.wine.repository.UserRepository;
import com.mardeveloper.wine.service.exception.DatabaseException;
import com.mardeveloper.wine.service.exception.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	public List<com.mardeveloper.wine.entity.User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);	
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());			
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public User update(Long id, User obj) {
		try {
			User entity = repository.getById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}
	
	private void updateData(User entity, User obj) {
		PasswordEncoder bcryptEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPassword(bcryptEncoder.encode(obj.getPassword()).replace("{bcrypt}", ""));
	}	
	
}

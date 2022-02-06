package com.mardeveloper.wine.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mardeveloper.wine.entity.User;
import com.mardeveloper.wine.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),	new ArrayList<>());
	}
	
	public User save(User user) {	
		PasswordEncoder bcryptEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		User newUser = new User();
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()).replace("{bcrypt}", ""));
		
		return userRepository.save(newUser);
	}
}
package com.mardeveloper.wine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.mardeveloper.wine.entity.User;
import com.mardeveloper.wine.repository.UserRepository;

//@Configuration
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		//PasswordEncoder bcryptEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		try {
			userRepository.deleteById((long) 1);	
		} catch (Exception e) {
			//e.printStackTrace();
		}		

		//User u = new User(null, "Marcos", "gmail.com", bcryptEncoder.encode("123456").replace("{bcrypt}", ""));
		User u = new User(null, "Marcos", "gmail.com", "123");
		
		userRepository.save(u);
		
		u.setName("Marcos Guimaraes");
		
		userRepository.save(u);
		
	}

}

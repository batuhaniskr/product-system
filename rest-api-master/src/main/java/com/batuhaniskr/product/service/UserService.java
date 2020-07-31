package com.batuhaniskr.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhaniskr.product.model.User;
import com.batuhaniskr.product.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User getUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		
		return user;
	}
	
	public User saveUser(User user) {
		User newUser = userRepository.save(user);
		
		return newUser;
	}
}

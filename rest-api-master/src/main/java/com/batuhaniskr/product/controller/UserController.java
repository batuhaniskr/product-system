package com.batuhaniskr.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batuhaniskr.product.model.User;
import com.batuhaniskr.product.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private UserService userService;	
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@PostMapping("/users")
	public void signUp(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userService.saveUser(user);
	}
}

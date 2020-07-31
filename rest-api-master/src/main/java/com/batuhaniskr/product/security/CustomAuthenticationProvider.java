package com.batuhaniskr.product.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.batuhaniskr.product.model.User;
import com.batuhaniskr.product.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
 
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
 
        String username = authentication.getName();
        String password =  authentication.getCredentials().toString();
        System.out.println(username + " " + password);
        User user = userService.getUserByUsername(username);
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) { 
            // use the credentials
            // and authenticate against the third-party system
            return new UsernamePasswordAuthenticationToken(
              username, password, new ArrayList<>());
        } else {
            throw new BadCredentialsException("Invalid credentials."); 
        }
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
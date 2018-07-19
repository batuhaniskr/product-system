package com.batuhaniskr.product.service;

import com.batuhaniskr.product.dto.UserRegistrationDto;
import com.batuhaniskr.product.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService extends UserDetailsService {

    User findByEmail(String email);
    User save(UserRegistrationDto userRegistrationDto);
}

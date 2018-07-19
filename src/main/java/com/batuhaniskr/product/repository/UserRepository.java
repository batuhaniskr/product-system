package com.batuhaniskr.product.repository;

import com.batuhaniskr.product.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}

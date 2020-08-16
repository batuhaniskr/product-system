package com.batuhaniskr.product.repository;

import com.batuhaniskr.product.model.Product;
import com.batuhaniskr.product.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByUser(User user);
}

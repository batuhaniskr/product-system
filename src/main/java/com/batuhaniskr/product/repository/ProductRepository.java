package com.batuhaniskr.product.repository;

import com.batuhaniskr.product.model.Product;
import com.batuhaniskr.product.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByUser(Pageable pageable, User user);
}

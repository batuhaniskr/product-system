package com.batuhaniskr.product.repository;

import com.batuhaniskr.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}

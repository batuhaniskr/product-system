package com.repository;

import com.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer>{
}

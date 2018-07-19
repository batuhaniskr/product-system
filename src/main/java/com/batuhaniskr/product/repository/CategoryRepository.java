package com.batuhaniskr.product.repository;

import com.batuhaniskr.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByCategoryName(String name);
}

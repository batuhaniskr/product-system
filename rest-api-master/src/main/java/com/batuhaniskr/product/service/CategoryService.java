package com.batuhaniskr.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhaniskr.product.model.Category;
import com.batuhaniskr.product.repository.CategoryRepository;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
    
    public Category getCategoryById(int id) {
    	return categoryRepository.findOne(id);
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findByCategoryName(name);
    }
}
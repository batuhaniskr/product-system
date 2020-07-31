package com.batuhaniskr.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batuhaniskr.product.model.Category;
import com.batuhaniskr.product.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(path="/category", produces = "application/json")
	public List<Category> getAllCategory() {
		List<Category> categoryList = categoryService.getAllCategory();
		
		return categoryList;
	}
	
	@GetMapping(path="/category/{id}", produces = "application/json")
	public Category getCategoryById(@PathVariable int id) {
		Category category = categoryService.getCategoryById(id);
		
		return category;
	}
	
}

package com.batuhaniskr.product.service;

import com.batuhaniskr.product.dto.CategoryDTO;
import com.batuhaniskr.product.model.Category;
import com.batuhaniskr.product.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::convertToCategoryToCategoryDTO).collect(Collectors.toList());
    }

    private CategoryDTO convertToCategoryToCategoryDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }
}
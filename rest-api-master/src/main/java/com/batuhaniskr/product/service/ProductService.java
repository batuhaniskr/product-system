package com.batuhaniskr.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.batuhaniskr.product.exception.ResourceNotFoundException;
import com.batuhaniskr.product.model.Category;
import com.batuhaniskr.product.model.Product;
import com.batuhaniskr.product.repository.CategoryRepository;
import com.batuhaniskr.product.repository.ProductRepository;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<Product> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {    	
        Category category = categoryRepository.findByCategoryName(product.getCategory().getCategoryName());
        product.setCategory(category);
        productRepository.save(product);
        
        return product;
    }
    
    public Product updateProduct(Integer id, Product updateProduct) {
       Product product = getProductById(id);
  	   product.setName(updateProduct.getName());
  	   product.setPrice(updateProduct.getPrice());
  	   product.setQuantity(updateProduct.getQuantity());
  	   product.setCategory(updateProduct.getCategory());
  	   Product savedProduct = saveProduct(product);
  	   
  	   return savedProduct;
    }

    public void deleteProductById(int id) {
        productRepository.delete(id);
    }

    public Product getProductById(Integer id) {
        Product product = productRepository.findOne(id);
        
        if (product == null) {
        	throw new ResourceNotFoundException("Product not found.");
        }
        
        return product;
    }
}

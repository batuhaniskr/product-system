package com.batuhaniskr.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batuhaniskr.product.model.Product;
import com.batuhaniskr.product.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService productService;
   
   @GetMapping(path = "/products", produces = "application/json")
   public List<Product> getAllProduct() {
	   List<Product> productList =  productService.getAllProduct();
	   
	   return productList;
   }
   
   @GetMapping(path = "/products/{id}")
   public Product getProduct(@PathVariable Integer id) {
	   Product product = productService.getProductById(id);
	   
	   return product;
   }
   
   @PostMapping("/products")
   public Product saveProduct(@RequestBody Product newProduct) {
		  return productService.saveProduct(newProduct);	 
   }
   
   @DeleteMapping(path = "/products/{id}")
   public void deleteProduct(@PathVariable Integer id) {
	   productService.deleteProductById(id);
   }
   
   @PutMapping("/products/{id}")
   public void updateProduct(@PathVariable Integer id, @RequestBody Product updateProduct) {
	   Product product = productService.getProductById(id);
	   product.setName(updateProduct.getName());
	   product.setPrice(updateProduct.getPrice());
	   product.setQuantity(updateProduct.getQuantity());
	   product.setCategory(updateProduct.getCategory());
	   productService.saveProduct(product);
   }
}

package com.batuhaniskr.product.controller;

import java.util.List;

import com.batuhaniskr.product.model.User;
import com.batuhaniskr.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

	@Autowired
	private UserService userService;
   
   @GetMapping(path = "/products", produces = "application/json")
   public List<Product> getAllProduct(Authentication authentication) {
       String username = authentication.getName();
	   List<Product> productList =  productService.getAllProduct(username);
	   
	   return productList;
   }
   
   @GetMapping(path = "/products/{id}")
   public Product getProduct(@PathVariable Integer id) {
	   Product product = productService.getProductById(id);
	   
	   return product;
   }
   
   @PostMapping("/products")
   public Product saveProduct(@RequestBody Product newProduct, Authentication authentication) {
       String username = authentication.getName();

       return productService.saveProduct(newProduct, username);
   }
   
   @DeleteMapping(path = "/products/{id}")
   public void deleteProduct(@PathVariable Integer id) {
	   productService.deleteProductById(id);
   }
   
   @PutMapping("/products/{id}")
   public void updateProduct(@PathVariable Integer id, @RequestBody Product updateProduct, Authentication authentication) {
	   Product product = productService.getProductById(id);
	   product.setName(updateProduct.getName());
	   product.setPrice(updateProduct.getPrice());
	   product.setQuantity(updateProduct.getQuantity());
	   product.setCategory(updateProduct.getCategory());

	   String username = authentication.getName();
	   productService.saveProduct(product, username);
   }
}

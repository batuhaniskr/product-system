package com.controller;

import com.service.ProductService;
import com.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class MainController {

    private ProductService productService;

    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("")
    public String index(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);

        return "products";
    }

    @RequestMapping(value = "/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());

        return "addproduct";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Product product) {
        productService.saveProduct(product);

        return "redirect:/products";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);

        return "redirect:/products";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editProduct(@PathVariable("id") Integer id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);

        return "editproduct";
    }
}

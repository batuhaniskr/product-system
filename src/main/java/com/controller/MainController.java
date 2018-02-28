package com.controller;

import com.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.repository.ProductRepository;

import java.util.List;

@Controller
@RequestMapping("/products")
public class MainController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("")
    public String index(Model model){
        List<Product> products = (List<Product>) productRepository.findAll();
        model.addAttribute("products",products);

        return "/products";
    }

    @RequestMapping(value = "/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Product product){
        productRepository.save(product);
        return "redirect:/products";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("id") Integer id){
        productRepository.delete(id);
        return "redirect:/products";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editProduct(@PathVariable("id") Integer id, Model model){

        Product product = productRepository.findOne(id);
        model.addAttribute("product", product);

        return "editProduct";
    }
}

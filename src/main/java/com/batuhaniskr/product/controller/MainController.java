package com.batuhaniskr.product.controller;

import com.batuhaniskr.product.model.Category;
import com.batuhaniskr.product.service.CategoryService;
import com.batuhaniskr.product.service.ProductService;
import com.batuhaniskr.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/products")
public class MainController {

    private ProductService productService;
    private CategoryService categoryService;

    private static int currentPage = 1;
    private static int pageSize = 5;
    private static final Logger LOG = Logger.getLogger(MainController.class.getName());

    @Autowired
    public MainController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping("")
    public String index(Model model, @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        page.ifPresent(p -> currentPage = p);
        size.ifPresent(s -> pageSize = s);

        Pageable pageable = new PageRequest(currentPage - 1, pageSize);
        Page<Product> productPage = productService.getAllProduct(pageable);

        model.addAttribute("productPage", productPage);

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "products";
    }


    @RequestMapping(value = "/add")
    public String addProduct(@Valid Model model) {
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("product", new Product());
        model.addAttribute("categoryList", categoryList);

        return "addproduct";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Product product) {
        LOG.log(Level.INFO, "/ " + product.getName());
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
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("product", product);
        model.addAttribute("categoryList", categoryList);

        return "editproduct";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handlerException() {
        return "error/404";
    }
}

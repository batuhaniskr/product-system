package com.batuhaniskr.product.controller;

import com.batuhaniskr.product.config.MyAccessDeniedHandler;
import com.batuhaniskr.product.config.SecurityConfig;
import com.batuhaniskr.product.dto.CategoryDTO;
import com.batuhaniskr.product.dto.ProductDTO;
import com.batuhaniskr.product.service.CategoryService;
import com.batuhaniskr.product.service.ProductService;
import com.batuhaniskr.product.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
@Import({SecurityConfig.class, MyAccessDeniedHandler.class})
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private UserService userService;

    @Test
    public void getProductsPage() throws Exception {
        CategoryDTO category = new CategoryDTO();
        category.setId(1);
        category.setCategoryName("teknoloji");

        List<ProductDTO> productList = new ArrayList<>();
        ProductDTO product1 = new ProductDTO();
        product1.setId(1);
        product1.setName("Product 1");
        product1.setCategory(category);
        productList.add(product1);

        ProductDTO product2 = new ProductDTO();
        product2.setId(2);
        product2.setName("Product 2");
        product2.setCategory(category);
        productList.add(product2);

        when(productService.getAllProduct(Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(productList));

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .with(user("batuhan").roles("USER")))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("products"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("productPage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("pageNumbers"));
    }

    @Test
    public void saveProduct() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1);
        categoryDTO.setCategoryName("Teknoloji");

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Test Product");
        productDTO.setPrice(10.99F);
        productDTO.setQuantity(1);
        productDTO.setCategory(categoryDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(
                "/products/save"
        ).with(csrf()).with(user("batuhan").roles("USER"))
                .flashAttr("productDTO", productDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
    }

    @Test
    public void saveProduct_withoutcsrf() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1);
        categoryDTO.setCategoryName("Teknoloji");

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Test Product");
        productDTO.setPrice(10.99F);
        productDTO.setQuantity(-1);
        productDTO.setCategory(categoryDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(
                                "/products/save"
                        ).with(user("batuhan").roles("USER"))
                        .flashAttr("productDTO", productDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/access-denied"));
    }

    @Test
    public void givenNegativeQuantity_whenSaveProduct_thenReturnsBadRequest() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1);
        categoryDTO.setCategoryName("Teknoloji");

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Test Product");
        productDTO.setPrice(10.99F);
        productDTO.setQuantity(-1);
        productDTO.setCategory(categoryDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(
                                "/products/save"
                        ).with(csrf()).with(user("batuhan").roles("USER"))
                        .flashAttr("productDTO", productDTO))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getProductsPage_whenUnauthenticatedUser() throws Exception {
        CategoryDTO category = new CategoryDTO();
        category.setId(1);
        category.setCategoryName("teknoloji");

        List<ProductDTO> productList = new ArrayList<>();
        ProductDTO product1 = new ProductDTO();
        product1.setId(1);
        product1.setName("Product 1");
        product1.setCategory(category);
        productList.add(product1);

        ProductDTO product2 = new ProductDTO();
        product2.setId(2);
        product2.setName("Product 2");
        product2.setCategory(category);
        productList.add(product2);



        when(productService.getAllProduct(Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(productList));

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"))
                .andDo(print());
    }

    @Test
    public void givenAdminRole_whenDeleteProduct_thenReturnsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/delete/1")
                        .with(user("batuhaniskr").password("password").roles("ADMIN")))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/products"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void givenUserRole_whenDeleteProduct_thenReturnsAccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/access-denied"))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void whenNoHandlerException_thenReturns404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/invalid-url"))
                .andExpect(status().isNotFound());
    }

}

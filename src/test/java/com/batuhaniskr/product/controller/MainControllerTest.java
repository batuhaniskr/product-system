package com.batuhaniskr.product.controller;

import com.batuhaniskr.product.config.MyAccessDeniedHandler;
import com.batuhaniskr.product.config.SecurityConfig;
import com.batuhaniskr.product.dto.ProductDTO;
import com.batuhaniskr.product.service.CategoryService;
import com.batuhaniskr.product.service.ProductService;
import com.batuhaniskr.product.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
    @WithMockUser(username = "user", roles = {"USER"})
    public void testSaveProduct_WithNegativePrice() throws Exception {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setPrice(-100F);

        String productJson = new ObjectMapper().writeValueAsString(productDTO);

        // Act & Assert
        mockMvc.perform(post("/products/save").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testSaveProduct_WithZeroPrice() throws Exception {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setPrice(0F);

        String productJson = new ObjectMapper().writeValueAsString(productDTO);

        // Act & Assert
        mockMvc.perform(post("/products/save")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testSaveProduct_WithNegativeQuantity() throws Exception {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setQuantity(-1);

        String productJson = new ObjectMapper().writeValueAsString(productDTO);

        // Act & Assert
        mockMvc.perform(post("/products/save")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testSaveProduct_WithZeroQuantity() throws Exception {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setQuantity(0);

        String productJson = new ObjectMapper().writeValueAsString(productDTO);

        // Act & Assert
        mockMvc.perform(post("/products/save")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isBadRequest());
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
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDeleteProduct_WithAdmin_Redirect() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/products/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testSaveProduct_WithoutCsrf() throws Exception {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setPrice(100F);
        productDTO.setQuantity(1);

        String productJson = new ObjectMapper().writeValueAsString(productDTO);

        // Act & Assert
        mockMvc.perform(post("/products/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(redirectedUrl("/access-denied"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testSaveProduct() throws Exception {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setPrice(100F);
        productDTO.setQuantity(1);

        String productJson = new ObjectMapper().writeValueAsString(productDTO);

        // Act & Assert
        mockMvc.perform(post("/products/save")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(redirectedUrl("/products"));
    }
}
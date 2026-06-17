package com.batuhaniskr.product.controller;

import com.batuhaniskr.product.model.Category;
import com.batuhaniskr.product.service.CategoryService;
import com.batuhaniskr.product.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private UserService userService; // Necesario para mantener estable el contexto de seguridad de Spring

    @Test
    public void testGetAllCategory_Success() throws Exception {
        // 1. ARRANGE (Preparar simulaciones)
        Category c1 = new Category();
        c1.setName("Electronics");
        Category c2 = new Category();
        c2.setName("Books");
        List<Category> mockList = Arrays.asList(c1, c2);

        Mockito.when(categoryService.getAllCategory()).thenReturn(mockList);

        // 2. ACT & 3. ASSERT (Ejecutar y Verificar)
        mockMvc.perform(get("/api/category"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Electronics"))
                .andExpect(jsonPath("$[1].name").value("Books"));
    }

    @Test
    public void testGetCategoryById_Success() throws Exception {
        // 1. ARRANGE
        Category category = new Category();
        category.setId(5);
        category.setName("Home Appliances");

        Mockito.when(categoryService.getCategoryById(5)).thenReturn(category);

        // 2. ACT & 3. ASSERT
        mockMvc.perform(get("/api/category/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Home Appliances"));
    }
}
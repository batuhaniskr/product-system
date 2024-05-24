package com.batuhaniskr.product;

import com.batuhaniskr.product.dto.CategoryDTO;
import com.batuhaniskr.product.dto.ProductDTO;
import com.batuhaniskr.product.model.Product;
import com.batuhaniskr.product.model.User;
import com.batuhaniskr.product.repository.CategoryRepository;
import com.batuhaniskr.product.repository.ProductRepository;
import com.batuhaniskr.product.service.ProductService;
import com.batuhaniskr.product.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper mockModelMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    public void getProductById() {
        ProductService productService = new ProductService(productRepository, categoryRepository, userService, mockModelMapper);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setName("Test");
        productDTO.setPrice(100F);
        productDTO.setQuantity(3);
        CategoryDTO category = new CategoryDTO();
        category.setCategoryName("TestCategory");
        category.setId(1);
        productDTO.setCategory(category);

        Product product = mockModelMapper.map(productDTO, Product.class);
        productRepository.save(product);

        when(productRepository.findOne(1)).thenReturn(product);
        when(mockModelMapper.map(any(), any())).thenReturn(productDTO);

        ProductDTO productByOneId = productService.getProductById(1);

        assertThat(productByOneId).isEqualTo(productDTO);
    }

    @Test(expected = AccessDeniedException.class)
    @WithMockUser(username = "user1")
    public void testGetProductById_UserNotOwner() {
        // Arrange
        User user1 = new User();
        user1.setUsername("user1");

        User user2 = new User();
        user2.setUsername("user2");

        Product product = new Product();
        product.setId(1);
        product.setUser(user2);

        when(productRepository.findOne(1)).thenReturn(product);
        when(userService.findByEmail("user1")).thenReturn(user1);

        // Act
        productService.getProductById(1);
    }
}

package com.batuhaniskr.product;

import com.batuhaniskr.product.dto.CategoryDTO;
import com.batuhaniskr.product.dto.ProductDTO;
import com.batuhaniskr.product.model.Category;
import com.batuhaniskr.product.model.Product;
import com.batuhaniskr.product.repository.CategoryRepository;
import com.batuhaniskr.product.repository.ProductRepository;
import com.batuhaniskr.product.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductServiceTests {

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void getProductById() {
        ProductService productService = new ProductService(productRepository, categoryRepository, mockModelMapper);
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
}
